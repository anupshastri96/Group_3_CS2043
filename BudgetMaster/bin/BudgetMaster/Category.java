package com.unb.budgetmaster.budgetmaster.domain.model;

public class Category {
    private String name ;
    private double budget;
    private String type;

    public Category(String name, double budget, String type) {
        this.name = name;
        this.budget = budget;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public String getType() {
        return type;
    }
}
