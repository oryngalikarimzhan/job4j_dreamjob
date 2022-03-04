package ru.job4j.dream.servlet;

import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String delObjParam = req.getParameter("obj");
        if ("can".equals(delObjParam)) {
            DbStore.instOf().deleteCandidate(Integer.valueOf(id));
            req.getRequestDispatcher("/photoDelete").include(req, resp);
            resp.sendRedirect(req.getHeader("referer"));
        } else if ("user".equals(delObjParam)) {
            DbStore.instOf().deleteUser(Integer.valueOf(id));
            resp.sendRedirect(req.getContextPath() + "/logout.do");
        } else if ("post".equals(delObjParam)) {
            DbStore.instOf().deletePost(Integer.valueOf(id));
            resp.sendRedirect(req.getHeader("referer"));
        }

    }


}
