package com.unb.budgetmaster.data.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.unb.budgetmaster.data.implementation.DatabaseImpl;
import com.unb.budgetmaster.domain.abs.AnalysisABS;
import com.unb.budgetmaster.data.implementation.TransactionImpl;

public class AnalysisImpl implements AnalysisABS{
    DatabaseImpl data = new DatabaseImpl();
    TransactionImpl transaction = new TransactionImpl();
    Connection connection = data.connectDatabase();

    @Override
    public double getTotalSpent(String username) {
        double total = 0;
        try{
            String totalSpentQuery = "select spendings_overall_total from transaction_data where user_name = " + username + ";";
            PreparedStatement statement = connection.prepareStatement(totalSpentQuery);
            ResultSet results = statement.executeQuery(); 
            if(results.next()){
                total = results.getDouble("spendings_overall_total");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public double getTotalSaved(String username) {
        return 0;
    }

    @Override
    public double getTotalSpent(String date1, String date2, String username) {
        return 0;
    }

    @Override
    public double getTotalSaved(String date1, String date2, String username) {
        return 0;
    }

    @Override
    public double getUsualSpent(String username) {
        return 0;
    }
    @Override
    public double getUsualSpent(String date1, String date2, String username) {
     return 0;
    }

    @Override
    public double getBalance(String username) {
        return 0;
    }
    
}
