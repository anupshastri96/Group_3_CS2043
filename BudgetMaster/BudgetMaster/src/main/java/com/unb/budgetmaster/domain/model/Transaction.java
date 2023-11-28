package com.unb.budgetmaster.domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    LocalDate date;
    int id;
    double amount;
    String payee; 
    String type; 
    String category;

    public Transaction(LocalDate date, int id, double amount, String payee, String type, String category) {
        this.date = date;
        this.id = id;
        this.amount = amount;
        this.payee = payee;
        this.type = type;
        this.category = category;
    }

    public LocalDate getDate() {
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String retString = "Date: " + date.format(formatter) + ", ID: " + id + ", Amount: " + amount + ", Payee:" + payee + ", Type: " + type + ", Category: " + category;

        return retString;
    }
}
