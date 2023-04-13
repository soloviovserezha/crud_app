package web.app.dao;

import web.app.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUserList();

    User getUserById(Long id);

    User deleteUserById(Long id);

    List<User> deleteAllUsers();

    User changeUser(String name, String surname);

    void addUser(User user);
}
