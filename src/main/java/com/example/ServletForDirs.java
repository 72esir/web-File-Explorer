package com.example;

import com.example.UsersManagment.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@WebServlet("/openDir, /filesList")
public class ServletForDirs extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }
        String userRoot = "C:\\Users\\Алексей\\File_manager\\" + user.getLogin();
        String path = req.getParameter("path");

        if (path == null || !path.startsWith(userRoot)) {
            path = userRoot;
        }

        File file = new File(path);

        req.setAttribute("files", file.listFiles());
        req.setAttribute("currentPath", path);
        req.setAttribute("parentPath", file.getParent());
        req.setAttribute("currentTime", new Date());
        req.getRequestDispatcher("/WEB-INF/filesList.jsp").forward(req,resp);
    }
}