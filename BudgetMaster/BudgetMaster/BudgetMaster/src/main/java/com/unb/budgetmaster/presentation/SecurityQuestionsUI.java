package com.unb.budgetmaster.presentation;

import com.unb.budgetmaster.data.implementation.Database;
import com.unb.budgetmaster.data.implementation.LoginImpl;
import com.unb.budgetmaster.domain.model.User;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class SecurityQuestionsUI {
    // Implementation Instances
    private LoginImpl loginImpl;

    // UI Instances
    private com.unb.budgetmaster.presentation.SignUpUI signUpUI;
    private LoginUI loginUI;
    private ForgotPasswordUI forgotPasswordUI;
    private Menu menu;

    // Security Questions Array
    private final String[] securityQuestionsArray = {
        "Question 1: What is your favorite childhood pet's name",
        "Question 2: In which city were you born?",
        "Question 3: What is your mother's maiden name?",
        "Question 4: What is the name of your favorite teacher in high school?",
        "Question 5: What is the model of your favourite car?"
    };

    public void getContent(BorderPane root, Boolean isNewUser) {
        // Instantiate implementations
        loginImpl = new LoginImpl();
        
        //Instantiate UI instances
        signUpUI = new SignUpUI();
        loginUI = new LoginUI();
        forgotPasswordUI = new ForgotPasswordUI();
        menu = new Menu();

        // Create text for title
        Text title = new Text("Security Questions");
        title.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 40));

        // Create text prompts for security questions
        Text firstQuestionPrompt = new Text("");
        Text secondQuestionPrompt = new Text("");

        // Create VBox to contain everything in
        VBox topPane = new VBox(10.0);
        topPane.setAlignment(Pos.CENTER);

        // Create texts for security questions
        Text firstQuestionText = new Text("");
        Text secondQuestionText = new Text("");

        // Create text fields for security questions
        TextField firstQuestionField = new TextField();
        TextField secondQuestionField = new TextField();

        // Create back and submit buttons
        Button backButton = new Button("Back");
        Button submitButton = new Button("Submit");

        // Create text to display if incorrect answers are given
        Text incorrectText = new Text("");

        // If new user, let user choose security questions
        if(isNewUser) {
            // Create choice boxes for security questions
            ChoiceBox<String> choiceBox1 = new ChoiceBox<String>();
            ChoiceBox<String> choiceBox2 = new ChoiceBox<String>();

            // Set text to choose security questions
            firstQuestionPrompt.setText("Please choose a Security Question");
            secondQuestionPrompt.setText("Please choose a Security Question");

            // Add security questions to choice boxes
            choiceBox1.getItems().addAll(securityQuestionsArray);
            choiceBox2.getItems().addAll(securityQuestionsArray);
            choiceBox1.setOnAction(event -> getSecurityQuestion(firstQuestionText, choiceBox1));
            choiceBox2.setOnAction(event -> getSecurityQuestion(secondQuestionText, choiceBox2));

            // Create HBox for security questions text and choice boxes
            HBox choice1 = new HBox(10);
            HBox choice2 = new HBox(10);
            choice1.setAlignment(Pos.CENTER);
            choice2.setAlignment(Pos.CENTER);

            // Add security questions text and choices to HBox
            choice1.getChildren().addAll(firstQuestionText, choiceBox1);
            choice2.getChildren().addAll(secondQuestionText, choiceBox2);

            // Add functionality to back and submit buttons
            backButton.setOnAction(event -> backEvent(root, true));
            submitButton.setOnAction(event -> submitEvent(root, true, choiceBox1.getValue(), firstQuestionField.getText(), choiceBox2.getValue(), secondQuestionField.getText(), incorrectText));

            // Add elements to the VBox
            topPane.getChildren().addAll(title, firstQuestionPrompt, choice1, firstQuestionField, secondQuestionPrompt, choice2, secondQuestionField);
        }
    }

    private void getSecurityQuestion(Text text, ChoiceBox<String> choiceBox) {
        // Get the question from the choice box
        String question = choiceBox.getValue();

        //  Set the value of our text to the text box
        text.setText(question);
        return;
    }

    private void backEvent(BorderPane root, Boolean isNewUser) {
        if(isNewUser) {
            // Switch the content displayed in root to Sign Up
            signUpUI.getContent(root);
            return;
        }

        // Switch the content displayed to Log In
        loginUI.getLoginUI(root);
        return;
    }

    private void submitEvent(BorderPane root, Boolean isNewUser, String question1, String answer1, String question2, String answer2, Text incorrectText) {
        // Get our username from the login information given
        String username = Database.user.getUsername();
        
        // Check if an answer was given for the first security question
        if(answer1.equals("")) {
            incorrectText.setText("Please input an answer for the first security question");
            return;
        }
        // Check if an answer was given for the second security question
        if(answer2.equals("")) {
            incorrectText.setText("Please input an answer for the second security question");
            return;
        }
        
        // Check if we are submitting as a new user
        if(isNewUser) {
            // Check if both security questions are different
            if(question1.equals(question2)) {
                incorrectText.setText("Please choose 2 different security questions");
                return;
            }

            loginImpl.createUser(Database.user);

            menu.getContentMenu(root);
        }

        // Verify that answers match the ones established
        if(!loginImpl.checkSecurityQuestions(username, answer1, answer2)) {
            incorrectText.setText("One or more of your answers are incorrect, please try again!");
            return;
        }

        // Go to Forgot Password
        forgotPasswordUI.getContent(root, username);

    }
}