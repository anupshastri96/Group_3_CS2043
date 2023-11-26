package com.unb.budgetmaster.domain.model;

public class Transaction {
    String date ;
    int id;
    double amount ;
    String payee; 
    String type; 
    //Category category;
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

    public String getPayee() {
        return this.payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
}
