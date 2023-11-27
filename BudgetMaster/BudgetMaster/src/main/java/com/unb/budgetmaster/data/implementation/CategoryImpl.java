package com.unb.budgetmaster.data.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.unb.budgetmaster.domain.abs.CategoryABS;
import com.unb.budgetmaster.domain.model.Category;

public class CategoryImpl implements CategoryABS{
    DatabaseImpl data = new DatabaseImpl();
    Connection connection = data.getDatabase();
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
        if(category.getType().equals("Spendings")){
            query =  "insert into category_data(category_name) "
        }else{
            query =  "insert into category_data(category_name,"
        }
    }

    @Override
    public void deleteCategory(Category category) {

    }
