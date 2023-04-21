package web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.app.dao.UserDao;
import web.app.model.User;
import web.app.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public User deleteUserById(Long id) {
        return userDao.deleteUserById(id);
    }

    @Transactional
    @Override
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }

    @Transactional
    @Override
    public User changeUser(User user) {
        User userChanged = getUserById(user.getId());
        userChanged.setName(user.getName());
        userChanged.setSurname(user.getSurname());
        return userDao.changeUser(userChanged);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
