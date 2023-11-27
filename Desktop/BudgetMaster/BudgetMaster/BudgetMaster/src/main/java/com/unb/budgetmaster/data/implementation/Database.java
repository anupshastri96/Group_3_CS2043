package com.unb.budgetmaster.data.implementation;

import java.sql.Connection;
import java.sql.DriverManager;

import com.unb.budgetmaster.domain.constant.Constants;
import com.unb.budgetmaster.domain.model.User;

public class Database {
    static Connection con = null;
    public static User user = null;
    public static Connection getDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(Constants.URL,Constants.USER,Constants.PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void disconnectDatabase() {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println("Could not close connection " + e.getMessage());
        }
    }

}
