package com.unb.budgetmaster.domain.abs;
import java.time.LocalDate;

public interface AnalysisABS {
    double getTotalSpent(String username);
    double getTotalSaved(String username);
    double getTotalSpent(LocalDate date1, LocalDate date2, String username);
    double getTotalSaved(LocalDate date1, LocalDate date2, String username);
    double getUsualSpent(String username);
    double getUsualSpent(LocalDate date1, LocalDate date2, String username);
    double getBalance(String username);
}
