package com.unb.budgetmaster.data.implementation;

import java.util.ArrayList;

import com.unb.budgetmaster.domain.abs.TransactionABS;
import com.unb.budgetmaster.domain.model.Transaction;
import com.unb.budgetmaster.domain.model.Category;

public class TransactionImpl implements TransactionABS{

    @Override
    public Transaction getTransactionDetail(int id) {
        return null;
    }

    @Override
    public void setTransactionDetails(Transaction transaction) {
    
    }

    @Override
    public void addTransaction(Category category, String type) {
      
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
    
    }

    @Override
    public ArrayList<Transaction> getTransactions(String type, Category category, String sort) {
        return null;
    }

    public String toString(Transaction transaction) {
        return "Test String";
    }
    
}
