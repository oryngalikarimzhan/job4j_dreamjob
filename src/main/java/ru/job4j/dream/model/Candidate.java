package ru.job4j.dream.model;

import ru.job4j.dream.store.DbStore;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Candidate {
    private int id;
    private String name;
    private int cityId;
    private LocalDateTime created;

    public Candidate(int id, String name, int cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        created = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }

    public static void main(String[] args) {
        Collection<Candidate> candidates = DbStore.instOf().findAllCandidates();
        Map<Candidate, String> candidateMap = candidates.stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                candidate -> DbStore.instOf().findCityById(candidate.getCityId()).getName()));
        candidateMap.forEach((a, b) -> System.out.println(a.getName() + " " + b));
    }
}