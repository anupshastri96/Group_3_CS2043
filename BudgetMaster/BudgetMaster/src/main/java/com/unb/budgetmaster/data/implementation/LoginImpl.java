package com.unb.budgetmaster.data.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.unb.budgetmaster.domain.abs.LoginABS;
import com.unb.budgetmaster.domain.model.User;
import com.unb.budgetmaster.data.implementation.DatabaseImpl;

public class LoginImpl implements LoginABS {
    DatabaseImpl data = new DatabaseImpl();
    Connection connection = data.connectDatabase();

    @Override
    public Boolean checkLoginInfo(String username, String password) {
        String userName = "";
        String passWord = "";
        Boolean success = false;

        try{
            String getLoginInfo = "select user_name, user_password from user_data where user_name = " + username + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(getLoginInfo);
            ResultSet results = preparedStatement.executeQuery();

            if(results.next()){
                userName = results.getString("user_name");
                passWord = results.getString("user_password");
            }
            if(userName.equals(username) && passWord.equals(password)){
                success = true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Boolean checkSignUpInfo(String firstname, String lastname, String username, String password) {
        
       return null;
    }

    @Override
    public Boolean confirmPassword(String pass, String confirmPass) {
        if(pass.equals(confirmPass)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void setSecurityQuestions(String answer1, String answer2, String username) {
        try{
            Statement statement = connection.createStatement();
            String insertSecurityQuestions = "insert into security_data(account_answer1, account_answer2) values(" + answer1 + ", " + answer2 + " where user_name = " + username + ")";
            statement.executeQuery(insertSecurityQuestions);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean checkSecurityQuestions(String answer1, String answer2, String username) {
        String a1 = "";
        String a2 = "";
        Boolean success = false;

        try{
            String getAnswers = "select account_answer1, account_answer2 from security_data where username = " + username + ";";
            PreparedStatement statement = connection.prepareStatement(getAnswers);
            ResultSet results = statement.executeQuery();

            if(results.next()){
                a1 = results.getString("account_answer1");
                a2 = results.getString("account_answer2");
            }
            if(a1.equals(answer1) && a2.equals(answer2)){
                success = true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Boolean doesUsernameExists(String username) {
        String userName = "";
        Boolean success = false;
        try{
            String getUserName = "select user_name from user_data;";
            PreparedStatement statement = connection.prepareStatement(getUserName);
            ResultSet results = statement.executeQuery();

            while(results.next()){
                userName = results.getString("user_name");
                if(username.equals(userName)){
                    success = true;
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return success;
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
            String insertAccountSQL = "insert into account_data values(" + name + "," + middleName + "," + lastName + ") where user_name = " + userName + ";";
            //Inserts security question 1, question 2, answer 1, and answer 2 into security_data table
            String insertSecuritySQL = "insert into security_data values(" + q1 + "," + a1 + "," + q2 + "," + a2 + ") where user_name = " + userName + ";";
            statement.executeUpdate(insertUserSQL);
            statement.executeUpdate(insertAccountSQL);
            statement.executeUpdate(insertSecuritySQL);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
       
    }
    
}
