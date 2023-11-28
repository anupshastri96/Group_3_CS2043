package com.unb.budgetmaster.presentation;

import com.unb.budgetmaster.data.implementation.AnalysisImpl;
import com.unb.budgetmaster.data.implementation.TransactionImpl;
import com.unb.budgetmaster.domain.model.Transaction;
import java.util.Collections;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Savings {
    // Constants
    private final int TRANSACTIONS_DISPLAYED = 5;

    // Starting index for transactions
    private int startIndex = 0;
    
    // Implementation Instances
    private AnalysisImpl analysisImpl;
    private TransactionImpl transactionImpl;

    // Buttons
    Button nextButton;
    Button previousButton;

    public void getContent(Label contentLabel, VBox contentContainer) {
        // Instantiate implementations
        transactionImpl = new TransactionImpl();
        analysisImpl = new AnalysisImpl();

        // Get first day of month and current day
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate currentDay = LocalDate.now();

        // Modify contentLabel
        contentLabel.setText("Your Monthly Savings: " + analysisImpl.getTotalSaved(firstDayOfMonth, currentDay));

        // Create HBox for Title and Edit button
        HBox titleBox = new HBox(20);

        // Label for title
        Label titleLabel = new Label("Saving Goals");

        // Create Edit button
        Button editButton = new Button("Edit");

        // Add Title label and Edit button to HBox
        titleBox.getChildren().addAll(titleLabel, editButton);

        // Label for displaying transaction history
        Label historyLabel = new Label("List of Savings:");

        // Display transactions
        VBox transactionsContainer = new VBox(10);
        updateTransactions(transactionsContainer, nextButton, previousButton);

        // Create Previous and Next buttons
        previousButton = new Button("Previous");
        nextButton = new Button("Next");

        // Set button event handlers
        previousButton.setOnAction(event -> {
            startIndex = Math.max(startIndex - TRANSACTIONS_DISPLAYED, 0);
            updateTransactions(transactionsContainer, nextButton, previousButton);
        });

        nextButton.setOnAction(event -> {
            int totalTransactions = transactionImpl.getTransactions("Savings").size();
            startIndex = Math.min(startIndex + TRANSACTIONS_DISPLAYED, totalTransactions - 1);
            updateTransactions(transactionsContainer, nextButton, previousButton);
        });

        // Set initial button visibility
        updateButtonVisibility(nextButton, previousButton);

        // Create HBox for buttons
        HBox buttonsBox = new HBox(10, previousButton, nextButton);
        buttonsBox.setStyle("-fx-alignment: CENTER;");

        // Add components to contentContainer
        contentContainer.getChildren().addAll(titleBox, historyLabel, transactionsContainer, buttonsBox);
    }

    private void updateTransactions(VBox transactionsContainer, Button nextButton, Button prevButton) {
        // Clear the transactions currently being displayed
        transactionsContainer.getChildren().clear();

        // Get transactions from the implementation
        ArrayList<Transaction> transactionsList = transactionImpl.getTransactions("Savings");

        // Inverse order of transactions so that first one is the most recent
        Collections.reverse(transactionsList);

        // Display transactions based on the current startIndex
        for (int i = startIndex; i < startIndex + TRANSACTIONS_DISPLAYED && i < transactionsList.size(); i++) {
            Label transactionLabel = new Label(transactionsList.get(i).toString());
            transactionsContainer.getChildren().add(transactionLabel);
        }

        // Update button visibility based on the startIndex and total transactions
        updateButtonVisibility(nextButton, prevButton);
        return;
    }

    private void updateButtonVisibility(Button nextButton, Button previousButton) {
        ArrayList<Transaction> transactionsList = transactionImpl.getTransactions("Savings");
        int totalTransactions = transactionsList.size();

        // Show/hide Next button based on remaining transactions
        nextButton.setDisable(startIndex + TRANSACTIONS_DISPLAYED >= totalTransactions);

        // Show/hide Previous button based on the current startIndex
        previousButton.setDisable(startIndex <= 0);
        return;
    }
}
// End of Savings class