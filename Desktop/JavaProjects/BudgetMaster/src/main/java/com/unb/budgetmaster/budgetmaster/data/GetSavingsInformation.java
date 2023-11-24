package com.unb.budgetmaster.budgetmaster.data;
import java.sql.Connection; 
    import java.sql.DriverManager; 
    import java.sql.ResultSet;
    import java.sql.SQLException; 
    import java.sql.PreparedStatement; 
    import java.util.Scanner;
    import java.util.InputMismatchException;
public class GetSavingsInformation {
    public void main(String[]args){
        Connection connector = openConnection(); //opens connection to SQL database
        String userName = args[0]; //reads in username and stores it
        if(connector = null){
            System.err.println("Unable to connect to the database"); 
            System.exit(1);
        }
        //Preparing statement for query with connector and username
        PreparedStatement statement = preparedStatementSavings(connector, userName);
        try{
            ResultSet rs = statement.executeQuery();
            //if query returns results from database, each piece of information is stored in a variable
            if(rs.next()){
                String categorySavings = rs.getString("savings_current_amount");
                String goalSavings = rs.getString("savings_goal");
                String categoryName = rs.getString("category_name");
                String transactionType = rs.getString("transaction_name");
            }
        }
        catch(SQLException e){
            System.err.println("Could not execute database query");
        }
        closeConnection(connector);

    }
    //Method to create a connection to SQL database
    private static Connection openConnection(){
        final String URL = "jdbc:mysql://localhost:3306/sonoo";
        final String USER = "root";
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
    //Method to close connection to SQL database
    private static void closeConnection(Connection connector){
        try{
            connector.close();
        }
        catch(Exception e){
            System.err.println("Could not close connection " + e.getMessage()); 
        }
    }
    //Method to prepare statement to execute the query to SQL database
    private static PreparedStatement preparedStatementSavings(Connection connection, String userName){
        PreparedStatement result = null;
        try{
            //Query to get information from SQL database
            String query = "select savings_goal, savings_current_amount, category_name, transaction_name " +
                        "from transaction_type natural join category_data natural join transaction_type" +
                        "where user_name = " + userName + ";"; 

            result = connector.preparedStatement(query);
        }
        catch(SQLException e){
            System.err.println("Could not prepare SQL Statement " + e.getMessage());
        }
        //Return results from query
        return result;
    }
    
}   

