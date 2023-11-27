package com.unb.budgetmaster.presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import com.unb.budgetmaster.data.implementation.AnalysisImpl;
import com.unb.budgetmaster.data.implementation.TransactionImpl;
import com.unb.budgetmaster.domain.model.Transaction;

public class Spending {
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

    // VBoxes
    VBox transactionsContainer;

    public void getContent(Label contentLabel, VBox contentContainer) {
        // Instantiate our implementations
        transactionImpl = new TransactionImpl();
        analysisImpl = new AnalysisImpl();

        // Get first day of month and current day
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate currentDay = LocalDate.now();

        // Modify contentLabel
        contentLabel.setText("Your Monthly Spending: " + analysisImpl.getTotalSpent(firstDayOfMonth, currentDay));

        // Create HBox for Title and Edit button
        HBox titleBox = new HBox(20);

        // Label for title
        Label titleLabel = new Label("Saving Goals");

        // Create Edit button
        Button editButton = new Button("Add Spending");
        editButton.setOnAction(event -> editSpending());

        // Add Title label and Edit button to HBox
        titleBox.getChildren().addAll(titleLabel, editButton);

        // Label for displaying transaction history
        Label historyLabel = new Label("List of Spendings:");

        // Display transactions
        transactionsContainer = new VBox(10);
        updateTransactions(transactionsContainer, transactionImpl, nextButton, previousButton);

        // Create Previous and Next buttons
        previousButton = new Button("Previous");
        nextButton = new Button("Next");

        // Set button event handlers
        previousButton.setOnAction(event -> {
            startIndex = Math.max(startIndex - TRANSACTIONS_DISPLAYED, 0);
            updateTransactions(transactionsContainer, transactionImpl, nextButton, previousButton);
        });

        nextButton.setOnAction(event -> {
            int totalTransactions = transactionImpl.getTransactions("Spendings").size();
            startIndex = Math.min(startIndex + TRANSACTIONS_DISPLAYED, totalTransactions - 1);
            updateTransactions(transactionsContainer, transactionImpl, nextButton, previousButton);
        });

        // Set initial button visibility
        updateButtonVisibility(transactionImpl, nextButton, previousButton);

        // Create HBox for buttons
        HBox buttonsBox = new HBox(10, previousButton, nextButton);
        buttonsBox.setStyle("-fx-alignment: CENTER;");

        // Add components to contentContainer
        contentContainer.getChildren().addAll(titleBox, historyLabel, transactionsContainer, buttonsBox);
    }

    private void updateTransactions(VBox transactionsContainer, TransactionImpl transactionImpl, Button nextButton, Button prevButton) {
        // Clear the transactions currently being displayed
        transactionsContainer.getChildren().clear();

        // Get transactions from the implementation
        ArrayList<Transaction> transactionsList = transactionImpl.getTransactions("Spendings");

        // Inverse order of transactions so that first one is the most recent
        Collections.reverse(transactionsList);

        // Display transactions based on the current startIndex
        for (int i = startIndex; i < startIndex + TRANSACTIONS_DISPLAYED && i < transactionsList.size(); i++) {
            Label transactionLabel = new Label(transactionsList.get(i).toString());
            transactionsContainer.getChildren().add(transactionLabel);
        }

        // Update button visibility based on the startIndex and total transactions
        updateButtonVisibility(transactionImpl, nextButton, prevButton);
    }

    private void updateButtonVisibility(TransactionImpl transactionImpl, Button nextButton, Button previousButton) {
        ArrayList<Transaction> transactionsList = transactionImpl.getTransactions("Spendings");
        int totalTransactions = transactionsList.size();

        // Show/hide Next button based on remaining transactions
        nextButton.setDisable(startIndex + TRANSACTIONS_DISPLAYED >= totalTransactions);

        // Show/hide Previous button based on the current startIndex
        previousButton.setDisable(startIndex <= 0);
    }

    private void editSpending() {
        // Create a new stage for the popup
        Stage popupStage = new Stage();
        popupStage.setTitle("Add Spending");
        popupStage.initModality(Modality.APPLICATION_MODAL);

        // Create labels and text fields for the spending details
        Label titleLabel = new Label("Add Spending");
        Label payeeLabel = new Label("Payee");
        Label amountLabel = new Label("Amount");
        Label dateLabel = new Label("Date");
        Label categoryLabel = new Label("Category");

        TextField payeeField = new TextField();
        TextField amountField = new TextField();
        TextField dateField = new TextField();
        dateField.setPromptText("DD/MM/YYYY");
        TextField categoryField = new TextField();
        categoryField.setPromptText("Leave blank if none");

        // Create buttons for submitting or canceling the edit
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");

        // Set button event handlers
        backButton.setOnAction(event -> popupStage.close());
        submitButton.setOnAction(event -> {
            // Parse date from the text field
            LocalDate date;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(dateField.getText(), formatter);
            } catch (Exception e) {
                // Handle invalid date format
                System.out.println("Invalid date format");
                return;
            }

            // Call addTransaction method with the entered values
            transactionImpl.addTransaction(date, (transactionImpl.getLastTransactionID()+1),Double.parseDouble(amountField.getText()), "Spendings", categoryField.getText());

            // Update transactions and close the popup
            updateTransactions(transactionsContainer, transactionImpl, nextButton, previousButton);
            popupStage.close();
        });

        // Create layout for the popup
        VBox popupLayout = new VBox(10);
        popupLayout.getChildren().addAll(
                titleLabel,
                payeeLabel, payeeField,
                amountLabel, amountField,
                dateLabel, dateField,
                categoryLabel, categoryField,
                new HBox(10, backButton, submitButton)
        );

        // Set style for the labels
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Create scene for the popup
        Scene popupScene = new Scene(popupLayout, 300, 300);
        popupStage.setScene(popupScene);

        // Show the popup
        popupStage.showAndWait();
    }
}
// End of Spending class