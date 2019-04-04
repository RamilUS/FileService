package bell.yusipov.fileservice.dao.role;

import bell.yusipov.fileservice.model.Role;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * {@inheritDoc}
 */
@Service
public class RoleDaoImp implements RoleDao {

    private final EntityManager entityManager;

    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role getRole(String roleName) {

        if(roleName == null || roleName.isEmpty()){
            throw new RuntimeException("Role name cannot be null or empty");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("roleName"), roleName));

        TypedQuery<Role> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getSingleResult();

    }

}
