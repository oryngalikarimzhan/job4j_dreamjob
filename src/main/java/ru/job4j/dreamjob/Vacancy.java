package ru.job4j.dreamjob;

public class Vacancy {
    private String position;
    private int salary;
    private String requirements;
    private String description;

    public Vacancy(String position, int salary) {
        this.position = position;
        this.salary = salary;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
