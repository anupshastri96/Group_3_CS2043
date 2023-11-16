package com.unb.budgetmaster.budgetmaster.domain.abs;

import com.unb.budgetmaster.budgetmaster.domain.model.User;

public interface LoginABS {
    Boolean checkLoginInfo(String username, String password);
    Boolean checkSignUpInfo(String firstname, String lastname, String username, String password);
    Boolean confirmPassword(String pass, String confirmPass);
    void setSecurityQuestions(String answer1, String answer2);
    Boolean checkSecurityQuestions(String answer1, String answer2);
    Boolean doesUsernameExists(String username);
    void createUser(User user);
}
