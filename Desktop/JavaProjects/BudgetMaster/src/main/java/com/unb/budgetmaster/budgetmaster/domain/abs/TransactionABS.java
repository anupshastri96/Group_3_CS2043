package com.unb.budgetmaster.budgetmaster.domain.abs;
import java.time.LocalDate;
import java.util.ArrayList;

import com.unb.budgetmaster.budgetmaster.domain.model.Category;
import com.unb.budgetmaster.budgetmaster.domain.model.Transaction;

public interface TransactionABS {
    Transaction getTransactionDetails(int id);
    void setTransactionDetails(Transaction transaction);
    void addTransaction(String username, LocalDate date, double amount, String payee, String type, String category);
    void deleteTransaction(String username, int id);
    ArrayList<Transaction> getTransactions(String username, String type, Category category, String sort);
    ArrayList<Transaction> getTransactions(String username);
    public ArrayList<Transaction> getTransactions(String username, String type);
}
