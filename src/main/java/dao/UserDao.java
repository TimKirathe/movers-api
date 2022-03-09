package dao;

import models.User;

import java.util.List;

public interface UserDao {

    // CREATE
    void save(User user);

    // READ
    User findByEmail(String email);
    List<User> getAll();

    // UPDATE


    // DELETE
    void clearAll();
}
