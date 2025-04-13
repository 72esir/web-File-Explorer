package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
public class JavaDB {
    private static final String url = "jdbc:postgresql://localhost:5432/webFileExplorerDb";
    private static final String user = "postgres";
    private static final String pass = "";

    public static void createTable(){
        String createQuery = "CREATE TABLE users (\n" +
                "    user_id SERIAL PRIMARY KEY,\n" +
                "    username VARCHAR(50) UNIQUE NOT NULL,\n" +
                "    password CHAR(60) NOT NULL\n" +
                ");";

        try (Connection con = DriverManager.getConnection(url,user, pass);
        PreparedStatement pst = con.prepareStatement(createQuery)){
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean existTable(){

        String tableName = "users";
        boolean exist = false;

        try (Connection con = DriverManager.getConnection(url,user,pass)){

            DatabaseMetaData meta = con.getMetaData();
            try (ResultSet rs = meta.getTables(null, null, tableName, new String[]{"TABLE"})) {
                return rs.next();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return exist;
    }

    public static void addToDB(String name, String password){

        String insertQuery = "INSERT INTO users(username, password) VALUES(?, ?)";

        try (Connection con = DriverManager.getConnection(url,user,pass);
             PreparedStatement pst = con.prepareStatement(insertQuery)) {

            pst.setString(1,name);
            //pst.setString(2,mail);
            pst.setString(2,password);
            pst.executeUpdate();

            //user_id = getID(name,password);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean existInDB(String user_name, String password){
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        boolean exist = false;

        try(Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1, user_name);
            pst.setString(2, password);

            try (ResultSet resultSet = pst.executeQuery()){
                if (resultSet.next()){
                    exist = true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return exist;
    }
}
