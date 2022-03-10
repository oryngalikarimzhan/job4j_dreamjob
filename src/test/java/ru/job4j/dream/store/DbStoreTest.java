package ru.job4j.dream.store;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dream.ReStarter;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DbStoreTest {

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
    public void whenCreatePost() {
        Store store = DbStore.instOf(config);
        Post post = new Post(0, "Java Job");
        store.save(post);
        post.setId(1);
        Post postInDb = store.findPostById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        Store store = DbStore.instOf(config);
        Post post = new Post(0, "Java Job");
        Post post1 = new Post(1, "Java Job");
        store.save(post);
        store.save(post1);
        Post postInDb = store.findPostById(post1.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenFindAllPosts() {
        Store store = DbStore.instOf(config);
        Post post = new Post(0, "Java Job");
        store.save(post);
        post.setId(1);
        assertThat(store.findAllPosts(), is(List.of(post)));
    }

    @Test
    public void whenCreateCandidate() {
        Store store = DbStore.instOf(config);
        Candidate candidate = new Candidate(0, "Java Developer", 1);
        store.save(candidate);
        candidate.setId(1);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        Store store = DbStore.instOf(config);
        Candidate candidate = new Candidate(0, "Java Developer", 1);
        Candidate candidate1 = new Candidate(1, "Java Developer", 1);
        store.save(candidate);
        store.save(candidate1);
        Candidate candidateInDb = store.findCandidateById(candidate1.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenFindAllCandidates() {
        Store store = DbStore.instOf(config);
        Candidate candidate = new Candidate(0, "Java Developer", 1);
        store.save(candidate);
        candidate.setId(1);
        assertThat(store.findAllCandidates(), is(List.of(candidate)));
    }
}