package com.unb.budgetmaster.budgetmaster.data;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.PreparedStatement; 
import java.util.Scanner;
import java.util.InputMismatchException;
public class GetUserAccountInformation {
    public void main(String[]args){
        Connection connector = openConnection();
        String userName = args[0];
        if(connector = null){
            System.err.println("Unable to connect to the database"); 
            System.exit(1);
        }

        PreparedStatement statement = preparedStatementAccount(connector, userName);
        try{
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                String first_name = rs.getString("account_name");
                String middle_name = rs.getString("account_middlename");
                String last_name = rs.getString("account_lastname");
                String security_q1 = rs.getString("account_question1");
                String security_a1 = rs.getString("account_answer1");
                String security_q2 = rs.getString("account_question2");
                String security_a2 = rs.getString("account_answer2");
                String username = rs.getString("user_name");
                String password = rs.getString("user_password");
            }
        }
        catch(SQLException e){
            System.err.println("Could not execute database query");
        }
        closeConnection(connector);

    }
    private static Connection openConnection(){
        final String URL = "";
        final String USER = "";
        final String PASSWORD = "";
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(Exception e){
            System.err.println("Could not establish connection " + e.getMessage());
        }
        return connection;
    }
    private static void closeConnection(Connection connector){
        try{
            connector.close();
        }
        catch(Exception e){
            System.err.println("Could not close connection " + e.getMessage()); 
        }
    }
    private static PreparedStatement preparedStatementAccount(Connection connection, String userName){
        PreparedStatement result = null;
        try{
            String query = "select account_name, account_middlename, account_lastname, account_question1, account_answer1, account_question2, account_answer2, user_name, user_password " +
                           "from user_data natural join account_data " +
                           "where user_name = " + userName + ";"; 
            result = connector.preparedStatement(query);
        }
        catch(SQLException e){
            System.err.println("Could not prepare SQL Statement " + e.getMessage());
        }
        return result;
    }
}
