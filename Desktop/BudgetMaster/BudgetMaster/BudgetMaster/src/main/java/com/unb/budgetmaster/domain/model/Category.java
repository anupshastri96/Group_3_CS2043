package com.unb.budgetmaster.domain.model;

public class Category {
    String name ;
    double budget;
    String type;

    public Category(String name, double budget, String type) {
        this.name = name;
        this.budget = budget;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
