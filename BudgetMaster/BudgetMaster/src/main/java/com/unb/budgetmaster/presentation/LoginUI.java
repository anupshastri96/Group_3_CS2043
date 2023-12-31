package com.unb.budgetmaster.budgetmaster.presentation;

import java.util.ArrayList;

import com.unb.budgetmaster.budgetmaster.domain.implementation.LoginImpl;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class LoginUI {

    // Implementation Instances
    private LoginImpl loginImpl;

    // UI Instances
    private Menu menu;
    private SignUpUI signUpUI;
    private SecurityQuestionsUI securityQuestionsUI;

    // Store username
    private String username;

    public void getLoginUI(Pane root){
        // Instantiate implementations
        loginImpl = new LoginImpl();

        // Instantiate UI instances
        menu = new Menu();
        signUpUI = new SignUpUI();
        securityQuestionsUI = new SecurityQuestionsUI();

        // Create text for title
        Text title = new Text("Budget Master");
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        
        // Create text for Login
        Text displayLogin = new Text("Login");
        displayLogin.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        
        // Create text for incorrect login information
        Text loginSuccess = new Text("");

        // Create label and text field for username
        Label userName_lb = new Label("Username: ");
        TextField userName_tf = new TextField();
        userName_tf.setMaxWidth(200);
        
        // Create label and text field for password
        Label passWord_lb = new Label("Password: ");
        TextField passWord_tf = new TextField();
        passWord_tf.setMaxWidth(200);

        // Create button for Create Account
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setOnAction(event -> switchToSignUp(root));

        // Create button for Forgot Password
        Button forgotPasswordButton = new Button("Forgot Password");
        forgotPasswordButton.setOnAction(event -> switchToFP(root));

        // Create button to submit login information
        Button submit = new Button("Submit");
        submit.setOnAction(event -> verifyLogin(userName_tf.getText(), passWord_tf.getText(), root, loginSuccess));
        
        // Create VBox to contain everything in
        VBox topPane = new VBox(10.0);
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().addAll(title, displayLogin, loginSuccess, userName_lb, userName_tf, passWord_lb, passWord_tf, submit);
        
        // Set the root Pane to the Login UI
        root.getChildren().clear();
        root.getChildren().add(topPane);
        return;
    }

    private void verifyLogin(String username, String password, Pane root, Text loginSuccess) {
        // Check to make sure our username and password are part of our database
        if(loginImpl.checkLoginInfo(username, password)) {
            // Get login information from username
            ArrayList<String> loginInformation = loginImpl.getLoginDetails(username);

            // Switch to Menu screen
            menu.getContentMenu(root, loginInformation);
            return;
        }

        // If not, tell user that username or password is invalid
        loginSuccess.setText("Invalid username/password");
        return;
    }

    private void switchToFP(Pane root) {
        // Open prompt that asks for username
        openUsernameInput();

        ArrayList<String> loginInformation = loginImpl.getLoginDetails(username);

        // Switch the content displayed in root to Security Questions
        securityQuestionsUI.getContent(root, loginInformation, false);
        return;
    }

    private void switchToSignUp(Pane root) {
        // Switch the content displayed in root to Sign Up
        signUpUI.getContent(root);
        return;
    }

    private void openUsernameInput() {
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UTILITY);
        popupStage.initModality(Modality.WINDOW_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Enter your username");

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> popupStage.close());
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> submitUsername(usernameTextField.getText(), usernameTextField, popupStage));

        grid.add(usernameTextField, 0, 0, 2, 1);
        grid.add(backButton, 0, 1);
        grid.add(submitButton, 1, 1);

        Scene dialogScene = new Scene(grid, 300, 150);
        popupStage.setScene(dialogScene);
        popupStage.setTitle("Enter your username");
        popupStage.show();
    }

    private void submitUsername(String inputUsername, TextField usernameTextField, Stage stage) {
        if(inputUsername.equals("")) {
            usernameTextField.clear();
            usernameTextField.setPromptText("Please input a username");
            return;
        }

        if(loginImpl.doesUsernameExists(inputUsername) == false) {
            usernameTextField.clear();
            usernameTextField.setPromptText("Invalid username, please try again");
            return;
        }

        username = inputUsername;
        stage.close();
    }
}
// End of LoginUI class