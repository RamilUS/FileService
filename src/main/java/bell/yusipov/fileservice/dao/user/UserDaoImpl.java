package bell.yusipov.fileservice.dao.user;

import bell.yusipov.fileservice.model.Usr;
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
    public void addUser(Usr usr) {
        if (usr == null) {
            return;
        }

        entityManager.persist(usr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Usr> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usr> criteriaQuery = criteriaBuilder.createQuery(Usr.class);
        Root<Usr> root = criteriaQuery.from(Usr.class);

        criteriaQuery.select(root);

        TypedQuery<Usr> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Usr getUserByName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new RuntimeException("User dao error: user name cannot be null or empty");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usr> criteriaQuery = criteriaBuilder.createQuery(Usr.class);
        Root<Usr> root = criteriaQuery.from(Usr.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("userName"), userName));

        TypedQuery<Usr> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usr getUserById(Integer userId){
        if (userId == null) {
            throw new RuntimeException("User dao error: user ID cannot be null");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usr> criteriaQuery = criteriaBuilder.createQuery(Usr.class);
        Root<Usr> root = criteriaQuery.from(Usr.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), userId));

        TypedQuery<Usr> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getSingleResult();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Usr getUserByActivationCode(String activationCode) {

        if (activationCode == null || activationCode.isEmpty()) {
            throw new RuntimeException("Activation code cannot be null or empty");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usr> criteriaQuery = criteriaBuilder.createQuery(Usr.class);
        Root<Usr> root = criteriaQuery.from(Usr.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("activationCode"), activationCode));

        TypedQuery<Usr> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getSingleResult();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(Usr usr) {

        if (usr == null) {
            return;
        }
        entityManager.merge(usr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getUserId(Usr usr){

        return usr.getId();
    }

}
