package bell.yusipov.fileservice.service.user;

import bell.yusipov.fileservice.dao.role.RoleDao;
import bell.yusipov.fileservice.dao.user.UserDao;
import bell.yusipov.fileservice.model.Role;
import bell.yusipov.fileservice.model.User;
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
        this.emailService = emailService;

    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addUser(User user) {
        if (user == null) {
            throw new RuntimeException("UserService ERROR: user cannot be null");
        }

        if (roleName == null) {
            saveRoleUser();
        }

        String validationCode = UUID.randomUUID().toString();


        SendThread sendThread = new SendThread(emailService, validationCode, user.getEmail());
        sendThread.start();


        user.setActivationCode(validationCode);
        user.setIsActive(false);
        user.setRole(roleName);
        user.setPassword(encoder.encode(user.getPassword()));

        userDao.addUser(user);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> userList() {

        return userDao.findAll();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void validateUser(String activationCode) {

        User user = userDao.getUserByActivationCode(activationCode);
        user.setIsActive(true);
        userDao.updateUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getOwnerByName(String owner) {

        return userDao.getUserByName(owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getOwnerId(User user) {

        return userDao.getUserId(user);
    }

    /**
     * Установка у нового пользователя роль user
     */
    private void saveRoleUser() {

        roleName = roleDao.getRole("user");
    }

}
