package com.unb.budgetmaster.budgetmaster.domain.implementation;

import com.unb.budgetmaster.budgetmaster.domain.abs.LoginABS;
import com.unb.budgetmaster.budgetmaster.domain.model.User;

public class LoginImpl implements LoginABS {

    @Override
    public Boolean checkLoginInfo(String username, String password) {
        return null;
    }

    @Override
    public Boolean checkSignUpInfo(String firstname, String lastname, String username, String password) {
       return null;
    }

    @Override
    public Boolean confirmPassword(String pass, String confirmPass) {
        return null;
    }

    @Override
    public void setSecurityQuestions(String answer1, String answer2) {
    }

    @Override
    public Boolean checkSecurityQuestions(String answer1, String answer2) {
        return null;
    }

    @Override
    public Boolean doesUsernameExists(String username) {
        return null;
    }

    @Override
    public void createUser(User user) {
    }
    
}
