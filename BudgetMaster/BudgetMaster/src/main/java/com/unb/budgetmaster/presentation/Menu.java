package com.unb.budgetmaster.presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Menu {
    // Buttons that need to be tracked outside of functions
    private Button historyButton;
    private Button selectedButton; // Track the currently selected button

    // Menu Sections
    private Spending spending;
    private Savings savings;
    private Analysis analysis;
    private History history;
    private Account account;

    // UI Elements
    private Label titleLabel;
    private Label contentLabel;
    private VBox contentContainer;
    private VBox contentArea;
    private VBox contentLabelBox;
    private VBox menuBar;

    public void getContentMenu(BorderPane root) {
        // Initialize our Menu classes
        spending = new Spending();
        savings = new Savings();
        analysis = new Analysis();
        history = new History();
        account = new Account();

        // Initialize Menu UI components
        initializeUI();

        // Create layout
        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(createMenuBar(root));
        mainPane.setCenter(createContentArea());

        // Set default selection to "History" button
        setButtonSelected(historyButton);

        root.getChildren().clear();
        root.setCenter(mainPane);
    }

    private void initializeUI() {
        titleLabel = new Label("Menu");
        titleLabel.setStyle("-fx-font-weight: bold");
        titleLabel.setStyle("-fx-font-size: 30");

        contentLabel = new Label("Current Balance: "); // Default content label
        contentLabel.setStyle("-fx-font-weight: bold");
        contentLabel.setStyle("-fx-font-size: 24");

        contentContainer = new VBox(10);
        contentContainer.setPadding(new Insets(10));
        contentContainer.getChildren().add(contentLabel);
    }

    private VBox createMenuBar(BorderPane root) {
        menuBar = new VBox(10);
        menuBar.setPadding(new Insets(10));
        menuBar.setStyle("-fx-background-color: #e0e0e0");
        menuBar.setAlignment(Pos.TOP_CENTER);
        menuBar.setMinWidth(200);

        Button spendingsButton = createMenuButton("Spendings", root);
        Button savingsButton = createMenuButton("Savings", root);
        Button analysisButton = createMenuButton("Analysis", root);
        Button accountButton = createMenuButton("Account", root);
        historyButton = createMenuButton("History", root);

        menuBar.getChildren().addAll(titleLabel, spendingsButton, savingsButton, analysisButton, historyButton, accountButton);
        return menuBar;
    }

    private VBox createContentArea() {
        contentArea = new VBox(10);
        contentLabelBox = new VBox(30);
        contentLabelBox.setPadding(new Insets(30));
        contentLabelBox.setStyle("-fx-border-color: black");
        contentLabelBox.setStyle("-fx-border-style: hidden hidden solid hidden");
        contentLabelBox.getChildren().addAll(contentLabel);
        contentLabelBox.setAlignment(Pos.CENTER);
        contentArea.getChildren().addAll(contentLabelBox, contentContainer);
        contentArea.setAlignment(Pos.TOP_CENTER);
        return contentArea;
    }

    private Button createMenuButton(String text, BorderPane root) {
        Button button = new Button(text);

        // Set button to be transparent by default
        button.setStyle("-fx-background-color: transparent");

        // Set button event
        button.setOnAction(event -> {
            handleMenuButtonClick(text, root);
            setButtonSelected(button);
        });
        return button;
    }

    private void handleMenuButtonClick(String menuOption, BorderPane root) {
        // Clear existing content
        contentContainer.getChildren().clear();

        // Handle the menu button click
        for (Node node : menuBar.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (button.getText().equals(menuOption)) {
                    // Found the clicked button
                    setButtonSelected(button);
                    break;
                }
            }
        }

        // Handle content based on the clicked menu option
        if (menuOption.equals("Spendings")) {
            spending.getContent(contentLabel, contentContainer);
        } else if (menuOption.equals("Savings")) {;
            savings.getContent(contentLabel, contentContainer);
        } else if (menuOption.equals("Analysis")) {
            analysis.getContent(contentLabel, contentContainer);
        } else if (menuOption.equals("History")) {
            history.getContent(contentLabel, contentContainer);
        } else if (menuOption.equals("Account")) {
            account.getContent(contentLabel, contentContainer, root);
        }
        return;
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
        return;
    }
}
// End of Menu class