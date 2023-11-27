package com.unb.budgetmaster.budgetmaster.domain.abs;

import java.util.ArrayList;

public interface LoginABS {
    Boolean checkLoginInfo(String username, String password);
    Boolean checkSignUpInfo(String firstname, String lastname, String username, String password);
    Boolean confirmPassword(String pass, String confirmPass);
    void setSecurityQuestions(String username, String answer1, String answer2);
    Boolean checkSecurityQuestions(String username, String answer1, String answer2);
    Boolean doesUsernameExists(String username);
    void createUser(ArrayList<String> loginInformation);
}
