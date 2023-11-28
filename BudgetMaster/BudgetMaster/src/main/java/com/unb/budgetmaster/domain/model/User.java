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

    public User(String firstname, String middlename, String lastname, String password, String username) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
        
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
     public void setQ1(String string) {
        secQ1 = string;
    }

     public void setQ2(String string) {
        secQ2 = string;
    }

    public void setQ1Answer(String string){
        secQ1Answer = string;
        
    }
    
     public void setQ2Answer(String string){
        secQ2Answer = string;
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
