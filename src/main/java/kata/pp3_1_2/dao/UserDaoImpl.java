package kata.pp3_1_2.dao;

import kata.pp3_1_2.model.User;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> userList() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long ID) {
        entityManager.remove(entityManager.find(User.class, ID));
    }

    @Override
    public User getUserById(long ID) {
        return entityManager.createQuery("from User where id = :id", User.class)
                .setParameter("id", ID).getSingleResult();
    }
}
