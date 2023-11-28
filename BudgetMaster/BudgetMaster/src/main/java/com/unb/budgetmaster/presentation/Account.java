package com.unb.budgetmaster.presentation;

import com.unb.budgetmaster.data.implementation.AnalysisImpl;
import com.unb.budgetmaster.data.implementation.Database;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Account {
    // Interface Implementation
    private AnalysisImpl analysisImpl;

    // UI Implementation
    private LoginUI loginUI;

    // Buttons
    Button editBudgetButton;
    Button changePasswordButton;
    Button logOutButton;

    Label currentBudget_lb;

    public void getContent(Label contentLabel, VBox contentContainer, BorderPane root) {
        // Instantiate implementations
        analysisImpl = new AnalysisImpl();

        // UI instances
        loginUI = new LoginUI();

        // Modify contentLabel
        contentLabel.setText("Account Details");

        // Create Label for name
        Label name_lb = new Label("Name: " + Database.user.getFullName());

        // Create Label for balance
        Label balance_lb = new Label("Balance: " + analysisImpl.getBalance());

        // Create Label for Current Budget
        currentBudget_lb = new Label("Current Budget: " + analysisImpl.getBudgetTotal());

        // Setup buttons
        editBudgetButton = new Button("Edit");
        editBudgetButton.setOnAction(event -> editBudget());
        changePasswordButton = new Button("Change Password");
        changePasswordButton.setOnAction(event -> changePassword());
        logOutButton = new Button("Log Out");
        logOutButton.setOnAction(event -> logOut(root));

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

    private void editBudget() {
        Stage editStage = new Stage();
        editStage.setTitle("Edit Budget");
        editStage.initModality(Modality.APPLICATION_MODAL);

        Label newBudget_lb = new Label("Enter a new Budget Amount");
        TextField budget_tf = new TextField();

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> back(editStage));
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> editSubmit(budget_tf.getText(), budget_tf, editStage));

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(backButton, submitButton);

        VBox editBox = new VBox(10);
        editBox.getChildren().addAll(newBudget_lb, budget_tf, buttonBox);

        // Create scene for the popup
        Scene popupScene = new Scene(editBox, 300, 200);
        editStage.setScene(popupScene);

        // Show the popup
        editStage.show();
    }
    
    private void back(Stage stage) {
        stage.close();
        return;
    }

    private void editSubmit(String newBudget, TextField budget_tf, Stage stage) {
        if(newBudget.equals(null)) {
            budget_tf.clear();
            budget_tf.setPromptText("Please enter a value");
            return;
        }

        double budgetDouble;

        try {
            budgetDouble = Double.parseDouble(newBudget);
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.out.println("Invalid date format");
            return;
        }

        Database.user.setBudgetTotal(budgetDouble);
        currentBudget_lb.setText("Current Budget: " + analysisImpl.getBudgetTotal());
        stage.close();
        return;
    }

    private void changePassword() {
        Stage pWStage = new Stage();
        pWStage.setTitle("Change Password");
        pWStage.initModality(Modality.APPLICATION_MODAL);

        Label newPW_lb = new Label("Enter a new Password");
        TextField pW_tf = new TextField();

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> back(pWStage));
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> passwordSubmit(pW_tf.getText(), pW_tf, pWStage));

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(backButton, submitButton);

        VBox pWBox = new VBox(10);
        pWBox.getChildren().addAll(newPW_lb, pW_tf, buttonBox);

        // Create scene for the popup
        Scene popupScene = new Scene(pWBox, 300, 200);
        pWStage.setScene(popupScene);

        // Show the popup
        pWStage.show();
    }

    private void passwordSubmit(String newPW, TextField pW_tf, Stage stage) {
        if(newPW.equals(null)) {
            pW_tf.clear();
            pW_tf.setPromptText("Please enter a new password");
            return;
        }

        Database.user.setPassword(newPW);
        stage.close();
        return;
    }

    private void logOut(BorderPane root) {
        loginUI.getLoginUI(root);
        return;
    }
}
// End of Account class