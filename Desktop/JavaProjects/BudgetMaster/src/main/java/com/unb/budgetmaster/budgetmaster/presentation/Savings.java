package com.unb.budgetmaster.budgetmaster.presentation;

import com.unb.budgetmaster.budgetmaster.domain.implementation.AnalysisImpl;
import com.unb.budgetmaster.budgetmaster.domain.implementation.TransactionImpl;
import com.unb.budgetmaster.budgetmaster.domain.model.Transaction;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Savings {
    // Implementation Instances
    private AnalysisImpl analysisImpl;
    private TransactionImpl transactionImpl;

    public void getContent(Label contentLabel, VBox contentContainer) {
        // Instantiate implementations
        transactionImpl = new TransactionImpl();
        analysisImpl = new AnalysisImpl();

        // Modify contentLabel
        contentLabel.setText("Your Monthly Savings: " + String.valueOf(analysisImpl.getTotalSaved()));

        // Create HBox for Title and Edit button
        HBox titleBox = new HBox(20);

        // Label for title
        Label titleLabel = new Label("Saving Goals");

        // Create Edit button
        Button editButton = new Button("Edit");

        // Add Title label and Edit button to HBox
        titleBox.getChildren().addAll(titleLabel, editButton);

        // Create components
        Label goalLabel = new Label("Goal");
        Label percentageLabel = new Label("% to Achieving Goal");
        Label currentAmountLabel = new Label("Current Amount Invested to Goal");

        // Center align the titles
        goalLabel.setStyle("-fx-alignment: CENTER;");
        percentageLabel.setStyle("-fx-alignment: CENTER;");
        currentAmountLabel.setStyle("-fx-alignment: CENTER;");

        // Create header row
        HBox headerRow = new HBox(10, goalLabel, percentageLabel, currentAmountLabel);

        // Add a line separator
        Label separator = new Label("------------------------------------------------------");
        separator.setStyle("-fx-alignment: CENTER;");

        // Add header row and separator to contentContainer
        contentContainer.getChildren().addAll(titleBox, headerRow, separator);

        // Create array list of Goal objects
        ArrayList<Transaction> transactions = transactionImpl.getTransactions("Savings", null, null);

        // Display up to 5 goals
        for (int i = 0; i < Math.min(transactions.size(), 5); i++) {
            Transaction goal = transactions.get(i);
            HBox goalRow = createGoalRow(goal);
            contentContainer.getChildren().add(goalRow);
        }

        // Add "Create New Goal" button
        Button createNewGoalButton = new Button("Create New Goal");
        contentContainer.getChildren().add(createNewGoalButton);
    }

    private static HBox createGoalRow(Transaction goal) {
        Label goalLabel = new Label(goal.get());
        Label percentageLabel = new Label(goal.getPercentage());
        Label currentAmountLabel = new Label(goal.getCurrentAmount());

        // Center align the data
        goalLabel.setStyle("-fx-alignment: CENTER;");
        percentageLabel.setStyle("-fx-alignment: CENTER;");
        currentAmountLabel.setStyle("-fx-alignment: CENTER;");

        return new HBox(10, goalLabel, percentageLabel, currentAmountLabel);
    }
}
