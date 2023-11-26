package com.unb.budgetmaster.domain.model;

public class Transaction {
    String date ;
    static int id;
    double amount ;
    String transacitonType; 
    String category;

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return this.transacitonType;
    }

    public void setType(String type) {
        this.transactionType = type;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
}
