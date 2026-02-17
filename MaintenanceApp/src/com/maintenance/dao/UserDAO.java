package com.maintenance.dao;

import com.maintenance.model.User;
import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUser(int uid);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    User login(String username, String password);
}
