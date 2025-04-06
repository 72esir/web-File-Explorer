package com.example;

import java.util.HashMap;
import java.util.Map;

public class UserDataManager {
    private static final Map<String, String> userDatabase = new HashMap<>();

    public static Map<String, String> getUserDatabase() {
        return userDatabase;
    }

}
