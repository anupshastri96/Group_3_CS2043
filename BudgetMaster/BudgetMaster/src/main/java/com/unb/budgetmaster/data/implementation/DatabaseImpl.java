package com.unb.budgetmaster.data.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.unb.budgetmaster.domain.abs.DatabaseABS;
import com.unb.budgetmaster.domain.constant.Constants;

public class DatabaseImpl implements DatabaseABS {
    Connection con = null;

    @Override
    public Connection connectDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(Constants.URL,Constants.USER,Constants.PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    @Override
    public void disconnectDatabase() {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println("Could not close connection " + e.getMessage());
        }
    }

}
