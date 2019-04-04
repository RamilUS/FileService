package bell.yusipov.fileservice.config;

import bell.yusipov.fileservice.dao.user.UserDao;
import bell.yusipov.fileservice.model.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsrAuth implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UsrAuth(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usr usr = userDao.getUserByName(userName);
        return new UsrPrincipal(usr);
    }
}
