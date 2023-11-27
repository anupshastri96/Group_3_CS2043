package com.unb.budgetmaster.data.implementation;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import com.unb.budgetmaster.domain.abs.TransactionABS;
import com.unb.budgetmaster.domain.model.Transaction;
import com.unb.budgetmaster.domain.model.Category;
import com.unb.budgetmaster.data.implementation.DatabaseImpl;

public class TransactionImpl implements TransactionABS{
    DatabaseImpl data = new DatabaseImpl();
    Connection connection = data.connectDatabase();

    @Override
    public Transaction getTransactionDetail(int id, String username) {
        Transaction transactionDetails;
        String transactionType = "";
        double transactionAmount = 0;
        LocalDate transactionDate = LocalDate.now();
        String categoryName = "";
        String payee = "";

        try{
            String transactionQuery = "select transaction_name, transaction_amount, category_name, date_format(transaction_date, '%d-%m-%Y'), transaction_payee from transaction_data natural join category_data natural join transaction_type where transaction_id = " + id +
            " and where user_name = " + username + "order by transaction_date asc;"; 
            PreparedStatement preparedStatement = connection.prepareStatement(transactionQuery);
            ResultSet results = preparedStatement.executeQuery();

            if(results.next()){
                transactionType = results.getString("transaction_name");
                transactionAmount = results.getDouble("transaction_amount");
                transactionDate = results.getDate("transaction_date").toLocalDate();
                categoryName = results.getString("category_name");
                payee = results.getString("transaction_payee");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        transactionDetails = new Transaction(transactionDate, id, transactionAmount, payee, transactionType, categoryName);
        return transactionDetails;
    }

    @Override
    public void setTransactionDetails(Transaction transaction, String username) {
        try{
            Statement statement = connection.createStatement();
            int transactionID = transaction.getID();
            Date transactionDate = Date.valueOf(transaction.getDate());
            double transactionAmount = transaction.getAmount();
            String transactionType = transaction.getType();
            String categoryType = transaction.getCategory();
            String payee = transaction.getPayee();
            String changeTransaction = "update transaction_data set transaction_date " + transactionDate
            + ", transaction_amount = " + transactionAmount
            + ", transaction_type = " + transactionType
            + ", category_name = " + categoryType 
            + ", transaction_payee = " + payee + " where transaction_id = " + transactionID + "and user_name = " + username + ";";
          
            statement.executeUpdate(changeTransaction);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    
    }

    @Override
    public void addTransaction(String date, int id, double amount, String type, String category, String username) {
        try{
            Statement statement = connection.createStatement();
            String transactionDate  = date;
            int transactionID = id;
            double transactionAmount = amount;
            String transactionType = type;
            String categoryType = category;
            String insertTransaction = "insert into transaction_data(transaction_date, transaction_id, transaction_amount, transaction_type) values(" + transactionDate + "," + transactionID + "," + transactionAmount + "," + transactionType + "," + categoryType + " where user_name = " + username + ");";
            statement.executeUpdate(insertTransaction);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }

    @Override
    public void deleteTransaction(Transaction transaction, String username) {
        int transactionID = transaction.getID();
        try{
            Statement statement = connection.createStatement();
            String deleteTransaction = "delete from transaction_data where " + transactionID + " and user_name = " + username + ";";
            statement.executeUpdate(deleteTransaction);
        }
         catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Transaction> getTransactions(String type, Category category, String sort, String username) {
        ArrayList<Transaction> allTransactions = new ArrayList<Transaction>();
        Transaction transaction;
        int transactionID = 0;
        String transactionType = "";
        double transactionAmount = 0;
        LocalDate transactionDate = LocalDate.now();
        String categoryName = "";
        String payee = "";

        try{
            String transactionQuery = "select transaction_id, transaction_name, transaction_amount, category_name, date_format(transaction_date, '%d-%m-%Y'), transaction_payee from transaction_data natural join category_data natural join transaction_type where user_name = " + username + "order by transaction_date asc;"; 
            
            PreparedStatement preparedStatement = connection.prepareStatement(transactionQuery);
            ResultSet results = preparedStatement.executeQuery();

            if(results.next()){
                transactionID = results.getInt("transaction_id");
                transactionType = results.getString("transaction_name");
                transactionAmount = results.getDouble("transaction_amount");
                transactionDate = results.getDate("transaction_date").toLocalDate();
                categoryName = results.getString("category_name");
                payee = results.getString("transaction_payee");
                transaction = new Transaction(transactionDate, transactionID, transactionAmount, payee, transactionType, categoryName);
                allTransactions.add(transaction);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return allTransactions;
    }

    public String toString(Transaction transaction) {
        
        return "Transaction Date: " + transaction.getDate() + "\n" +
               "Transaction ID: " + transaction.getID() + "\n" +
               "Transaction Amount: " + transaction.getAmount() + "\n" +
               "Transaction Payee: " + transaction.getPayee()  + "\n" +
               "Transaction Type: " + transaction.getType()  + "\n" +
               "Transaction Category: " + transaction.getCategory();
    }
    
}
