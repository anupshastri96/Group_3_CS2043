package com.unb.budgetmaster.domain.abs;
import java.time.LocalDate;
import java.util.ArrayList;

import com.unb.budgetmaster.domain.model.Transaction;
import com.unb.budgetmaster.domain.model.Category;

public interface TransactionABS {
    Transaction getTransactionDetail(int id);
    void setTransactionDetails(Transaction transaction);
    void addTransaction(LocalDate date, double amount, String payee, String type, String category);
    void deleteTransaction(Transaction transaction);
    ArrayList<Transaction> getTransactions(String type, Category category);
    ArrayList<Transaction> getTransactions(String type);
    ArrayList<Transaction> getTransactions(Category category);
    ArrayList<Transaction> getTransactions();
    int getLastTransactionID();
}
// End of TransactionABS Interface