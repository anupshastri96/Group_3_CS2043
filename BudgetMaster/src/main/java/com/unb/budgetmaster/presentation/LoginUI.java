package com.unb.budgetmaster.presentation;

import com.unb.budgetmaster.data.implementation.LoginImpl;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;


public class LoginUI {

    // Implementation Instances
    private LoginImpl loginImpl;

    // UI Instances
    private Menu menu;
    private SignUpUI signUpUI;
    //private ForgotPasswordUI forgotPasswordUI; // Change name of class to fit this

    public void getLoginUI(Pane root){
        // Instantiate implementations
        loginImpl = new LoginImpl();

        // Instantiate UI instances
        menu = new Menu();
        signUpUI = new SignUpUI();
        //forgotPasswordUI = new ForgotPasswordUI; //Change name of class to fit this

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
            // Switch to Menu screen
            menu.getContentMenu(root, username);
            return;
        }

        // If not, tell user that username or password is invalid
        loginSuccess.setText("Invalid username/password");
        return;
    }

    private void switchToFP(Pane root) {
        // Switch the content displayed in root to Forgot Password
        //forgotPasswordUI.getContent(root);
        return;
    }

    private void switchToSignUp(Pane root) {
        // Switch the content displayed in root to Sign Up
        signUpUI.getContent(root);
        return;
    }
}
// End of LoginUI class