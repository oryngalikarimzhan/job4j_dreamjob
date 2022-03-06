package ru.job4j.dream.servlet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dream.ReStarter;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServletTest {
    private static Properties config;

    @BeforeClass
    public static void init() {
        config = ReStarter.loadConfig();
    }

    @AfterClass
    public static void closeConnection() {
        ReStarter.closeConnection();
    }

    @After
    public void wipeTable(){
        ReStarter.wipeTable();
    }

    @Test
    public void whenCreatePost() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("name of new post");
        new PostServlet().doPost(req, resp);
        Post post = DbStore.instOf(config).findPostById(1);
        assertThat(post, notNullValue());
        assertThat(post.getName(),  is("name of new post"));
    }
}
