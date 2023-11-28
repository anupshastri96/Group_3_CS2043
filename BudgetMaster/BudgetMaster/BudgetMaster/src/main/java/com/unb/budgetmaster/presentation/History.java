package com.unb.budgetmaster.budgetmaster.presentation;

import com.unb.budgetmaster.budgetmaster.domain.model.Transaction;
import com.unb.budgetmaster.budgetmaster.domain.implementation.TransactionImpl;
import com.unb.budgetmaster.budgetmaster.domain.implementation.AnalysisImpl;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Collections;

public class History {
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

    public void getContent(Label contentLabel, VBox contentContainer, ArrayList<String> loginInformation) {
        // Instantiate implementations
        transactionImpl = new TransactionImpl();
        analysisImpl = new AnalysisImpl();

        // Get username from login information
        String username = loginInformation.get(3);

        // Modify contentLabel
        updateBalanceLabel(contentLabel, username);
    
        // Create HBox for Title and Edit button
        HBox titleBox = new HBox(20);
    
        // Label for title
        Label titleLabel = new Label("History");
    
        // Create Edit button
        Button editButton = new Button("Edit");
    
        // Add Title label and Edit button to HBox
        titleBox.getChildren().addAll(titleLabel, editButton);
    
        // Label for displaying transaction history
        Label historyLabel = new Label("List History of Transactions:");
    
        // Display transactions
        VBox transactionsContainer = new VBox(10);
        updateTransactions(transactionsContainer, transactionImpl, nextButton, previousButton, username);
    
        // Create Previous and Next buttons
        previousButton = new Button("Previous");
        nextButton = new Button("Next");
    
        // Set button event handlers
        previousButton.setOnAction(event -> {
            startIndex = Math.max(startIndex - TRANSACTIONS_DISPLAYED, 0);
            updateTransactions(transactionsContainer, transactionImpl, nextButton, previousButton, username);
        });
    
        nextButton.setOnAction(event -> {
            int totalTransactions = transactionImpl.getTransactions(username).size();
            startIndex = Math.min(startIndex + TRANSACTIONS_DISPLAYED, totalTransactions - 1);
            updateTransactions(transactionsContainer, transactionImpl, nextButton, previousButton, username);
        });
    
        // Set initial button visibility
        updateButtonVisibility(transactionImpl, nextButton, previousButton, username);
    
        // Create HBox for buttons
        HBox buttonsBox = new HBox(10, previousButton, nextButton);
        buttonsBox.setStyle("-fx-alignment: CENTER;");
    
        // Add components to contentContainer
        contentContainer.getChildren().addAll(titleBox, historyLabel, transactionsContainer, buttonsBox);
    }

    // Add this method to update the balance label
    private void updateBalanceLabel(Label contentLabel, String username) {
        contentLabel.setText("Current Balance: " + analysisImpl.getBalance(username));
    }

    private void updateTransactions(VBox transactionsContainer, TransactionImpl transactionImpl, Button nextButton, Button prevButton, String username) {
        // Clear the transactions currently being displayed
        transactionsContainer.getChildren().clear();
    
        // Get transactions from the implementation
        ArrayList<Transaction> transactionsList = transactionImpl.getTransactions(username);
    
        // Inverse order of transactions so that the first one is the most recent
        if(transactionsList != null)
            Collections.reverse(transactionsList);
    
        // Display transactions based on the current startIndex
        for (int i = startIndex; i < startIndex + TRANSACTIONS_DISPLAYED && i < transactionsList.size(); i++) {
            Label transactionLabel = new Label(transactionsList.get(i).toString());
            transactionsContainer.getChildren().add(transactionLabel);
        }
    
        // Update button visibility based on the startIndex and total transactions
        updateButtonVisibility(transactionImpl, nextButton, prevButton, username);
    
        // Ensure that historyLabel is always present
        if (transactionsContainer.getChildren().isEmpty()) {
            Label historyLabel = new Label("No transactions available.");
            transactionsContainer.getChildren().add(historyLabel);
        }
    }

    private void updateButtonVisibility(TransactionImpl transactionImpl, Button nextButton, Button previousButton, String username) {
        ArrayList<Transaction> transactionsList = transactionImpl.getTransactions(username);
        int totalTransactions = transactionsList.size();

        // Show/hide Next button based on remaining transactions
        nextButton.setDisable(startIndex + TRANSACTIONS_DISPLAYED >= totalTransactions);

        // Show/hide Previous button based on the current startIndex
        previousButton.setDisable(startIndex <= 0);
    }
}
// End of History class