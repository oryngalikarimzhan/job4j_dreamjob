package ru.job4j.dream.model;

import ru.job4j.dream.store.DbStore;

import java.util.Objects;

public class City {
    private int id;
    private String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
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

    public static void main(String[] args) {
        DbStore.instOf().findAllCities().forEach(System.out::println);
    }
}
