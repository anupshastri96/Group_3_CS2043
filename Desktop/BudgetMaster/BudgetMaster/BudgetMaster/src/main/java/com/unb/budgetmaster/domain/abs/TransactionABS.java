package com.unb.budgetmaster.domain.abs;
import java.util.ArrayList;

import com.unb.budgetmaster.domain.model.Transaction;
import com.unb.budgetmaster.domain.model.Category;

public interface TransactionABS {
    Transaction getTransactionDetail(int id);
    void setTransactionDetails(Transaction transaction);
    void addTransaction(String date, int id, double amount, String type, String category);
    void deleteTransaction(Transaction transaction);
    ArrayList<Transaction> getTransactions(String type, Category category, String sort);
}
