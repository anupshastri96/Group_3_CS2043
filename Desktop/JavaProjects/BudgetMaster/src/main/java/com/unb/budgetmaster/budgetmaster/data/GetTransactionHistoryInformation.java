package com.unb.budgetmaster.budgetmaster.data;
import java.sql.Connection; 
    import java.sql.DriverManager; 
    import java.sql.ResultSet;
    import java.sql.SQLException; 
    import java.sql.PreparedStatement; 
    import java.util.Scanner;
    import java.util.InputMismatchException;
public class GetTransactionHistoryInformation {
    public void main(String[]args){
        Connection connector = openConnection(); //opens connection to SQL database
        String userName = args[0]; //reads in username and stores it
        if(connector = null){
            System.err.println("Unable to connect to the database"); 
            System.exit(1);
        }
        //Preparing statement for query with connector and username
        PreparedStatement statement = preparedStatementTransactions(connector, userName);
        try{
            //if query returns results from database, each piece of information is stored in a variable
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                String transactionType = rs.getString("transaction_name");
                double transactionAmount = rs.getString("transaction_amount");
                String transactionDate = rs.getString("transaction_date");
                String categoryName = rs.getString("category_name");
            }
        }
        catch(SQLException e){
            System.err.println("Could not execute database query");
        }
        closeConnection(connector);

    }
    //Method to create a connection to SQL database
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
    private static PreparedStatement preparedStatementTransactions(Connection connection, String userName){
        PreparedStatement result = null;
        try{
            //Query to get information from SQL database
            String query = "select transaction_name, transaction_amount, convert(transaction_date, varchar), category_name " +
                            "from transaction_data natural join category_data natural join transaction_type" +
                            "order by transaction_date asc" +
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

