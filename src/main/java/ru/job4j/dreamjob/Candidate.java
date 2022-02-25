package ru.job4j.dreamjob;

public class Candidate {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private CV resume;

    public Candidate(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public void publishResume(CV resume) {
        this.resume = resume;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public CV getResume() {
        return resume;
    }
}
