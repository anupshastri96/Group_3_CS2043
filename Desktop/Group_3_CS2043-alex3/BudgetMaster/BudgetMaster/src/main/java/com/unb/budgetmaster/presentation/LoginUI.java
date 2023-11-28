package com.unb.budgetmaster.presentation;

import java.util.ArrayList;

import com.unb.budgetmaster.data.implementation.Database;
import com.unb.budgetmaster.data.implementation.LoginImpl;
import com.unb.budgetmaster.domain.helper.Toast;
import com.unb.budgetmaster.domain.model.User;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

public class LoginUI {

    // Implementation Instances
    private LoginImpl loginImpl;

    // UI Instances
    private Menu menu;
    private SignUpUI signUpUI;
    private SecurityQuestionsUI securityQuestionsUI;

    BorderPane loginRoot;

    public void getLoginUI(BorderPane root){
        // Instantiate implementations
        loginImpl = new LoginImpl();
        loginRoot = root;

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
        createAccountButton.setOnAction(event -> switchToSignUp());

        // Create button for Forgot Password
        Button forgotPasswordButton = new Button("Forgot Password");
        forgotPasswordButton.setOnAction(event -> switchToSecurityQuestions());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(createAccountButton, forgotPasswordButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Create button to submit login information
        Button submit = new Button("Submit");
        submit.setOnAction(event -> verifyLogin(userName_tf.getText(), passWord_tf.getText(), root, loginSuccess));
        
        // DELETE THIS
        Button temporary = new Button("Temporary");
        temporary.setOnAction(event -> {
            Database.user = new User("Alex", "middlename", "Boudreau", "Username_Here", "Password_Here");
            menu.getContentMenu(root);
        });

        // Create VBox to contain everything in
        VBox topPane = new VBox(20.0);
        topPane.setStyle("-fx-alignment: CENTER");
        topPane.getChildren().addAll(title, displayLogin, loginSuccess, userName_lb, userName_tf, passWord_lb, passWord_tf, submit, buttonBox, temporary);

        // Set the root Pane to the Login UI
        root.getChildren().clear();
        root.setCenter(topPane);
    }

    private void verifyLogin(String username, String password, BorderPane root, Text loginSuccess) {
        // Check to make sure our username and password are part of our database
        if(loginImpl.checkLoginInfo(username, password)) {
            // Set user information
            Database.user = new User("blank", "blank", "blank", username, password);
            Database.user = loginImpl.getUser();

            // Switch to Menu screen
            menu.getContentMenu(root);
            return;
        }

        // If not, tell user that username or password is invalid
        loginSuccess.setText("Invalid username/password");
        return;
    }

    private void switchToSecurityQuestions() {
        // Open prompt that asks for username
        openUsernameInput();
        // Switch the content displayed in root to Security Questions
        return;
    }

    private void switchToSignUp() {
        // Switch the content displayed in root to Sign Up
        signUpUI.getContent(loginRoot);
        return;
    }

//    securityQuestionsUI.getContent(root, false);
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

        if(!loginImpl.doesUsernameExists(inputUsername)) {
            usernameTextField.clear();
            Toast.makeText(stage,"Invalid username, please try again");
//            usernameTextField.setPromptText("Invalid username, please try again");
            return;
        }

        Database.user = new User("test", "test", "test", inputUsername, "test");
        Database.user = loginImpl.getUser();
        securityQuestionsUI.getContent(loginRoot, false);
        stage.close();
    }
}
// End of LoginUI class