package com.unb.budgetmaster.domain.abs;

public interface AnalysisABS {
    double getTotalSpent(String username);
    double getTotalSaved(String username);
    double getTotalSpent(String date1, String date2, String username);
    double getTotalSaved(String date1, String date2, String username);
    double getUsualSpent(String username);
    double getUsualSpent(String date1, String date2, String username);
    double getBalance(String username);
}
