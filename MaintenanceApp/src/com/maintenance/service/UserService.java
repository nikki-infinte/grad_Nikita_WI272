package com.maintenance.service;

import com.maintenance.dao.UserDAO;
import com.maintenance.dao.impl.UserDAOImpl;
import com.maintenance.model.User;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public void addOwner(String name, String phone) {
        User user = new User(name, phone, "OWNER");
        userDAO.addUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }
    
    public User getUser(int uid) {
        return userDAO.getUser(uid);
    }
}
