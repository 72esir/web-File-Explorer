package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

//@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/filemanager";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "postgres";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        JavaDB.addToDB(username, password);
        response.sendRedirect("login.jsp");
        //Map<String, String> userDatabase = UserDataManager.getUserDatabase();

        /*if (registerUser(userDatabase, username, password, email)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("register.jsp");
        }*/
    }

    private boolean registerUser(Connection conn, String username, String password, String email) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw e;
        }
    }
}