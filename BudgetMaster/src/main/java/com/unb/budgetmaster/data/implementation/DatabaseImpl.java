package com.unb.budgetmaster.data.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.unb.budgetmaster.domain.abs.DatabaseABS;

public class DatabaseImpl implements DatabaseABS {

    final String URL = "jdbc:mysql://ipaddress/database?useSSL=false&allowPublicKeyRetrieval=true&enabledTLSProtocol=TLSv1.2";
    final String USER = "";
    final String PASSWORD = "";
    Connection con = null;

    @Override
    public Connection connectDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
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
