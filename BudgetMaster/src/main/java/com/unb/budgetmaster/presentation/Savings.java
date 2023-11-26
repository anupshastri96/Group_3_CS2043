package com.unb.budgetmaster.presentation;

import java.util.ArrayList;

import com.unb.budgetmaster.data.implementation.AnalysisImpl;
import com.unb.budgetmaster.data.implementation.TransactionImpl;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Savings {
    private AnalysisImpl analysisImpl;
   private TransactionImpl transactionImpl;;

    public void getContent(Label contentLabel, VBox contentContainer) {
        //Instantiate our implementations
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

/*Savings page functions:
Savings tab displays the transactions made to the savings account. If there exists different categories then you can toggle between different categories.
There is a big colored box in which you have text saying the category name and then you have a edit button in the say right end of the box which is an edit icon.
On clicking the icon, you open an edit category box where you can edit your category goal (which updates budget variable in category model) and you can change
the category name.

In the bottom tab of this page, we will have some vector art related to analysing things that says: "Analyse your savings" in the middle and art around it. 
This helps fill up the space on the page and makes the page more attractive to look at. Also helps people to regularly get reminder of analysing their 
transactions to keep up with their financial goals.

 * 1. a function to check if you have categories or not. (create a helper function this helps in both the spendings and savings)
 * 2. a function to add categories and show up a popup box for that. 
 * 3. a function that checks the category with maximum budget and sets it as default/ shows it first on screen. (can also be made to a helper function)
 * 4. for category page you have a button on the transaction to move it to a different category (optional) (implement if we have time)
 * 5. call the helper function that shows the list of all the transactions say the helper function showTransactions(type:savings/spendings) 
 * with other related parameters you need for doing the UI job without redundant code.
 * 6. All the savings goals and stuff comes in analysis tab so don't bother about it.
 */




        // Create array list of Goal objects
        // ArrayList<Goal> goals = savingImpl.getGoalList();

        // // Display up to 5 Goal objects
        // for (int i = 0; i < Math.min(goals.length, 5); i++) {
        //     Goal goal = goals.get(i);
        //     HBox goalRow = createGoalRow(goal);
        //     contentContainer.getChildren().add(goalRow);
        // }

        // Add "Create New Goal" button
        Button createNewGoalButton = new Button("Create New Goal");
        contentContainer.getChildren().add(createNewGoalButton);
    }

    // private static HBox createGoalRow(Goal goal) {
    //     Label goalLabel = new Label(goal.getGoal());
    //     Label percentageLabel = new Label(goal.getPercentage());
    //     Label currentAmountLabel = new Label(goal.getCurrentAmount());

    //     // Center align the data
    //     goalLabel.setStyle("-fx-alignment: CENTER;");
    //     percentageLabel.setStyle("-fx-alignment: CENTER;");
    //     currentAmountLabel.setStyle("-fx-alignment: CENTER;");

    //     return new HBox(10, goalLabel, percentageLabel, currentAmountLabel);
    // }
}
