package ru.job4j.dreamjob;

public class CV {
    private String position;
    private int salary;
    private String experience;
    private String characteristics;

    public CV(String position, int salary) {
        this.position = position;
        this.salary = salary;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
}
