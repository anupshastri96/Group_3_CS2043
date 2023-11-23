package com.unb.budgetmaster.budgetmaster.presentation;

import com.unb.budgetmaster.budgetmaster.domain.implementation.CategoryImpl;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Menu extends Application {
    private Stage stage;
    private Scene scene;
    private Rectangle2D screenSize;
    //Menu Buttons + Label
    private Label menuLabel;
    private Button spendingButton, savingButton, analysisButton, historyButton;
    private double menuButtonPrefWidth, menuButtonPrefHeight;

    //Other Buttons
    private Button editButton;

    //Panes
    private BorderPane mainPane, centralPane, contentPane;

    //Boxes
    private VBox navigation;
    private HBox topBar;

    //Labels
    private Label displayedAmount;

    //Menu Groups
    private History history;

    //TableView

    private Scene spendingScene;
    private Scene savingScene;
    private Scene analysisScene;
    private Scene historyScene;

    public void start(Stage primaryStage) {
        //Creating the scene and panes
        screenSize = Screen.getPrimary().getVisualBounds();
        stage = primaryStage;

        //Create the menu buttons and label
        menuButtonPrefWidth = 120;
        menuButtonPrefHeight = 30;

        menuLabel = addLabel("Menu:", "Arial", 16);
        menuLabel.setStyle("-fx-font-weight: bold");

        spendingButton = addButton("Spending", menuButtonPrefWidth, menuButtonPrefHeight);
        spendingButton.setOnAction(event -> switchScenes(spendingScene, "Spending"));

        savingButton = addButton("Saving", menuButtonPrefWidth, menuButtonPrefHeight);
        savingButton.setOnAction(event -> switchScenes(savingScene, "Saving"));

        analysisButton = addButton("Analysis", menuButtonPrefWidth, menuButtonPrefHeight);
        analysisButton.setOnAction(event -> switchScenes(analysisScene, "Analysis"));

        historyButton = addButton("History", menuButtonPrefWidth, menuButtonPrefHeight);
        historyButton.setOnAction(event -> switchScenes(historyScene, "History"));

        //Create box for menu buttons
        navigation = new VBox();
        navigation.setAlignment(Pos.TOP_CENTER);
        navigation.setPrefWidth(300);
        navigation.setSpacing(20);
        navigation.setPadding(new Insets(5, 5, 5, 5));
        navigation.setStyle("-fx-border-color: black");
        navigation.setStyle("-fx-border-style: hidden solid hidden hidden");

        navigation.getChildren().addAll(menuLabel, spendingButton, savingButton, analysisButton, historyButton);

        //Create top bar for displayed amount
        topBar = new HBox();
        topBar.setAlignment(Pos.CENTER);
        topBar.setStyle("-fx-border-color: black");
        topBar.setStyle("-fx-border-style: hidden hidden solid hidden");

        displayedAmount = addLabel("Your Monthly Spending: $0.00", "Arial", 24);
        displayedAmount.setPadding(new Insets(50, 0, 50, 0));

        topBar.getChildren().addAll(displayedAmount);

        //Create content borderpane to be centered in the central pane
        contentPane = new BorderPane();
        

        //Create central borderpane to be centered in the main borperpane
        centralPane = new BorderPane();
        centralPane.setTop(topBar);
        centralPane.setCenter(contentPane);

        //Create main borderpane to be set in scene
        mainPane = new BorderPane();
        mainPane.setLeft(navigation);
        mainPane.setCenter(centralPane);

        //Sets the scene and displays it
        scene = new Scene(mainPane, screenSize.getWidth(), screenSize.getHeight());
        stage.setScene(scene);
        stage.setTitle("BudgetMaster - History");
        stage.setMaximized(true);
        stage.show();
    }

    public Scene createSpendingScene(Stage stage, Rectangle2D screensize) {
        Label displayedAmount;
        Label contentTitle;
        Label categoryTitle;
        Label percentSpendTitle;
        Label amountPaidTitle;
        Button editSpendings;
        Button newCategory;

        BorderPane borderPane = new BorderPane();
        BorderPane contentPane = new BorderPane();
        BorderPane contentTop = new BorderPane();
        VBox contentBox = new VBox();
        HBox contentColumns = new HBox();
        Scene scene = new Scene(borderPane, screensize.getWidth(), screensize.getHeight());

        //Navigation Buttons
        menuButtonPrefWidth = 120;
        menuButtonPrefHeight = 30;

        spendingButton = addButton("Spending", menuButtonPrefWidth, menuButtonPrefHeight);

        savingButton = addButton("Saving", menuButtonPrefWidth, menuButtonPrefHeight);
        savingButton.setOnAction(event -> switchScenes(savingScene, "Saving"));

        analysisButton = addButton("Analysis", menuButtonPrefWidth, menuButtonPrefHeight);
        analysisButton.setOnAction(event -> switchScenes(analysisScene, "Analysis"));

        historyButton = addButton("History", menuButtonPrefWidth, menuButtonPrefHeight);
        historyButton.setOnAction(event -> switchScenes(historyScene, "History"));

        //Add this line to event handler to make currently selected button transparent;
        spendingButton.setStyle("-fx-background-color: transparent");

        //Navigation Pane
        VBox navigation = new VBox();
        navigation.setAlignment(Pos.TOP_CENTER);
        navigation.setPrefWidth(300);
        navigation.setSpacing(20);
        navigation.setPadding(new Insets(5, 5, 5, 5));
        navigation.setStyle("-fx-border-color: black");
        navigation.setStyle("-fx-border-style: hidden solid hidden hidden");

        menuLabel = addLabel("Menu:", "Arial", 16);
        menuLabel.setStyle("-fx-font-weight: bold");

        navigation.getChildren().addAll(menuLabel, spendingButton, savingButton, analysisButton, historyButton);

        //Top Bar
        displayedAmount = addLabel("Your Monthly Spending: $0.00", "Arial", 24);
        displayedAmount.setPadding(new Insets(10, 0, 0, 0));

        //ContentBox
        contentTitle = addLabel("Categories of Spending", "Arial", 20);
        editSpendings = addButton("Edit", 60, 10);
        newCategory = addButton("Create new Category of Spending", 500, 10);

        //Content Top
        contentTop.setLeft(contentTitle);
        contentTop.setRight(editSpendings);

        //Content Columns
        categoryTitle = addCategoryColumn("Categories");
        percentSpendTitle = addCategoryColumn("% of Spendings");
        amountPaidTitle = addCategoryColumn("Amount Paid This Month");

        contentColumns.getChildren().addAll(categoryTitle, percentSpendTitle, amountPaidTitle);
        contentColumns.setAlignment(Pos.CENTER);

        HBox category1 = addSpending("Grocery\t\t\t\t\t0%\t\t\t\t\t$0.00");
        category1.setAlignment(Pos.CENTER);
        HBox category2 = addSpending("Rent\t\t\t\t\t\t0%\t\t\t\t\t$0.00");
        category2.setAlignment(Pos.CENTER);
        HBox category3 = addSpending("Gas\t\t\t\t\t\t0%\t\t\t\t\t$0.00");
        category3.setAlignment(Pos.CENTER);
        HBox category4 = addSpending("Phone Bill\t\t\t\t0%\t\t\t\t\t$0.00");
        category4.setAlignment(Pos.CENTER);

        contentBox.getChildren().addAll(contentTop, contentColumns, category1, category2, category3, category4);
        contentBox.setPadding(new Insets(15, 15, 15, 15));

        //Content Pane
        contentPane.setTop(displayedAmount);
        contentPane.setAlignment(displayedAmount, Pos.CENTER);
        contentPane.setCenter(contentBox);
        contentPane.setBottom(newCategory);

        //Border Pane
        borderPane.setLeft(navigation);
        borderPane.setCenter(contentPane);
        borderPane.setAlignment(contentBox, Pos.CENTER_LEFT);
        borderPane.setPadding(new Insets(0, 0, 5, 0));

        return scene;
    }

    public Scene createSavingScene(Stage stage, Rectangle2D screensize) {
        Label displayedAmount;
        Label contentTitle;
        Label goalTitle;
        Label percentGoalTitle;
        Label amountInvestedTitle;
        Button editSpendings;
        Button newGoal;

        BorderPane borderPane = new BorderPane();
        BorderPane contentPane = new BorderPane();
        BorderPane contentTop = new BorderPane();
        VBox contentBox = new VBox();
        HBox contentColumns = new HBox();
        Scene scene = new Scene(borderPane, screensize.getWidth(), screensize.getHeight());

        //Navigation Buttons
        menuButtonPrefWidth = 120;
        menuButtonPrefHeight = 30;

        spendingButton = addButton("Spending", menuButtonPrefWidth, menuButtonPrefHeight);
        spendingButton.setOnAction(event -> switchScenes(spendingScene, "Spending"));

        savingButton = addButton("Saving", menuButtonPrefWidth, menuButtonPrefHeight);

        analysisButton = addButton("Analysis", menuButtonPrefWidth, menuButtonPrefHeight);
        analysisButton.setOnAction(event -> switchScenes(analysisScene, "Analysis"));

        historyButton = addButton("History", menuButtonPrefWidth, menuButtonPrefHeight);
        historyButton.setOnAction(event -> switchScenes(historyScene, "History"));

        //Add this line to event handler to make currently selected button transparent;
        savingButton.setStyle("-fx-background-color: transparent");

        //Navigation Pane
        VBox navigation = new VBox();
        navigation.setAlignment(Pos.TOP_CENTER);
        navigation.setPrefWidth(300);
        navigation.setSpacing(20);
        navigation.setPadding(new Insets(5, 5, 5, 5));
        navigation.setStyle("-fx-border-color: black");
        navigation.setStyle("-fx-border-style: hidden solid hidden hidden");

        menuLabel = addLabel("Menu:", "Arial", 16);
        menuLabel.setStyle("-fx-font-weight: bold");

        navigation.getChildren().addAll(menuLabel, spendingButton, savingButton, analysisButton, historyButton);

        //Top Bar
        displayedAmount = addLabel("Your Monthly Savings: $0.00", "Arial", 24);
        displayedAmount.setPadding(new Insets(10, 0, 0, 0));

        //ContentBox
        contentTitle = addLabel("Saving Goals", "Arial", 20);
        editSpendings = addButton("Edit", 60, 10);
        newGoal = addButton("Create New Goal", 500, 10);

        //Content Top
        contentTop.setLeft(contentTitle);
        contentTop.setRight(editSpendings);

        //Content Columns
        goalTitle = addCategoryColumn("Goals");
        percentGoalTitle = addCategoryColumn("% to Achieving Goal");
        amountInvestedTitle = addCategoryColumn("Current Amount Invested To Goal");

        contentColumns.getChildren().addAll(goalTitle, percentGoalTitle, amountInvestedTitle);
        contentColumns.setAlignment(Pos.CENTER);

        HBox category1 = addSpending("Car\t\t\t\t\t\t0%\t\t\t\t$0.00");
        category1.setAlignment(Pos.CENTER);
        HBox category2 = addSpending("Mortgage\t\t\t\t\t0%\t\t\t\t$0.00");
        category2.setAlignment(Pos.CENTER);
        HBox category3 = addSpending("PS5\t\t\t\t\t\t0%\t\t\t\t$0.00");
        category3.setAlignment(Pos.CENTER);

        contentBox.getChildren().addAll(contentTop, contentColumns, category1, category2, category3);
        contentBox.setPadding(new Insets(15, 15, 15, 15));

        //Content Pane
        contentPane.setTop(displayedAmount);
        contentPane.setAlignment(displayedAmount, Pos.CENTER);
        contentPane.setCenter(contentBox);
        contentPane.setBottom(newGoal);

        //Border Pane
        borderPane.setLeft(navigation);
        borderPane.setCenter(contentPane);
        borderPane.setAlignment(contentBox, Pos.CENTER_LEFT);
        borderPane.setPadding(new Insets(0, 0, 5, 0));

        return scene;
    }

    public Scene createAnalysisScene(Stage stage, Rectangle2D screensize) {
        Label displayedAmount;

        BorderPane borderPane = new BorderPane();
        BorderPane contentPane = new BorderPane();
        Scene scene = new Scene(borderPane, screensize.getWidth(), screensize.getHeight());

        //Navigation Buttons
        menuButtonPrefWidth = 120;
        menuButtonPrefHeight = 30;

        spendingButton = addButton("Spending", menuButtonPrefWidth, menuButtonPrefHeight);
        spendingButton.setOnAction(event -> switchScenes(spendingScene, "Spending"));

        savingButton = addButton("Saving", menuButtonPrefWidth, menuButtonPrefHeight);
        savingButton.setOnAction(event -> switchScenes(savingScene, "Saving"));

        analysisButton = addButton("Analysis", menuButtonPrefWidth, menuButtonPrefHeight);

        historyButton = addButton("History", menuButtonPrefWidth, menuButtonPrefHeight);
        historyButton.setOnAction(event -> switchScenes(historyScene, "History"));

        //Add this line to event handler to make currently selected button transparent;
        analysisButton.setStyle("-fx-background-color: transparent");

        //Navigation Pane
        VBox navigation = new VBox();
        navigation.setAlignment(Pos.TOP_CENTER);
        navigation.setPrefWidth(300);
        navigation.setSpacing(20);
        navigation.setPadding(new Insets(5, 5, 5, 5));
        navigation.setStyle("-fx-border-color: black");
        navigation.setStyle("-fx-border-style: hidden solid hidden hidden");

        menuLabel = addLabel("Menu:", "Arial", 16);
        menuLabel.setStyle("-fx-font-weight: bold");

        navigation.getChildren().addAll(menuLabel, spendingButton, savingButton, analysisButton, historyButton);

        //Top Bar
        displayedAmount = addLabel("Analysis Page", "Arial", 24);
        displayedAmount.setPadding(new Insets(10, 0, 0, 0));


        //Content Pane
        contentPane.setTop(displayedAmount);
        contentPane.setAlignment(displayedAmount, Pos.CENTER);

        //Border Pane
        borderPane.setLeft(navigation);
        borderPane.setCenter(contentPane);
        borderPane.setPadding(new Insets(0, 0, 5, 0));

        return scene;
    }

    /*public BorderPane createHistoryPane(Label displayedAmount, double spendingsAmount, int numberOfCategories, String[] categoryNames) {
        BorderPane borderPane = new BorderPane();

        //Buttons
        Button editHistoryButton = addButton("Edit", 60, 10);
        Button loadMoreButton = addButton("Load More", 100, 10);;

        //Bottom HBox for buttons
        HBox bottomHBox = new HBox(10);
        bottomHBox.setPadding(new Insets(10));
        bottomHBox.getChildren().addAll(editHistoryButton, loadMoreButton);

        //borderPane.setTop();
        borderPane.setBottom(bottomHBox);

        Label contentTitle;
        Label historyTitle;

        displayedAmount.setText("Your Monthly Spending: " + String.valueOf(spendingsAmount));

        BorderPane pane;
        BorderPane contentTop = new BorderPane();
        VBox contentBox = new VBox();
        HBox contentColumns = new HBox();

        //ContentBox
        contentTitle = addLabel("History", "Arial", 20);
        loadMoreButton.setAlignment(Pos.CENTER);

        //Content Top
        contentTop.setLeft(contentTitle);
        //contentTop.setRight(editHistory);

        //Content Columns
        historyTitle = addCategoryColumn("List History of Transactions:");

        contentColumns.getChildren().addAll(historyTitle);
        contentColumns.setAlignment(Pos.CENTER_LEFT);

        HBox transaction1 = addSpending("Transaction 1");
        transaction1.setAlignment(Pos.CENTER);
        HBox transaction2 = addSpending("Transaction 2");
        transaction2.setAlignment(Pos.CENTER);
        HBox transaction3 = addSpending("Transaction 3");
        transaction3.setAlignment(Pos.CENTER);
        HBox transaction4 = addSpending("Transaction 4");
        transaction4.setAlignment(Pos.CENTER);

        contentBox.getChildren().addAll(contentTop, contentColumns, transaction1, transaction2, transaction3, transaction4);
        contentBox.setPadding(new Insets(15, 15, 15, 15));

        //Content Pane
        contentPane.setTop(displayedAmount);
        contentPane.setAlignment(displayedAmount, Pos.CENTER);
        contentPane.setCenter(contentBox);
        contentPane.setBottom(loadMoreButton);

        return pane;
    }*/

    public static void main(String[] args) {
    	launch(args);
    }

    public void switchScenes(Scene scene, String sceneName) {
        stage.setScene(scene);
        stage.setTitle("BudgetMaster - " + sceneName);
    }

    public Button addButton(String buttonText, double prefWidth, double prefHeight) {
        Button button = new Button(buttonText);
        button.setPrefSize(prefWidth, prefHeight);

        return button;
    }

    public Label addLabel(String labelName, String fontName, double fontSize) {
        Label label = new Label(labelName);
        Font font = new Font(fontName, fontSize);
        label.setFont(font);

        return label;
    }

    public Label addCategoryColumn(String columnName) {
        Label label = addLabel(columnName, "Arial", 20);
        //label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(5, 50, 5, 50));

        return label;
    }

    public HBox addSpending(String string) {
        HBox line = new HBox();
        Label spending;

        spending = addCategoryColumn(string);
        spending.setAlignment(Pos.CENTER_LEFT);

        line.getChildren().addAll(spending);

        return line;
    }
}