package com.unb.budgetmaster.data.implementation;

import java.util.ArrayList;

import com.unb.budgetmaster.domain.abs.CategoryABS;
import com.unb.budgetmaster.domain.model.Category;

public class CategoryImpl implements CategoryABS{

    @Override
    public ArrayList<Category> getCategories(String type) {
        return null;
    }

    @Override
    public void addCategory(Category category) {
        String categoryName = category.getName();
        
    }

    @Override
    public void deleteCategory(Category category) {
    
    }
    
}
