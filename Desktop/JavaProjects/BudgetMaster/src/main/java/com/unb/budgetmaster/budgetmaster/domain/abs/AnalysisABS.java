package com.unb.budgetmaster.budgetmaster.domain.abs;

import java.time.LocalDate;

public interface AnalysisABS {
    double getTotalSpent(String username);
    double getTotalSaved(String username);
    double getTotalSpent(String username, LocalDate startDate, LocalDate endDate);
    double getTotalSaved(String username, LocalDate startDate, LocalDate endDate);
    double getUsualSpent(String username);
    double getUsualSpent(String username, LocalDate startDate, LocalDate endDate);
    double getUsualSaved(String username);
    double getUsualSaved(String username, LocalDate startDate, LocalDate endDate);
    double getBalance(String username);
    double getBudgetTotal(String username);
    double getAmountSpentByCategory(String username, String category);
    double getAmountSpentByCategory(String username, String category, LocalDate startDate, LocalDate endDate);
    double getAmountSavedByCategory(String username, String category);
    double getAmountSavedByCategory(String username, String category, LocalDate startDate, LocalDate endDate);
}
