package ru.job4j.dream.servlet;

import org.apache.log4j.pattern.LineSeparatorPatternConverter;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CandidateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Candidate, String> candidates = DbStore.instOf().findAllCandidates().stream()
                .collect(Collectors.toMap(
                                Function.identity(),
                                candidate -> DbStore.instOf().findCityById(candidate.getCityId()).getName()));
        req.setAttribute("candidates", candidates);
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        DbStore.instOf().save(
                new Candidate(
                        Integer.valueOf(req.getParameter("id")),
                        req.getParameter("name"),
                        Integer.parseInt(req.getParameter("city"))
                )
        );
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}