package bell.yusipov.fileservice.service.access;

import bell.yusipov.fileservice.config.UsrPrincipal;
import bell.yusipov.fileservice.dao.user.UserDao;
import bell.yusipov.fileservice.model.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * {@inheritDoc}
 */
@Service
public class AccessServiceImpl implements AccessService {

    private final EntityManager entityManager;
    private final UserDao userDao;

    @Autowired
    public AccessServiceImpl(EntityManager entityManager, UserDao userDao) {

        this.entityManager = entityManager;
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void requestVisibleAccess(UsrPrincipal usrPrincipal, Integer userId) {

        if (usrPrincipal == null || userId == null) {

            throw new RuntimeException("Access controller ERROR: empty parameters");

        }

        Usr principal = userDao.getUserByName(usrPrincipal.getUsername());
        Usr user = userDao.getUserById(userId);
        if (!user.getRequestersToVisible().contains(principal)
                && !user.getVisibleAccess().contains(principal)) {
            user.getRequestersToVisible().add(principal);

            userDao.updateUser(user);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void confirmVisibleAccess(UsrPrincipal usrPrincipal, Integer userId) {
        if (usrPrincipal == null || userId == null) {

            throw new RuntimeException("Access controller ERROR: empty parameters");

        }

        Usr principal = userDao.getUserByName(usrPrincipal.getUsername());
        Usr user = userDao.getUserById(userId);

        principal.getVisibleAccess().add(user);
        principal.getRequestersToVisible().remove(user);
        userDao.updateUser(principal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refuseVisibleAccess(UsrPrincipal usrPrincipal, Integer userId) {
        if (usrPrincipal == null || userId == null) {

            throw new RuntimeException("Access controller ERROR: empty parameters");

        }
        Usr principal = userDao.getUserByName(usrPrincipal.getUsername());
        Usr user = userDao.getUserById(userId);

        if(principal.getRequestersToVisible().contains(user)){

            principal.getRequestersToVisible().remove(user);
            userDao.updateUser(principal);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void requestDownloadAccess(UsrPrincipal usrPrincipal, Integer userId) {

        if (usrPrincipal == null || userId == null) {

            throw new RuntimeException("Access controller ERROR: empty parameters");

        }

        Usr principal = userDao.getUserByName(usrPrincipal.getUsername());
        Usr user = userDao.getUserById(userId);
        if (!user.getRequestersToDownload().contains(principal)
                && !user.getDownloadAccess().contains(principal)) {
            user.getRequestersToDownload().add(principal);

            userDao.updateUser(user);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void confirmDownloadAccess(UsrPrincipal usrPrincipal, Integer userId) {
        if (usrPrincipal == null || userId == null) {

            throw new RuntimeException("Access controller ERROR: empty parameters");

        }

        Usr principal = userDao.getUserByName(usrPrincipal.getUsername());
        Usr user = userDao.getUserById(userId);

        principal.getDownloadAccess().add(user);
        principal.getRequestersToDownload().remove(user);
        userDao.updateUser(principal);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refuseDownloadAccess(UsrPrincipal usrPrincipal, Integer userId) {

        if (usrPrincipal == null || userId == null) {

            throw new RuntimeException("Access controller ERROR: empty parameters");

        }
        Usr principal = userDao.getUserByName(usrPrincipal.getUsername());
        Usr user = userDao.getUserById(userId);

        if(principal.getRequestersToDownload().contains(user)){

            principal.getRequestersToDownload().remove(user);
            userDao.updateUser(principal);
        }

    }
}
