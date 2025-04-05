package com.example.UsersManagment;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private static final File FILE = new File("users.ser");
    private static Map<String, User> users = load();

    private static Map<String, User> load() {
        if (!FILE.exists()) return new HashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            return (Map<String, User>) in.readObject();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    private static void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User get(String login) {
        return users.get(login);
    }

    public static void add(User user) {
        users.put(user.getLogin(), user);
        save();
    }

    public static boolean exists(String login) {
        return users.containsKey(login);
    }
}
