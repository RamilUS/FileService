package bell.yusipov.fileservice.dao.user;

import bell.yusipov.fileservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUser(User user) {
        if (user == null) {
            return;
        }

        entityManager.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new RuntimeException("User dao error: user name cannot be null or empty");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("userName"), userName));

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserById(Integer userId){
        if (userId == null) {
            throw new RuntimeException("User dao error: user ID cannot be null");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), userId));

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getSingleResult();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByActivationCode(String activationCode) {

        if (activationCode == null || activationCode.isEmpty()) {
            throw new RuntimeException("Activation code cannot be null or empty");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("activationCode"), activationCode));

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getSingleResult();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(User user) {

        if (user == null) {
            return;
        }
        entityManager.merge(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getUserId(User user){

        return user.getId();
    }

}
