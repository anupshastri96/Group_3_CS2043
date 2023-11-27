package com.unb.budgetmaster.presentation;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;

import com.unb.budgetmaster.data.implementation.AnalysisImpl;
import com.unb.budgetmaster.data.implementation.TransactionImpl;
import com.unb.budgetmaster.domain.model.Transaction;

/* Spending page functions: 
 * 1. a function to check if you have categories or not. (create a helper function this helps in both the spendings and savings classes)
 * 2.  a function to add categories and show up a popup box for that.
 * 3. a function that checks the category with maximum budget and sets it as default/ shows it first on screen. (can also be made to a helper function)
 * call the helper function that shows the list of all the transactions say the helper function showTransactions(type:savings/spendings) 
 * with other related parameters you need for doing the UI job without redundant code.
 * 
 * In the bottom tab of this page, we will have some vector art related to analysing things that says: "Analyse your spendings" in the middle and art around it. 
This helps fill up the space on the page and makes the page more attractive to look at. Also helps people to regularly get reminder of analysing their 
transactions to keep up with their financial goals.
 */




public class Spending {
    private AnalysisImpl analysisImpl;
    private TransactionImpl transactionImpl;

    public void getContent(Label contentLabel, VBox contentContainer) {
        //Instantiate our implementations
        analysisImpl = new AnalysisImpl();
        transactionImpl = new TransactionImpl();
        // Modify contentLabel
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate currentDay = LocalDate.now();
        contentLabel.setText("Your Monthly Spending: " + (analysisImpl.getTotalSpent(firstDayOfMonth, currentDay)));

        // Create HBox for Title and Edit button
        HBox titleBox = new HBox(20);

        // Label for title
        Label titleLabel = new Label("Saving Goals");

        // Create Edit button
        Button editButton = new Button("Edit");

        // Add Title label and Edit button to HBox
        titleBox.getChildren().addAll(titleLabel, editButton);

        // Labels for columns
        Label categoryLabel = new Label("Category of Spending");
        Label percentageLabel = new Label("% of Spending");
        Label amountLabel = new Label("Amount Paid This Month");

        // Center align the column labels
        categoryLabel.setStyle("-fx-alignment: CENTER;");
        percentageLabel.setStyle("-fx-alignment: CENTER;");
        amountLabel.setStyle("-fx-alignment: CENTER;");

        // Create header row
        HBox headerRow = new HBox(10, categoryLabel, percentageLabel, amountLabel);

        // Add header row to contentContainer
        contentContainer.getChildren().addAll(titleBox, headerRow);

        // Create "Create New Category of Spending" button
        Button createNewCategoryButton = new Button("Create New Category of Spending");

        // Display Spendings
        VBox spendingsContainer = new VBox(10);
        updateSpendings(spendingsContainer, transactionImpl, createNewCategoryButton);

        // Add components to contentContainer
        contentContainer.getChildren().addAll(spendingsContainer, createNewCategoryButton);
    }

    private static void updateSpendings(VBox spendingsContainer, TransactionImpl transactionImpl, Button createNewCategoryButton) {
        spendingsContainer.getChildren().clear();

        // Get spendings from the provider
        ArrayList<Transaction> spendings = transactionImpl.getTransactions("spending", null, null);

        // // Display spendings
        // for (Transaction spending : spendings) {
        //     HBox spendingRow = createSpendingRow(spending);
        //     spendingsContainer.getChildren().add(spendingRow);
        // }

        // Update button visibility based on the number of spendings
        createNewCategoryButton.setDisable(spendings.size() >= 5);
    }


    // private static HBox createSpendingRow(Transaction spending) {
    //     Label categoryLabel = new Label(spending.getCategory());
    //     // Label percentageLabel = new Label(spending.getPercentage());
    //     // Label amountLabel = new Label(spending.getAmount());

    //     // Center align the data
    //     categoryLabel.setStyle("-fx-alignment: CENTER;");
    //     // percentageLabel.setStyle("-fx-alignment: CENTER;");
    //     // amountLabel.setStyle("-fx-alignment: CENTER;");

    //     return new HBox(10, categoryLabel, percentageLabel, amountLabel);
    // }
}