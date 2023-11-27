package com.unb.budgetmaster.budgetmaster.domain.abs;

import java.util.ArrayList;

import com.unb.budgetmaster.budgetmaster.domain.model.Category;
public interface CategoryABS {
    ArrayList<Category> getCategories(String username, String type);
    void addCategory(Category category);
    void deleteCategory(Category category);
    
}
