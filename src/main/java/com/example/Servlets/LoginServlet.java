package com.example.Servlets;
import com.example.dbService.JavaDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (JavaDB.existInDB(username,password)){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            response.sendRedirect("main-servlet");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}