package com.unb.budgetmaster.presentation;

import com.unb.budgetmaster.data.implementation.DatabaseImpl;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BudgetPlannerApp extends Application {

    // UI Instances
    private LoginUI loginUI;
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

        // Create layout
        Pane root = new Pane();
        loginUI.getLoginUI(root); // Set default to show on startup to be Login

        // Create scene
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        DatabaseImpl database = new DatabaseImpl();
        database.connectDatabase();

        // Set scene and show stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
// End of BudgetPlannerApp class