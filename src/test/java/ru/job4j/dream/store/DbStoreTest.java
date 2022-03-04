package ru.job4j.dream.store;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DbStoreTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = DbStoreTest.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("jdbc.driver"));
            connection = DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "ALTER SEQUENCE post_id_seq RESTART WITH 1;"
                + "DELETE FROM post;"
                + "ALTER SEQUENCE candidate_id_seq RESTART WITH 1;"
                + "DELETE FROM post;"
                + "ALTER SEQUENCE users_id_seq RESTART WITH 1;"
                + "DELETE FROM users;")) {
            statement.execute();
        }
    }

    @Test
    public void whenCreatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        post.setId(1);
        Post postInDb = store.findPostById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        Post post1 = new Post(1, "Java Job");
        store.save(post);
        store.save(post1);
        Post postInDb = store.findPostById(post1.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenFindAllPosts() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        post.setId(1);
        assertThat(store.findAllPosts(), is(List.of(post)));
    }

    @Test
    public void whenCreateCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Developer");
        store.save(candidate);
        candidate.setId(1);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Developer");
        Candidate candidate1 = new Candidate(1, "Java Developer");
        store.save(candidate);
        store.save(candidate1);
        Candidate candidateInDb = store.findCandidateById(candidate1.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenFindAllCandidates() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Developer");
        store.save(candidate);
        candidate.setId(1);
        assertThat(store.findAllCandidates(), is(List.of(candidate)));
    }
}