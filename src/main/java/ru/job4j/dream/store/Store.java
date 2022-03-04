package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store {

    void save(Post post);
    Post findPostById(int id);
    Collection<Post> findAllPosts();
    Post deletePost(int id);

    void save(Candidate candidate);
    Candidate findCandidateById(int id);
    Collection<Candidate> findAllCandidates();
    Candidate deleteCandidate(int id);

    void save(User user);
    User findUserByEmail(String email);
    User findUserById(int id);
    Collection<User> findAllUsers();
    User deleteUser(int id);
}