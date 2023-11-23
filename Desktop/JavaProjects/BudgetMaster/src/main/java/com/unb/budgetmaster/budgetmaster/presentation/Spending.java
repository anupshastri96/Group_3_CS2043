package com.unb.budgetmaster.budgetmaster.presentation;

import com.unb.budgetmaster.budgetmaster.domain.implementation.AnalysisImpl;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class Spending {
    private AnalysisImpl analysisImpl;
    private SpendingImpl spendingImpl;

    public void getContent(Label contentLabel, VBox contentContainer) {
        //Instantiate our implementations
        analysisImpl = new AnalysisImpl();
        spendingImpl = new SpendingImpl();

        // Modify contentLabel
        contentLabel.setText("Your Monthly Spending: " + String.valueOf(analysisImpl.getTotalSpent()));

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
        updateSpendings(spendingsContainer, spendingImpl, createNewCategoryButton);

        // Add components to contentContainer
        contentContainer.getChildren().addAll(spendingsContainer, createNewCategoryButton);
    }

    private static void updateSpendings(VBox spendingsContainer, SpendingImpl spendingImpl, Button createNewCategoryButton) {
        spendingsContainer.getChildren().clear();

        // Get spendings from the provider
        ArrayList<Spending> spendings = spendingImpl.getSpending();

        // Display spendings
        for (Spending spending : spendings) {
            HBox spendingRow = createSpendingRow(spending);
            spendingsContainer.getChildren().add(spendingRow);
        }

        // Update button visibility based on the number of spendings
        createNewCategoryButton.setDisable(spendings.size() >= 5);
    }

    private static HBox createSpendingRow(Spending spending) {
        Label categoryLabel = new Label(spending.getCategory());
        Label percentageLabel = new Label(spending.getPercentage());
        Label amountLabel = new Label(spending.getAmount());

        // Center align the data
        categoryLabel.setStyle("-fx-alignment: CENTER;");
        percentageLabel.setStyle("-fx-alignment: CENTER;");
        amountLabel.setStyle("-fx-alignment: CENTER;");

        return new HBox(10, categoryLabel, percentageLabel, amountLabel);
    }
}