package com.jobApplication.generateMail.Model;

public class Recruiter {
    private String name;
    private String email;
    private String company;

    public Recruiter() {
    }

    public Recruiter(String name, String email, String company) {
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Recruiter{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
