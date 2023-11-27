package com.unb.budgetmaster.domain.abs;

import java.time.LocalDate;

public interface AnalysisABS {
    double getTotalSpent(LocalDate firstDayOfMonth, LocalDate currentDay);
    double getTotalSaved(LocalDate firstDayOfMonth, LocalDate currentDay);
    double getUsualSpent();
    double getUsualSpent(LocalDate date1, LocalDate date2);
    double getUsualSaved();
    double getBalance();

    double getAmountSpentByCategory(String category, LocalDate date1, LocalDate date2);
    double getBudgetTotal();
}
