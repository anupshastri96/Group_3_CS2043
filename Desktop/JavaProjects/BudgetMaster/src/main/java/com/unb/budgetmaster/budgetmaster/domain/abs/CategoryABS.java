package com.unb.budgetmaster.budgetmaster.domain.abs;

import java.util.ArrayList;

import com.unb.budgetmaster.budgetmaster.presentation.Category;
public interface CategoryABS {
    ArrayList<Category> getCategories(String type);
    void addCategory(Category category);
    void deleteCategory(Category category);
    
}
