package com.example;

import com.example.UsersManagment.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/start")
public class ServletForStart extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {

            resp.sendRedirect("login");
            return;
        }
        String path = "C:\\Users\\Алексей\\File_manager\\" + user.getLogin();
        resp.sendRedirect("filesList?path=" + path);
    }
}

