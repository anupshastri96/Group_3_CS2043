package com.unb.budgetmaster.budgetmaster.domain.abs;

public interface AnalysisABS {
    double getTotalSpent();
    double getTotalSaved();
    double getTotalSpent(String date1, String date2);
    double getTotalSaved(String date1, String date2);
    double getUsualSpent();
    double getUsualSpent(String date1, String date2);
    double getBalance();
}
