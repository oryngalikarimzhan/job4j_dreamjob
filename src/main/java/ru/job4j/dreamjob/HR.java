package ru.job4j.dreamjob;

import java.util.ArrayList;
import java.util.List;

public class HR {
    private String name;
    private String companyName;
    private String phoneNumber;
    private String emailAddress;
    private List<Vacancy> vacancyList = new ArrayList<>();

    public HR(String name, String companyName, String phoneNumber, String emailAddress) {
        this.name = name;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public void publishVacancy(Vacancy vacancy) {
        this.vacancyList.add(vacancy);
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }
}
