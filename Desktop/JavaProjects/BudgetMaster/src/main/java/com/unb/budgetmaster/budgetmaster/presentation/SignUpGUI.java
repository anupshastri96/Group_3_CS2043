package com.unb.budgetmaster.budgetmaster.presentation;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;



public class SignUpGUI extends Application{
    private TextField firstName_tf;
    private TextField middleName_tf;
    private TextField lastName_tf;
    private TextField userName_tf;
    private TextField passWord_tf;
    private TextField confirmPass_tf;
    private Text displaySignUp;
    private Text title;
 
    public void start(Stage signUpStage){
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        signUpStage.setMaximized(true);
        signUpStage.setTitle("Sign Up Page");
        
        title = new Text("Budget Master");
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));

        displaySignUp = new Text("Sign Up");
        displaySignUp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));

        Label firstName_lb = new Label("First Name*");
        firstName_tf = new TextField();
        firstName_tf.setMaxWidth(200);

        Label middleName_lb = new Label("Middle Name*");
        middleName_tf = new TextField();
        middleName_tf.setMaxWidth(200);

        Label lastName_lb = new Label("Last Name*");
        lastName_tf = new TextField();
        lastName_tf.setMaxWidth(200);

        Label userName_lb = new Label("Username*");
        userName_tf = new TextField();
        userName_tf.setMaxWidth(200);

        Label passWord_lb = new Label("Password*");
        passWord_tf = new TextField();
        passWord_tf.setMaxWidth(200);

        Label confirmPass_lb = new Label("Confirm Password*");
        confirmPass_tf = new TextField();
        confirmPass_tf.setMaxWidth(200);

        Button submit = new Button("Submit");
        
        VBox topPane = new VBox(10.0);
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().addAll(title, displaySignUp, firstName_lb, firstName_tf, middleName_lb, middleName_tf, lastName_lb, lastName_tf, userName_lb, userName_tf, passWord_lb, passWord_tf, confirmPass_lb, confirmPass_tf, submit);
        

        Scene scene = new Scene(topPane, screenSize.getWidth(), screenSize.getHeight());
        signUpStage.setScene(scene);
        signUpStage.show();
    }

    public static void main(String[] args) {
    	launch(args);
    }
}