package com.unb.budgetmaster.budgetmaster.domain.model;

import com.unb.budgetmaster.budgetmaster.presentation.Category;

public class Transaction {
    String date;
    int id;
    double amount;
    String payee; 
    String type; 
    //Category category;
    String category;

    public Transaction(String date, int id, double amount, String payee, String type, String category) {
        this.date = date;
        this.id = id;
        this.amount = amount;
        this.payee = payee;
        this.type = type;
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public int getID() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getPayee() {
        return payee;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }
}


