package com.unb.budgetmaster.budgetmaster.domain.model;

public class User {
    private String firstname;
    private String middlename;
    private String lastname;
    private String username;
    private String password;
    private String secQ1;
    private String secQ1Answer;
    private String secQ2;
    private String secQ2Answer;

    public User(String firstname, String middlename, String lastname, String username, String password, String secQ1, String secQ1Answer, String secQ2, String secQ2Answer) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.secQ1 = secQ1;
        this.secQ1Answer = secQ1Answer;
        this.secQ2 = secQ2;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

        return;
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
}
