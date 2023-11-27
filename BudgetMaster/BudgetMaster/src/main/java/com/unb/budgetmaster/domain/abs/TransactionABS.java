package com.unb.budgetmaster.domain.abs;
import java.util.ArrayList;

import com.unb.budgetmaster.domain.model.Transaction;
import com.unb.budgetmaster.domain.model.Category;

public interface TransactionABS {
    Transaction getTransactionDetail(int id, String username);
    void setTransactionDetails(Transaction transaction, String username);
    void addTransaction(String date, int id, double amount, String type, String category, String username);
    void deleteTransaction(Transaction transaction, String username);
    ArrayList<Transaction> getTransactions(String type, Category category, String sort, String username);
}
