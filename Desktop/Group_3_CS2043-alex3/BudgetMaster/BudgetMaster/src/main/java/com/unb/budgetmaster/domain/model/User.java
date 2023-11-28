package com.unb.budgetmaster.domain.model;

public class User {
    private String firstname;
    private String middlename;
    private String lastname;
    private String password;
    private String username;
    private String secQ1;
    private String secQ1Answer;
    private String secQ2;
    private String secQ2Answer;
    private double budget;

    public User(String firstname, String middlename, String lastname, String username, String password) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public void setSecQ1(String secQ1) {
        this.secQ1 = secQ1;
    }

    public void setSecQ1Answer(String secQ1Answer) {
        this.secQ1Answer = secQ1Answer;
    }

    public void setSecQ2(String secQ2) {
        this.secQ2 = secQ2;
    }

    public void setSecQ2Answer(String secQ2Answer) {
        this.secQ2Answer = secQ2Answer;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getMiddleName() {
        return middlename;
    }

    public String getLastName() {
        return lastname;
    }

    public String getFullName() {
        if(getMiddleName().equals(null)) {
            return firstname + " " + lastname;
        }

        return firstname + " " + middlename + " " + lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecQ1() {
        return secQ1;
    }

    public String getSecQ1Answer() {
        return secQ1Answer;
    }

    public String getSecQ2() {
        return secQ2;
    }

    public String getSecQ2Answer() {
        return secQ2Answer;
    }

    public void setBudgetTotal(double budget) {
        this.budget = budget;
    }

    public double getBudgetTotal() {
        return budget;
    }
}
