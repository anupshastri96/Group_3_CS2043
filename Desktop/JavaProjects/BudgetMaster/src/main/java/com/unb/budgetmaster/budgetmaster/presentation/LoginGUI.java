package com.unb.budgetmaster.budgetmaster.presentation;

import com.unb.budgetmaster.budgetmaster.domain.implementation.LoginImpl;
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.layout.VBox;


public class LoginGUI extends Application{
    private TextField userName_tf;
    private TextField passWord_tf;
    private Text displayLogin;
    private Text title;

    //Implementation
    private LoginImpl loginImpl;

    public void start (Stage loginStage){
        loginImpl = new LoginImpl();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        loginStage.setMaximized(true);

        loginStage.setTitle("Login Page");
        title = new Text("Budget Master");
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        displayLogin = new Text("Login");
        displayLogin.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));

        Label userName_lb = new Label("Username: ");
        userName_tf = new TextField();
        userName_tf.setMaxWidth(200);
        

        Label passWord_lb = new Label("Password: ");
        passWord_tf = new TextField();
        passWord_tf.setMaxWidth(200);

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setOnAction(event -> switchToCA());

        Button forgotPasswordButton = new Button("Forgot Password");
        forgotPasswordButton.setOnAction(event -> switchToFP());

        Button submit = new Button("Submit");
        submit.setOnAction(event -> verifyLogin());
        
        VBox topPane = new VBox(10.0);
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().addAll(title, displayLogin, userName_lb, userName_tf, passWord_lb, passWord_tf, submit);
        

        Scene scene = new Scene(topPane, screenSize.getWidth(), screenSize.getHeight());
         

        loginStage.setScene(scene);
        loginStage.show();
    }
   
    public static void main(String[] args) {
    	launch(args);
    }

    private void verifyLogin() {
        if(loginImpl.checkLoginInfo(userName_tf.getText(), passWord_tf.getText()) == true) {
            
        }
    }

    private void switchToFP() {

    }

    private void switchToCA() {
        
    }
}