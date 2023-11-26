package com.unb.budgetmaster.data.implementation;

import java.beans.Statement;
import java.util.ArrayList;

import com.unb.budgetmaster.domain.abs.TransactionABS;
import com.unb.budgetmaster.domain.model.Transaction;
import com.unb.budgetmaster.domain.model.Category;

public class TransactionImpl implements TransactionABS{

    @Override
    public Transaction getTransactionDetail(int id) {
        return null;
    }

    @Override
    public void setTransactionDetails(Transaction transaction) {
    
    }

    @Override
    public void addTransaction(String date, int id, double amount, String type, String category) {
        Statement statement = connection.createStatement();
        String transactionDate  = date;
        int transactionID = id;
        double transactionAmount = amount;
        String transactionType = type;
        String categoryType = category;
        String insertTransaction = "insert into transaction_data(transaction_date, transaction_id, transaction_amount, transaction_type) values(" + transactionDate + "," + transactionID + "," + transactionAmount + "," + transactionType + "," + categoryType + ");";
        statement.executeUpdate(insertTransaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
     
    }

    @Override
    public ArrayList<Transaction> getTransactions(String type, Category category, String sort) {
        return null;
    }

    public String toString(Transaction transaction) {
        return "Test String";
    }
    
}
