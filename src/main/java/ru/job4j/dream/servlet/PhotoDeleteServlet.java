package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class PhotoDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("id");
            File deletingFile = null;
            for (File file : new File(
                    "/Users/oryngalikarimzhan/IdeaProjects/job4j_dreamjob/./src/main/webapp/images/")
                    .listFiles()) {
                String fileName = file.getName();
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
                if (name.equals(file.getName()) || name.equals(fileName)) {
                    deletingFile = file;
                    deletingFile.delete();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
