package com.example.dbService.DAO;

import com.example.dbService.Entities.User;

public interface UserDAO {
    User findById(long id);
    void createUser(User user);
}
