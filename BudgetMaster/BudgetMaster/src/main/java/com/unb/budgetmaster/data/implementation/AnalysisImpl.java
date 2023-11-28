package com.unb.budgetmaster.data.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import com.unb.budgetmaster.domain.abs.AnalysisABS;

public class AnalysisImpl implements AnalysisABS{
    public static LocalDate date = LocalDate.now();
    TransactionImpl transaction = new TransactionImpl();
    Connection connection = Database.getDatabase();

    @Override
    public double getTotalSpent() {
        double total = 0;
        try{
            String totalSpentQuery = "select transaction_amount from transaction_data where transaction_type = 'Spendings' and user_name = " + Database.user.getUsername()  + ";";
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
    public double getTotalSaved() {
        double total = 0;
        try{
            String totalSavedQuery = "select transaction_amount from transaction_data where transaction_type = 'Savings' and user_name = " + Database.user.getUsername() + ";";
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
    public double getTotalSpent(LocalDate date1, LocalDate date2) {
        double total = 0;
        Date d1 = Date.valueOf(date1);
        Date d2 = Date.valueOf(date2);
        try{
            String queryTotalSpent = "select transaction_amount from transaction_data where transaction_date >= '" + d1 + "' and transaction_date <= '" + d2 + "' and transaction_type = 'Spendings' and user_name = " + Database.user.getUsername() + ";";
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
    public double getTotalSaved(LocalDate date1, LocalDate date2) {
        double total = 0;
        Date d1 = Date.valueOf(date1);
        Date d2 = Date.valueOf(date2);
        try{
            String queryTotalSaved = "select transaction_amount from transaction_data where transaction_date >= '" + d1 + "' and transaction_date <= '" + d2 + "' and transaction_type = 'Savings' and user_name = " + Database.user.getUsername() + ";";
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
    public double getUsualSpent() {
        int lengthOfMonth = date.lengthOfMonth();
        double totalAmountSpent = this.getTotalSpent();
        double total = totalAmountSpent/lengthOfMonth;
        return total;
    }
   
    @Override
    public double getUsualSpent(LocalDate date1, LocalDate date2) {
        int lengthOfMonth = date.lengthOfMonth();
        double totalAmountSpent = this.getTotalSpent(date1, date2);
        double total = totalAmountSpent/lengthOfMonth;
        return total;
    }

    @Override
    public double getUsualSaved() {
        int lengthOfMonth = date.lengthOfMonth();
        double totalAmountSaved = this.getTotalSaved();
        double total = totalAmountSaved/lengthOfMonth;
        return total;
    }
   
    @Override
    public double getUsualSaved(LocalDate date1, LocalDate date2) {
        int lengthOfMonth = date.lengthOfMonth();
        double totalAmountSaved = this.getTotalSaved(date1, date2);
        double total = totalAmountSaved/lengthOfMonth;
        return total;
    }

    @Override
    public double getBalance() {
        double total = this.getTotalSpent() - this.getTotalSaved();
        return total;
    }

    @Override
    public double getBudgetTotal(){
        double budget = 0;
        try{
            String getBudget = "select user_budget from user_data where user_name = '" + Database.user.getUsername() + "';";
            PreparedStatement statement = connection.prepareStatement(getBudget);
            ResultSet results = statement.executeQuery();

            if(results.next()){
                budget = results.getDouble("user_budget");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        } 
        return budget;
    }

    @Override
    public void setBudgetTotal(double budget){
        try{
            Statement statement = connection.createStatement();
            String setBudget = "insert into user_data(user_budget) values(" + budget + ");";
            statement.executeUpdate(setBudget);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}