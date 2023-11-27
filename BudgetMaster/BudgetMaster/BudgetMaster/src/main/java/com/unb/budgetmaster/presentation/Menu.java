package com.unb.budgetmaster.presentation;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Menu {
    // Buttons that need to be tracked outside of functions
    private Button historyButton;
    private Button selectedButton; // Track the currently selected button

    // Menu Sections
    //private Analysis analysis; //ADD THIS IN
    private History history;

    // UI Elements
    private Label titleLabel;
    private Label contentLabel;
    private VBox contentContainer;

    public void getContentMenu(Pane root) {
        // Initialize our Menu classes
        //analysis = new Analysis(); // ADD THIS IN
        history = new History();

        // Initialize Menu UI components
        initializeUI();

        // Create layout
        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(createMenuBar());
        mainPane.setCenter(createContentArea());

        // Set default selection to "History" button
        setButtonSelected(historyButton);

        root.getChildren().clear();
        root.getChildren().add(mainPane);
    }

    private void initializeUI() {
        titleLabel = new Label("Menu");
        titleLabel.setStyle("-fx-font-weight: bold");
        titleLabel.setStyle("-fx-font-size: 30");

        Label contentLabel = new Label("Current Balance: "); // Default content label
        contentLabel.setStyle("-fx-font-weight: bold");
        contentLabel.setStyle("-fx-font-size: 24");

        VBox contentContainer = new VBox(10);
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
// End of Menu class