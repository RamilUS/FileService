package bell.yusipov.fileservice.dao.file;

import bell.yusipov.fileservice.model.File;
import bell.yusipov.fileservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class FileDaoImpl implements FileDao {

    private final EntityManager entityManager;

    @Autowired
    public FileDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadFile(File file) {

        entityManager.persist(file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File findFileByName(String fileName) {

        if (fileName == null || fileName.isEmpty()) {

            throw new RuntimeException("ERROR :File name is empty or null");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<File> criteriaQuery = criteriaBuilder.createQuery(File.class);
        Root<File> root = criteriaQuery.from(File.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("fileName"), fileName));
        TypedQuery<File> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<File> findAll() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<File> criteriaQuery = criteriaBuilder.createQuery(File.class);
        Root<File> root = criteriaQuery.from(File.class);

        criteriaQuery.select(root);

        TypedQuery<File> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<File> findUserFiles(User user) {

        if (user == null) {
            return Collections.emptyList();

        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<File> criteriaQuery = criteriaBuilder.createQuery(File.class);
        Root<File> root = criteriaQuery.from(File.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user));
        TypedQuery<File> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgradeFile(File file){

        entityManager.merge(file);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(File file) {

        entityManager.remove(file);
    }
}
