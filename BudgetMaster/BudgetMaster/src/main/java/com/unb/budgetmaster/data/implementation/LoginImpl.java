package com.unb.budgetmaster.data.implementation;

import java.sql.SQLException;
import java.sql.Statement;

import com.unb.budgetmaster.domain.abs.LoginABS;
import com.unb.budgetmaster.domain.model.User;

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
        String name = user.getFirstName();
        String middleName = user.getMiddleName();
        String lastName = user.getLastName();
        String userName = user.getUsername();
        String passWord = user.getPassword();
        String q1 = user.getSecQ1();
        String q2 = user.getSecQ2();
        String a1 = user.getSecQ1Answer();
        String a2 = user.getSecQ2Answer();

        try{
            Statement statement = connection.createStatement();
            //Inserts username and password into user_data table
            String insertUserSQL = "insert into user_data values(" + userName + "," + passWord + ");";
             //Inserts name, middle name, and last name into account_data table
            String insertAccountSQL = "insert into account_data values(" + name + "," + middleName + "," + lastName + ");";
            //Inserts security question 1, question 2, answer 1, and answer 2 into security_data table
            String insertSecuritySQL = "insert into security_data values(" + q1 + "," + a1 + "," + q2 + "," + a2 + ");";
            statement.executeUpdate(insertUserSQL);
            statement.executeUpdate(insertAccountSQL);
            statement.executeUpdate(insertSecuritySQL);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
       
    }
    
}
