package com.unb.budgetmaster.data.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.unb.budgetmaster.domain.abs.CategoryABS;
import com.unb.budgetmaster.domain.model.Category;

public class CategoryImpl implements CategoryABS{
    Connection connection = Database.getDatabase();
    PreparedStatement statement;
    String query = "";
    @Override
    public ArrayList<Category> getCategories(String type) {
        ArrayList<Category> allCategories = new ArrayList<Category>();
        Category category;
        String categoryName;
        double budget = 0;

        try{
            if(type.equals("Spendings")){
                query = "select category_name, spendings_per_category from category_data natural join transaction_data where transaction_name = " + type + ";";
            }
            else if(type.equals("Savings")){
                query = "select category_name, savings_goal from category_data natural join transaction_data where transaction_name = " + type + ";";
            }

            statement = connection.prepareStatement(query);
            ResultSet results = statement.executeQuery();

            if(results.next()){
                categoryName = results.getString("category_name");
                if(type.equals("Savings")){
                    budget = results.getDouble("savings_goal");
                }
                else if(type.equals("Spendings")){
                    budget = results.getDouble("spendings_per_category");
                }
                category = new Category(categoryName, budget, type);
                allCategories.add(category);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return allCategories;
    }

    @Override
    public Boolean checkCategoryExists(String category) {
        query = "select " + category + " from category_data";
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet results = null;
        try {
            results = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results != null;
    }


    @Override
    public void addCategory(Category category) {
        String name = category.getName();
        String type = category.getType();

        try{
            Statement statement1 = connection.createStatement();
            if(type.equals("Spendings")){
                query =  "insert into category_data(category_name) values('" + name + "') where transaction_type = 'Spendings' and user_name = '" + Database.user.getUsername() + "';";
            }
            else if(type.equals("Savings")){
                query =  "insert into category_data(category_name) values('" + name + "') where transaction_type = 'Savings' and user_name = '" + Database.user.getUsername() + "';";
            }
            statement1.executeUpdate(query);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(Category category) {
        String categoryName = category.getName();
        try{
            Statement statement = connection.createStatement();
            String deleteCategory= "delete from category_data where category_name = '" + categoryName + "' and user_name = '" +  Database.user.getUsername() + "';";
            statement.executeUpdate(deleteCategory);
        }
         catch(SQLException e){
            e.printStackTrace();
        }
    }
}