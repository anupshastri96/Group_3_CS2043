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
            Connection connector = openConnection();
            String userName = args[0];
            if(connector = null){
                System.err.println("Unable to connect to the database"); 
                System.exit(1);
            }
    
            PreparedStatement statement = preparedStatementSavings(connector, userName);
            try{
                ResultSet rs = statement.executeQuery();
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
        private static PreparedStatement preparedStatementSavings(Connection connection, String userName){
            PreparedStatement result = null;
            try{
                String query = "select savings_goal, savings_current_amount, category_name, transaction_name " +
                               "from transaction_type natural join category_data natural join transaction_type" +
                               "where user_name = " + userName + ";"; 
    
                result = connector.preparedStatement(query);
            }
            catch(SQLException e){
                System.err.println("Could not prepare SQL Statement " + e.getMessage());
            }
            return result;
        }
        
    }   

