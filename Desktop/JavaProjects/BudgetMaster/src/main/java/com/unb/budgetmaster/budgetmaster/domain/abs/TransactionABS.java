package com.unb.budgetmaster.budgetmaster.domain.abs;
import java.util.ArrayList;

import com.unb.budgetmaster.budgetmaster.domain.model.Category;
import com.unb.budgetmaster.budgetmaster.domain.model.Transaction;

public interface TransactionABS {
    Transaction getTransactionDetail(int id);
    void setTransactionDetails(Transaction transaction);
    void addTransaction(Category category, String type);
    void deleteTransaction(Transaction transaction);
    ArrayList<Transaction> getTransactions(String type, Category category, String sort);
}
