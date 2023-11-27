package com.unb.budgetmaster.data.implementation;

import com.unb.budgetmaster.domain.abs.AnalysisABS;

import java.time.LocalDate;

public class AnalysisImpl implements AnalysisABS{

    @Override
    public double getTotalSpent(LocalDate firstDayOfMonth, LocalDate currentDay) {
        return 0;
    }

    @Override
    public double getTotalSaved(LocalDate firstDayOfMonth, LocalDate currentDay) {
        return 0;
    }

    @Override
    public double getUsualSpent() {
        return 0;
    }
    @Override
    public double getUsualSpent(LocalDate date1, LocalDate date2) {
     return 0;
    }

    @Override
    public double getUsualSaved() {
        return 0;
    }


    @Override
    public double getBalance() {
        return 0;
    }

    @Override
    public double getAmountSpentByCategory(String category, LocalDate date1, LocalDate date2) {
        return 0;
    }

    @Override
    public double getBudgetTotal() {
        return 0;
    }

}
