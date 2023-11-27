package com.unb.budgetmaster.data.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.unb.budgetmaster.domain.abs.CategoryABS;
import com.unb.budgetmaster.domain.model.Category;
import com.unb.budgetmaster.data.implementation.DatabaseImpl;

public class CategoryImpl implements CategoryABS{
    DatabaseImpl data = new DatabaseImpl();
    Connection connection = data.connectDatabase();

    @Override
    public ArrayList<Category> getCategories(String type) {
        ArrayList<Category> allCategories = new ArrayList<Category>();
        Category category;
        int categoryID = 0;
        String categoryName = "";
        double budget = 0;
        String categoryQuery = "";

        try{
            if(type.equals("Spendings")){
                categoryQuery = "select category_id, category_name, spendings_per_category from category_data natural join transaction_data where transaction_name = " + type + ";";
            }
            else if(type.equals("Savings")){
                categoryQuery = "select category_id, category_name, savings_goal from category_data natural join transaction_data where transaction_name = " + type + ";";
            }
            
            PreparedStatement statement = connection.prepareStatement(categoryQuery);
            ResultSet results = statement.executeQuery();

            if(results.next()){
                categoryID = results.getInt("category_id");
                categoryName = results.getString("category_name");
                if(type.equals("Savings")){
                     budget = results.getDouble("savings_goal");
                }
                else if(type.equals("Spendings")){
                    budget = results.getDouble("spendings_per_category");
                }
                //category = new Category(categoryName, budget, categoryID)
               
            }
        }
        catch(SQLException e){
                e.printStackTrace();
        }
        return allCategories;
    }

    @Override
    public void addCategory(Category category) {
        String categoryName = category.getName();
        
    }

    @Override
    public void deleteCategory(Category category) {
    
    }
    
}
