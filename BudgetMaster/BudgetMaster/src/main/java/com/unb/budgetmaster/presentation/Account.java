package com.unb.budgetmaster.presentation;

import com.unb.budgetmaster.data.implementation.AnalysisImpl;
import com.unb.budgetmaster.data.implementation.Database;
import com.unb.budgetmaster.data.implementation.TransactionImpl;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Account {
    // Interface Implementation
    private AnalysisImpl analysisImpl;

    // Buttons
    Button editBudgetButton;
    Button changePasswordButton;
    Button logOutButton;

    public void getContent(Label contentLabel, VBox contentContainer) {
        //Instantiate implementations
        analysisImpl = new AnalysisImpl();

        // Modify contentLabel
        contentLabel.setText("Account Details");

        // Create Label for name
        Label name_lb = new Label("Name: " + Database.user.getFullName());

        // Create Label for balance
        Label balance_lb = new Label("Balance: " + analysisImpl.getBalance());

        // Create Label for Current Budget
        Label currentBudget_lb = new Label("Current Budget: " + analysisImpl.getBudgetTotal());

        // Setup buttons
        editBudgetButton = new Button("Edit");
        editBudgetButton.setOnAction(event -> editBudget());
        changePasswordButton = new Button("Change Password");
        changePasswordButton.setOnAction(event -> changePassword());
        logOutButton = new Button("Log Out");
        logOutButton.setOnAction(event -> logOut());

        // Create HBox for Budget label and Edit Budget button
        HBox budgetBox = new HBox(20);
        budgetBox.getChildren().addAll(currentBudget_lb, editBudgetButton);

        // Add components to contentContainer
        contentContainer.getChildren().addAll(
            name_lb,
            balance_lb,
            budgetBox,
            changePasswordButton,
            logOutButton
        );
        contentContainer.setSpacing(20);
    }

    public void editBudget() {
        
    }

    public void changePassword() {

    }

    public void logOut() {

    }
}
// End of Account class