package bell.yusipov.fileservice.service.user;

import bell.yusipov.fileservice.dao.role.RoleDao;
import bell.yusipov.fileservice.dao.user.UserDao;
import bell.yusipov.fileservice.model.Role;
import bell.yusipov.fileservice.model.Usr;
import bell.yusipov.fileservice.service.email.EmailService;
import bell.yusipov.fileservice.service.email.SendThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder encoder;
    private Role roleName;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder encoder, EmailService emailService) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
        this.emailService=emailService;

    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addUser(Usr usr) {
        if (usr == null) {
            throw new RuntimeException("UserService ERROR: user cannot be null");
        }

        if (roleName == null) {
            saveRoleUser();
        }

        String validationCode= UUID.randomUUID().toString();


        SendThread sendThread= new SendThread(emailService,validationCode, usr.getEmail());
        sendThread.start();


        usr.setActivationCode(validationCode);
        usr.setIsActive(false);
        usr.setRole(roleName);
        usr.setPassword(encoder.encode(usr.getPassword()));

        userDao.addUser(usr);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Usr> userList() {

        return userDao.findAll();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public  void validateUser (String activationCode){
        Usr usr=userDao.getUserByActivationCode(activationCode);
                usr.setIsActive(true);
        userDao.updateUser(usr);
    }

    private void saveRoleUser() {
        roleName = roleDao.getRole("user");
    }

}
