package com.unb.budgetmaster.data.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.unb.budgetmaster.data.implementation.DatabaseImpl;
import com.unb.budgetmaster.domain.abs.AnalysisABS;
import com.unb.budgetmaster.data.implementation.TransactionImpl;

public class AnalysisImpl implements AnalysisABS{
    public static LocalDate date = LocalDate.now();
    DatabaseImpl data = new DatabaseImpl();
    TransactionImpl transaction = new TransactionImpl();
    Connection connection = data.connectDatabase();

    @Override
    public double getTotalSpent(String username) {
        double total = 0;
        try{
            String totalSpentQuery = "select transaction_amount from transaction_data where transaction_type = 'Spendings' and user_name = " + username  + ";";
            PreparedStatement statement = connection.prepareStatement(totalSpentQuery);
            ResultSet results = statement.executeQuery();
            if(results.next()){
                total += results.getDouble("transaction_amount");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public double getTotalSaved(String username) {
        double total = 0;
        try{
            String totalSavedQuery = "select transaction_amount from transaction_data where transaction_type = 'Savings' and user_name = " + username + ";";
            PreparedStatement statement = connection.prepareStatement(totalSavedQuery);
            ResultSet results = statement.executeQuery();
            if(results.next()){
                total += results.getDouble("transaction_amount");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public double getTotalSpent(LocalDate date1, LocalDate date2, String username) {
        double total = 0;
        Date d1 = Date.valueOf(date1);
        Date d2 = Date.valueOf(date2);
        try{
            String queryTotalSpent = "select transaction_amount from transaction_data where transaction_date >= '" + d1 + "' and transaction_date <= '" + d2 + "' and transaction_type = 'Spendings' and user_name = " + username + ";";
            PreparedStatement statement = connection.prepareStatement(queryTotalSpent);
            ResultSet results = statement.executeQuery();
            if(results.next()){
                total += results.getDouble("transaction_amount");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public double getTotalSaved(LocalDate date1, LocalDate date2, String username) {
        double total = 0;
        Date d1 = Date.valueOf(date1);
        Date d2 = Date.valueOf(date2);
        try{
            String queryTotalSaved = "select transaction_amount from transaction_data where transaction_date >= '" + d1 + "' and transaction_date <= '" + d2 + "' and transaction_type = 'Savings' and user_name = " + username + ";";
            PreparedStatement statement = connection.prepareStatement(queryTotalSaved);
            ResultSet results = statement.executeQuery();
            if(results.next()){
                total += results.getDouble("transaction_amount");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public double getUsualSpent(String username) {
        int lengthOfMonth = date.lengthOfMonth();
        double totalAmountSpent = this.getTotalSpent(username);
        double total = totalAmountSpent/lengthOfMonth;

        return total;
    }
    @Override
    public double getUsualSpent(LocalDate date1, LocalDate date2, String username) {
        int lengthOfMonth = date.lengthOfMonth();
        double totalAmountSpent = this.getTotalSpent(date1, date2, username);
        double total = totalAmountSpent/lengthOfMonth;
        return total;
    }

    @Override
    public double getBalance(String username) {
        double total = this.getTotalSpent(username) - this.getTotalSaved(username);
        return total;
    }
    
}
