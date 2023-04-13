package web.app.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.app.dao.UserDao;
import web.app.model.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserList() {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserById(Long id) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from User u where u.id= :id");
        query.setParameter("id", id);
        Optional optional = query.list().stream().findFirst();
        return (User) optional.get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User deleteUserById(Long id) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("delete User u where u.id= :id");
        query.setParameter("id", id);
        Optional optional = query.list().stream().findFirst();
        return (User) optional.get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> deleteAllUsers() {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("delete User");
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User changeUser(String name, String surname) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("update User u set u.name= :name, u.surname= : surname");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        Optional optional = query.list().stream().findFirst();
        return (User) optional.get();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }


}
