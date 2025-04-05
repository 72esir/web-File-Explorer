package com.example.UsersManagment;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private String email;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}
