package com.unb.budgetmaster.domain.abs;
import java.util.ArrayList;

import com.unb.budgetmaster.domain.model.Transaction;
import com.unb.budgetmaster.domain.model.Category;

public interface TransactionABS {
    Transaction getTransactionDetail(int id);
    void setTransactionDetails(Transaction transaction);
    void addTransaction(Category category, String type);
    void deleteTransaction(Transaction transaction);
    ArrayList<Transaction> getTransactions(String type, Category category, String sort);
}
