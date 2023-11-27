package com.unb.budgetmaster.domain.abs;
import java.time.LocalDate;
import java.util.ArrayList;

import com.unb.budgetmaster.domain.model.Transaction;
import com.unb.budgetmaster.domain.model.Category;

public interface TransactionABS {
    Transaction getTransactionDetail(int id);
    void setTransactionDetails(Transaction transaction);
    void addTransaction(LocalDate date, int id, double amount, String type, String category);
    void deleteTransaction(Transaction transaction);
    ArrayList<Transaction> getTransactionsByCategory(String type, Category category);
    ArrayList<Transaction> getTransactions(String type);

    int getLastTransactionID();

    ArrayList<Transaction> getTransactions();
}
