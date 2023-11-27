package com.unb.budgetmaster.budgetmaster.presentation;

import java.util.ArrayList;

import com.unb.budgetmaster.budgetmaster.domain.implementation.LoginImpl;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ForgotPasswordUI {
    // Implementation Instances
    private LoginImpl loginImpl;
    
    // UI Instances
    private LoginUI loginUI;

    private Label notConfirmed;

    public void getContent(BorderPane root, String username) {
        // Instantiate implementation
        loginImpl = new LoginImpl();

        // Instantiate UI instances
        loginUI = new LoginUI();

        // Create text for title
        Text title = new Text("Forgot Password");
        title.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 40));

        // Create label indicating to enter details
        notConfirmed = new Label("");

        // Create label and text field for Password
        Label passWord_lb = new Label("New Password: ");
        TextField passWord_tf = new TextField();
        passWord_tf.setMaxWidth(200);

        // Create label and text field for Confirm Password
        Label confPassWord_lb = new Label("Confirm New Password: ");
        TextField confPassWord_tf = new TextField();
        passWord_tf.setMaxWidth(200);

        // Add button to go back to login
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> switchToLogin(root));

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> switchToMenu(root, username, passWord_tf.getText(), confPassWord_tf.getText()));

        // Create VBox to contain everything in
        VBox topPane = new VBox(10.0);
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().addAll(title, notConfirmed, passWord_lb, passWord_tf, confPassWord_lb, confPassWord_tf, backButton, submitButton);

        root.getChildren().clear();
        root.setCenter(topPane);
    }

    private void switchToLogin(BorderPane root) {
        loginUI.getLoginUI(root);
        return;
    }

    private void switchToMenu(BorderPane root, String username, String password, String confirmPass) {
        // Verify that our Password and Confirm Password fields match
        if(loginImpl.confirmPassword(password, confirmPass) == false) {
            notConfirmed.setText("Passwords do not match");
            return;
        }

        // Get login information to set the new password
        ArrayList<String> loginInformation = loginImpl.getLoginDetails(username);

        // Set our new password
        loginImpl.setLoginDetails(loginInformation);

        // Go to login page
        switchToLogin(root);
        return;
    }
}