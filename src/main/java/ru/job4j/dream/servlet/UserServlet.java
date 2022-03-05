package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("userProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        User user1 = DbStore.instOf().findUserByEmail(email);
        if (user1.equals(req.getSession().getAttribute("user"))) {
            DbStore.instOf().save(
                    new User(
                            user1.getId(),
                            req.getParameter("name"),
                            email,
                            req.getParameter("password")
                    )
            );
            req.setAttribute("message", "Данные успешно изменены. Выйдите с аккаунта чтобы изменения вступули в силу");
        }
        req.getRequestDispatcher("/userProfile.jsp").forward(req, resp);
    }
}
