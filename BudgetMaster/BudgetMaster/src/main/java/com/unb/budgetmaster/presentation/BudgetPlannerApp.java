package com.unb.budgetmaster.presentation;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BudgetPlannerApp extends Application {

    // UI Instances
    private LoginUI loginUI;
   // private Menu menu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initial setup for primary stage
        primaryStage.setTitle("BudgetMaster");
        primaryStage.setMaximized(true);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        // Create Login GUI instance
        loginUI = new LoginUI();
       //menu = new Menu();

        // DELETE THIS
        ArrayList<String> tempUser = new ArrayList<String>();
        tempUser.add("Alex");
        tempUser.add("middle");
        tempUser.add("Boudreau");
        tempUser.add("Username");
        tempUser.add("Password");
        tempUser.add("Q1");
        tempUser.add("A1");
        tempUser.add("Q2");
        tempUser.add("A2");

        // Create layout
        BorderPane root = new BorderPane();
        loginUI.getLoginUI(root); // Set default to show on startup to be Login

        // Create scene
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());

        // Set scene and show stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
// End of BudgetPlannerApp class