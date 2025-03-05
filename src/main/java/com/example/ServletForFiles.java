package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@WebServlet()
public class ServletForFiles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        File file = new File(path);

        Path filePath = Paths.get(path);
        resp.setContentType(Files.probeContentType(filePath));
        resp.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        Files.copy(filePath, resp.getOutputStream());
    }
}
