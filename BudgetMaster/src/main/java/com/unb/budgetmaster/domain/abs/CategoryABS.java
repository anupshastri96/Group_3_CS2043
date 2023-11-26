package com.unb.budgetmaster.domain.abs;

import java.util.ArrayList;

import com.unb.budgetmaster.domain.model.Category;
public interface CategoryABS {
    ArrayList<Category> getCategories(String type);
    void addCategory(Category category);
    void deleteCategory(Category category);
    
}
