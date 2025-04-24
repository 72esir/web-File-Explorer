package com.example.dbService.DAO;

import com.example.dbService.Entities.User;

public interface UserDAO {
    User findById();
    int createUser(User user);
}
