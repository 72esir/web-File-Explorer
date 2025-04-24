package com.example.dbService.DAO;

import com.example.dbService.Entities.User;
import org.hibernate.Session;

public class UserDAOImpl implements UserDAO{
    private Session session;

    public UserDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public User findById() {
        return null;
    }

    @Override
    public int createUser(User user) {
        return 0;
    }
}
