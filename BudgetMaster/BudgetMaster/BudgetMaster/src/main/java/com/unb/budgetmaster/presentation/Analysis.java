package com.unb.budgetmaster.presentation;


import com.unb.budgetmaster.data.implementation.AnalysisImpl;
import com.unb.budgetmaster.data.implementation.CategoryImpl;
import com.unb.budgetmaster.data.implementation.Database;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Analysis {
    // Constants
    private final int CATEGORIES_PER_PAGE = 5;

    // Analysis variables
    private int categoryStartIndex = 0;
    private String currentAnalysisType = "Spendings"; // Default type for Category Analysis

    // Dates
    private LocalDate startDate;
    private LocalDate endDate;

    // Labels
    private Label remainingBudgetLabel;
    private Label remainingAmountLabel;
    private Label savedAmountLabel;


    // Implementation Instances
    private AnalysisImpl analysisImpl;
    private CategoryImpl categoryImpl;

    public void getContent(Label contentLabel, Pane root) {
        analysisImpl = new AnalysisImpl();
        categoryImpl = new CategoryImpl();

        // Get username from login information
        String username = Database.user.getUsername();

        // Modify contentLabel
        contentLabel.setText("Analyze your expenses");
        contentLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-alignment: CENTER;");

        // Display current month and remaining budget
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate currentDay = LocalDate.now();
        String remainingBudgetText = "You have spent: $" + analysisImpl.getTotalSpent(firstDayOfMonth, currentDay) + " since " + firstDayOfMonth;
        String remainingAmountText = "You still have $" + (analysisImpl.getBudgetTotal() - analysisImpl.getTotalSpent( firstDayOfMonth, currentDay))  + " to spend!";

        // Display saved amount since the start of the month
        String savedAmountText = "You managed to save: $" + analysisImpl.getTotalSaved(firstDayOfMonth, currentDay) + " since " + firstDayOfMonth;

        // Display typical spending and savings
        String typicalSpendingText = "Your typical monthly spending: $" + analysisImpl.getUsualSpent();
        String typicalSavingsText = "Your typical monthly savings: $" + analysisImpl.getUsualSaved();

        // Create labels for the above texts
        remainingBudgetLabel = new Label(remainingBudgetText);
        remainingAmountLabel = new Label(remainingAmountText);
        savedAmountLabel = new Label(savedAmountText);
        Label typicalSpendingLabel = new Label(typicalSpendingText);
        Label typicalSavingsLabel = new Label(typicalSavingsText);

        // Left align the labels
        remainingBudgetLabel.setStyle("-fx-alignment: LEFT;");
        remainingAmountLabel.setStyle("-fx-alignment: LEFT;");
        savedAmountLabel.setStyle("-fx-alignment: LEFT;");
        typicalSpendingLabel.setStyle("-fx-alignment: LEFT;");
        typicalSavingsLabel.setStyle("-fx-alignment: LEFT;");

        // Create "Analyze through dates" button
        Button analyzeThroughDatesButton = new Button("Analyze through dates");
        analyzeThroughDatesButton.setOnAction(event -> showDateAnalysisPopup());

        // Create "Analyze through Categories" button
        Button analyzeThroughCategoriesButton = new Button("Analyze through Categories");
        analyzeThroughCategoriesButton.setOnAction(event -> showCategoryAnalysis(root, contentLabel));

        // Add components to contentContainer
        root.getChildren().addAll(
                remainingBudgetLabel,
                remainingAmountLabel,
                savedAmountLabel,
                typicalSpendingLabel,
                typicalSavingsLabel,
                analyzeThroughDatesButton,
                analyzeThroughCategoriesButton
        );
    }

    private void showDateAnalysisPopup() {
        // Create a new stage for the popup
        Stage popupStage = new Stage();
        popupStage.setTitle("Analyze through dates");
        popupStage.initModality(Modality.APPLICATION_MODAL);

        // Create labels and text fields for start and end dates
        Label startDateLabel = new Label("Start date");
        Label endDateLabel = new Label("End date");

        TextField startDayField = new TextField();
        startDayField.setPromptText("DD");
        TextField startMonthField = new TextField();
        startMonthField.setPromptText("MM");
        TextField startYearField = new TextField();
        startYearField.setPromptText("YYYY");
        TextField endDayField = new TextField();
        endDayField.setPromptText("DD");
        TextField endMonthField = new TextField();
        endMonthField.setPromptText("MM");
        TextField endYearField = new TextField();
        endYearField.setPromptText("YYYY");

        // Bold style for labels
        startDateLabel.setStyle("-fx-font-weight: bold;");
        endDateLabel.setStyle("-fx-font-weight: bold;");

        // Create analyze button
        Button analyzeButton = new Button("Analyze");
        analyzeButton.setOnAction(event -> {
            analyzeThroughDates(startDayField.getText(), startMonthField.getText(), startYearField.getText(),
                    endDayField.getText(), endMonthField.getText(), endYearField.getText());
            popupStage.close();
        });

        // Create layout for the popup
        VBox popupLayout = new VBox(10);
        popupLayout.setPadding(new Insets(10));
        popupLayout.getChildren().addAll(
                startDateLabel,
                new HBox(10, startDayField, startMonthField, startYearField),
                endDateLabel,
                new HBox(10, endDayField, endMonthField, endYearField),
                analyzeButton
        );

        // Create scene for the popup
        Scene popupScene = new Scene(popupLayout, 300, 200);
        popupStage.setScene(popupScene);

        // Show the popup
        popupStage.showAndWait();
    }

    private void analyzeThroughDates(String startDay, String startMonth, String startYear,
                                            String endDay, String endMonth, String endYear) {
        try {
            // Get date values as ints from passed in values
            int startDayInt = Integer.parseInt(startDay);
            int startMonthInt = Integer.parseInt(startMonth);
            int startYearInt = Integer.parseInt(startYear);
            int endDayInt = Integer.parseInt(endDay);
            int endMonthInt = Integer.parseInt(endMonth);
            int endYearInt = Integer.parseInt(endYear);

            // Set our startDate and endDate values using data
            startDate = LocalDate.of(startYearInt, startMonthInt, startDayInt);
            endDate = LocalDate.of(endYearInt, endMonthInt, endDayInt);
        } catch (NumberFormatException | DateTimeException e) {
            // Handle invalid input
            System.out.println("Invalid date format");
            return;
        }

        updateValues();
        return;
    }

    private void updateValues() {
        // Set new strings for amount spent and amount saved within given range
        String newRemainingBudgetText = "You have spent: $" + analysisImpl.getTotalSpent( startDate, endDate) + " from " + startDate + " until " + endDate;
        String newSavedAmountText = "You managed to save: $" + analysisImpl.getTotalSaved( startDate, endDate) + " from " + startDate + " until " + endDate;

        // Set the labels to have the new text
        remainingBudgetLabel.setText(newRemainingBudgetText);
        remainingAmountLabel.setText("");
        savedAmountLabel.setText(newSavedAmountText);
    }

    private void showCategoryAnalysis(Pane root, Label contentLabel) {
        // Clear content
        clearContent(root);

        // Change contentLabel
        contentLabel.setText("Category Analysis");

        // Create labels for columns
        Label categoryLabel = new Label("Category");
        Label percentageLabel = new Label("% of Spendings");
        Label amountPaidLabel = new Label("Amount Paid");

        // Center align the column labels
        categoryLabel.setStyle("-fx-alignment: CENTER;");
        percentageLabel.setStyle("-fx-alignment: CENTER;");
        amountPaidLabel.setStyle("-fx-alignment: CENTER;");

        // Create HBox for header
        HBox headerRow = new HBox(10, categoryLabel, percentageLabel, amountPaidLabel);

        // Add header row to contentContainer
        root.getChildren().add(headerRow);

        // Display categories
        VBox categoriesContainer = new VBox(10);
        updateCategories(categoriesContainer);

        // Create "Analyze through Categories" buttons
        Button previousCategoriesButton = new Button("Previous");
        Button nextCategoriesButton = new Button("Next");
        Button switchAnalysisTypeButton = new Button("Switch to Savings");
        Button returnToMonthlyAnalysisButton = new Button("Return to Monthly Analysis");
        Button analyzeCategoriesThroughDatesButton = new Button("Analyze Categories through Dates");

        // Set button events
        previousCategoriesButton.setOnAction(event -> {
            categoryStartIndex = Math.max(categoryStartIndex - CATEGORIES_PER_PAGE, 0);
            updateCategories(categoriesContainer);
        });

        nextCategoriesButton.setOnAction(event -> {
            int totalCategories = categoryImpl.getCategories(currentAnalysisType).size();
            categoryStartIndex = Math.min(categoryStartIndex + CATEGORIES_PER_PAGE, totalCategories - 1);
            updateCategories(categoriesContainer);
        });

        switchAnalysisTypeButton.setOnAction(event -> {
            if (currentAnalysisType.equals("Spendings")) {
                currentAnalysisType = "Savings";
                switchAnalysisTypeButton.setText("Switch to Spendings");
            } else {
                currentAnalysisType = "Spendings";
                switchAnalysisTypeButton.setText("Switch to Savings");
            }
            categoryStartIndex = 0; // Reset category index when switching types
            updateCategories(categoriesContainer);
        });

        returnToMonthlyAnalysisButton.setOnAction(event -> getContent(contentLabel, root));

        analyzeCategoriesThroughDatesButton.setOnAction(event -> showDateAnalysisPopup());

        // Set initial button visibility
        updateCategoryButtonVisibility();

        // Create HBox for buttons
        HBox buttonsBox = new HBox(10,
                previousCategoriesButton,
                nextCategoriesButton,
                switchAnalysisTypeButton,
                returnToMonthlyAnalysisButton,
                analyzeCategoriesThroughDatesButton);

        // Set button alignment
        buttonsBox.setStyle("-fx-alignment: CENTER_LEFT;");

        // Add components to contentContainer
        root.getChildren().addAll(categoriesContainer, buttonsBox);
    }

    private void updateCategories(VBox categoriesContainer) {
        categoriesContainer.getChildren().clear();

        // Get categories from the provider
        ArrayList<String> categories = new ArrayList<String>();
        int totalCategories = categoryImpl.getCategories( currentAnalysisType).size();
        for(int i = 0; i < totalCategories; i++) {
            categories.add(categoryImpl.getCategories( currentAnalysisType).get(i).getName());
        }

        // Display categories based on the current categoryStartIndex
        for (int i = categoryStartIndex; i < categoryStartIndex + CATEGORIES_PER_PAGE && i < categories.size(); i++) {
            String category = categories.get(i);
            HBox categoryRow = createCategoryRow(category);
            categoriesContainer.getChildren().add(categoryRow);
        }

        // Update button visibility based on the categoryStartIndex and total categories
        updateCategoryButtonVisibility();
    }

    private HBox createCategoryRow(String category) {
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        double amountSpent = analysisImpl.getAmountSpentByCategory(category, firstDayOfMonth, LocalDate.now());
        double totalSpent = analysisImpl.getTotalSpent(firstDayOfMonth, LocalDate.now());
        double percentage = (totalSpent != 0) ? (amountSpent / totalSpent) * 100 : 0;

        Label categoryLabel = new Label(category);
        Label percentageLabel = new Label(String.format("%.2f%%", percentage));
        Label amountPaidLabel = new Label(String.format("$%.2f", amountSpent));

        // Center align the data
        categoryLabel.setStyle("-fx-alignment: CENTER;");
        percentageLabel.setStyle("-fx-alignment: CENTER;");
        amountPaidLabel.setStyle("-fx-alignment: CENTER;");

        // Create HBox to contain everything
        HBox containerHBox = new HBox(10, categoryLabel, percentageLabel, amountPaidLabel);

        return containerHBox;
    }

    private void updateCategoryButtonVisibility() {

    }

    private void clearContent(Pane root) {
        root.getChildren().clear();
    }
}