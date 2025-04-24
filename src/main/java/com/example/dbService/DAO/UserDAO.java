package com.example.dbService.DAO;

import com.example.dbService.Entities.User;

public interface UserDAO {
    boolean validateUser(String username, String password);
    void createUser(User user);
}
