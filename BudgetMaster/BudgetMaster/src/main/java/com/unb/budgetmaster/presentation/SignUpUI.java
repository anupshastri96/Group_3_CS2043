package com.unb.budgetmaster.presentation;

import com.unb.budgetmaster.data.implementation.Database;
import com.unb.budgetmaster.data.implementation.LoginImpl;
import com.unb.budgetmaster.domain.model.User;
import com.unb.budgetmaster.presentation.SecurityQuestionsUI;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

public class SignUpUI {
    // Declare label for enter details
    private Label enterDetail_lb;

    // Implementation Instances
    private LoginImpl loginImpl;

    // UI Instances
    private SecurityQuestionsUI securityQuestionsUI; // Change name of class to match this
 
    public void getContent(BorderPane root){
        // Instantiate Implementation
        loginImpl = new LoginImpl();

        // UI Implementation
        securityQuestionsUI = new SecurityQuestionsUI();
        
        // Create text for title
        Text title = new Text("Budget Master");
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));

        // Create text for Sign Up
        Text displaySignUp = new Text("Sign Up");
        displaySignUp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));

        // Create label indicating to enter details
        enterDetail_lb = new Label("Please enter your details");

        // Create label and text field for First Name
        Label firstName_lb = new Label("First Name*");
        TextField firstName_tf = new TextField();
        firstName_tf.setMaxWidth(200);

        // Create label and text field for Middle Name
        Label middleName_lb = new Label("Middle Name");
        TextField middleName_tf = new TextField();
        middleName_tf.setMaxWidth(200);

        // Create label and text field for Last Name
        Label lastName_lb = new Label("Last Name*");
        TextField lastName_tf = new TextField();
        lastName_tf.setMaxWidth(200);

        // Create lavel and text field for Username
        Label userName_lb = new Label("Username*");
        TextField userName_tf = new TextField();
        userName_tf.setMaxWidth(200);

        // Create label and text field for Password
        Label passWord_lb = new Label("Password*");
        TextField passWord_tf = new TextField();
        passWord_tf.setMaxWidth(200);

        // Create label and text field for Confirm Password
        Label confirmPass_lb = new Label("Confirm Password*");
        TextField confirmPass_tf = new TextField();
        confirmPass_tf.setMaxWidth(200);

        // Create button to submit information
        Button submit = new Button("Submit");
        submit.setOnAction(event -> confirmSignUp(firstName_tf.getText(), middleName_tf.getText(), lastName_tf.getText(), userName_tf.getText(), passWord_tf.getText(), confirmPass_tf.getText(), root));
        
        // Create VBox to contain all fields
        VBox topPane = new VBox(10.0);
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().addAll(title, displaySignUp, enterDetail_lb, firstName_lb, firstName_tf, middleName_lb, middleName_tf, lastName_lb, lastName_tf, userName_lb, userName_tf, passWord_lb, passWord_tf, confirmPass_lb, confirmPass_tf, submit);

        // Set the root Pane to the Sign Up UI
        root.getChildren().clear();
        root.setCenter(topPane);
    }

    private void confirmSignUp(String firstname, String middlename, String lastname, String username, String password, String confirmPassword, BorderPane root) {
        // Check if First Name field isn't blank
        if(firstname.equals("")) {
            enterDetail_lb.setText("Please enter a First Name");
            return;
        }

        // Check if Last Name field isn't blank
        if(lastname.equals("")) {
            enterDetail_lb.setText("Please enter a Last Name");
            return;
        }

        // Check if Username field isn't blank
        if(username.equals("")) {
            enterDetail_lb.setText("Please enter a Username");
            return;
        }

        // Check if Password field isn't blank
        if(password.equals("")) {
            enterDetail_lb.setText("Please enter a Password");
            return;
        }
        
        // Check if Confirm Password field isn't blank
        if(confirmPassword.equals("")) {
            enterDetail_lb.setText("Please confirm your password");
            return;
        }

        // Verify that the username doesn't exist in our database
        if(loginImpl.doesUsernameExists(username)) {
            enterDetail_lb.setText("Username already taken/Invalid username");
            return;
        }
        
        // Verify that our Password and Confirm Password fields match
        if(!loginImpl.confirmPassword(password, confirmPassword)) {
            enterDetail_lb.setText("Passwords do not match");
            return;
        }

        Database.user = new User(firstname,middlename,lastname,username,password);
        // Switch to Security Questions Page
        securityQuestionsUI.getContent(root, true);
    }
}
// End of SignUpUI class