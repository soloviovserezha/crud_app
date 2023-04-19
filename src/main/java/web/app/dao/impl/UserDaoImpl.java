package web.app.dao.impl;

import org.springframework.stereotype.Component;
import web.app.dao.UserDao;
import web.app.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Component
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUserList() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User deleteUserById(Long id) {
        User user = getUserById(id);
        entityManager.remove(user);
        entityManager.close();
        return user;
    }

    @Override
    public List<User> deleteAllUsers() {
        List<User> userList = getUserList();
        for (User user : userList) {
            entityManager.remove(user);
        }
        entityManager.close();
        return userList;
    }

    @Override
    public User changeUser(User user) {
        List<User> userList = getUserList();
        for (User userEnt : userList) {
            if (Objects.equals(userEnt.getId(), user.getId())) {
                userEnt.setName(user.getName());
                userEnt.setSurname(user.getSurname());
                entityManager.persist(userEnt);
            }
        }
        entityManager.close();
        return user;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }


}
