package com.unb.budgetmaster.budgetmaster.presentation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BudgetPlannerApp extends Application {

    private BorderPane root;
    private Label titleLabel;
    private Label contentLabel;
    private VBox contentContainer;
    private Button historyButton; // Store the historyButton as a class-level variable
    private Button selectedButton; // Track the currently selected button
    private Rectangle2D screenSize;

    //Menu Sections
    private Spending spending;
    private Savings savings;
    private History history;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BudgetMaster");
        primaryStage.setMaximized(true);
        screenSize = Screen.getPrimary().getVisualBounds();

        // Initialize our Menu classes
        spending = new Spending();
        savings = new Savings();
        history = new History();

        // Initialize UI components
        initializeUI();

        // Create layout
        root = new BorderPane();
        root.setLeft(createMenuBar());
        root.setCenter(createContentArea());

        // Set default selection to "History" button
        setButtonSelected(historyButton);

        // Create scene
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());

        // Set scene and show stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeUI() {
        titleLabel = new Label("Menu");
        titleLabel.setStyle("-fx-font-weight: bold");
        titleLabel.setStyle("-fx-font-size: 30");

        contentLabel = new Label("Current Balance"); // Default content label
        contentLabel.setStyle("-fx-font-weight: bold");
        contentLabel.setStyle("-fx-font-size: 24");

        contentContainer = new VBox(10);
        contentContainer.setPadding(new Insets(10));
    }

    private VBox createMenuBar() {
        VBox menuBar = new VBox(10);
        menuBar.setPadding(new Insets(10));
        menuBar.setStyle("-fx-background-color: #e0e0e0");
        menuBar.setAlignment(Pos.TOP_CENTER);
        menuBar.setMinWidth(200);

        Button spendingsButton = createMenuButton("Spendings");
        Button savingsButton = createMenuButton("Savings");
        Button analysisButton = createMenuButton("Analysis");
        historyButton = createMenuButton("History"); // Store the historyButton as an instance variable since it's default

        menuBar.getChildren().addAll(titleLabel, spendingsButton, savingsButton, analysisButton, historyButton);

        return menuBar;
    }

    private VBox createContentArea() {
        VBox contentArea = new VBox(10);
        VBox contentLabelBox = new VBox(30);
        contentLabelBox.setPadding(new Insets(30));
        contentLabelBox.setStyle("-fx-border-color: black");
        contentLabelBox.setStyle("-fx-border-style: hidden hidden solid hidden");
        contentLabelBox.getChildren().addAll(contentLabel);
        contentLabelBox.setAlignment(Pos.CENTER);
        contentArea.getChildren().addAll(contentLabelBox, contentContainer);
        contentArea.setAlignment(Pos.TOP_CENTER);
        return contentArea;
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);

        // Set button to be transparent by default
        button.setStyle("-fx-background-color: transparent");

        // Set button event
        button.setOnAction(event -> {
            handleMenuButtonClick(text);
            setButtonSelected(button);
        });

        return button;
    }

    private void handleMenuButtonClick(String menuOption) {
        // Clear existing content
        contentContainer.getChildren().clear();

        // Add specific content for the selected menu option
        if(menuOption.equals("Spendings")) {
            spending.getContent(contentLabel, contentContainer);
        }
        
        if(menuOption.equals("Savings")) {
            savings.getContent(contentLabel, contentContainer);
        }

        if(menuOption.equals("Analysis")) {

        }

        if(menuOption.equals("History")) {
            history.getContent(contentLabel, contentContainer);
        }
    }

    private void setButtonSelected(Button selectedButton) {
        // Reset style for the previously selected button
        if (this.selectedButton != null) {
            this.selectedButton.setStyle("-fx-background-color: transparent");
        }

        // Set the selected button style
        selectedButton.setStyle("-fx-background-color: #a0a0a0");

        // Update the currently selected button
        this.selectedButton = selectedButton;
    }
}