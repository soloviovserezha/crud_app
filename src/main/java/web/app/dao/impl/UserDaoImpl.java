package web.app.dao.impl;

import org.springframework.stereotype.Repository;
import web.app.dao.UserDao;
import web.app.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUserList() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User deleteUserById(Long id) {
        User user = getUserById(id);
        entityManager.remove(user);
        return user;
    }

    @Override
    public void deleteAllUsers() {
        entityManager.createQuery("DELETE FROM User").executeUpdate();
    }

    @Override
    public User changeUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }


}
