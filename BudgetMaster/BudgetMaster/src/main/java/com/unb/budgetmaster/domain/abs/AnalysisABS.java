package com.unb.budgetmaster.domain.abs;
import java.time.LocalDate;

public interface AnalysisABS {
    double getTotalSpent();
    double getTotalSaved();
    double getTotalSpent(LocalDate date1, LocalDate date2);
    double getTotalSaved(LocalDate date1, LocalDate date2);
    double getUsualSpent();
    double getUsualSpent(LocalDate date1, LocalDate date2);
    double getBalance();
}
