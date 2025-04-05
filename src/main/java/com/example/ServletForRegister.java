package com.example;

import com.example.UsersManagment.User;
import com.example.UsersManagment.UserStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/register")
public class ServletForRegister extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        if (UserStore.exists(login)) {
            req.setAttribute("error", "Пользователь уже существует");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        } else {
            User user = new User(login, password, email);
            UserStore.add(user);
            new File("C:\\Users\\Алексей\\File_manager\\" + login).mkdirs();
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("home");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
}

