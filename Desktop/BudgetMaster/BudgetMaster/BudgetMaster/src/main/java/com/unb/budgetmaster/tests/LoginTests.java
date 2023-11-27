// package com.unb.budgetmaster.tests;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.Statement;

// import org.junit.Jupiter;
// import com.unb.budgetmaster.domain.helper.ConnectionManager;
// import com.unb.budgetmaster.data.implementation.DatabaseImpl;

// public class LoginTests {
//     DatabaseImpl databaseImpl = new DatabaseImpl();
//     Connection con = databaseImpl.connectDatabase();
//     @Test
//     public void checkUsernameExists(String username){
//         ConnectionManager.openConnection();
//         String query = "select user_name" +
//                         "from user_data" +
//                         "where user_name = "+username+";";
//         Statement statement = con.createStatement();
//         statement.execute(query);
        
//     }
// }
