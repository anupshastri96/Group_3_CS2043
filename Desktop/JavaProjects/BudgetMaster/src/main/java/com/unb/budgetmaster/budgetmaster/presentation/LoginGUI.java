package com.unb.budgetmaster.budgetmaster.presentation;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.event.ActionEvent;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight;
import javafx.scene.layout.VBox;


public class LoginGUI extends Application{
    private TextField userName_tf;
    private TextField passWord_tf;
    private Text displayLogin;
    private Text title;

    public void start (Stage loginStage){
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

         Button submit = new Button("Submit");
        
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
}