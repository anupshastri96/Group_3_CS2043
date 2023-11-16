package BudgetMaster;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Menu extends Application {
    private Stage stage;
    private Button spendingButton;
    private Button savingButton;
    private Button analysisButton;
    private Button historyButton;
    private Label menuLabel;
    private double navButtonPrefWidth;
    private double navButtonPrefHeight;
    private Scene spendingScene;
    private Scene savingScene;
    private Scene analysisScene;
    private Scene historyScene;

    public void start(Stage primaryStage) {
        //Creating the scene and panes
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        stage = primaryStage;
        
        /*
       	 * Organize the visual aspects
       	 */
        spendingScene = createSpendingScene(stage, screenSize);
        savingScene = createSavingScene(stage, screenSize);
        analysisScene = createAnalysisScene(stage, screenSize);
        historyScene = createHistoryScene(stage, screenSize);

        //Sets the scene and displays it
        stage.setMaximized(true);
        switchScenes(historyScene, "History");
        stage.show();
    }

    public Scene createSpendingScene(Stage stage, Rectangle2D screensize) {
        Label monthlySpendingText;

        BorderPane borderPane = new BorderPane();
        BorderPane topBar = new BorderPane();
        BorderPane content = new BorderPane();
        Scene scene = new Scene(borderPane, screensize.getWidth(), screensize.getHeight());

        //Navigation Buttons
        navButtonPrefWidth = 120;
        navButtonPrefHeight = 30;

        spendingButton = addButton("Spending", navButtonPrefWidth, navButtonPrefHeight);

        savingButton = addButton("Saving", navButtonPrefWidth, navButtonPrefHeight);
        savingButton.setOnAction(event -> switchScenes(savingScene, "Saving"));

        analysisButton = addButton("Analysis", navButtonPrefWidth, navButtonPrefHeight);
        analysisButton.setOnAction(event -> switchScenes(analysisScene, "Analysis"));

        historyButton = addButton("History", navButtonPrefWidth, navButtonPrefHeight);
        historyButton.setOnAction(event -> switchScenes(historyScene, "History"));

        //Add this line to event handler to make currently selected button transparent;
        spendingButton.setStyle("-fx-background-color: transparent");

        //Navigation Pane
        VBox navigation = new VBox();
        navigation.setAlignment(Pos.CENTER);
        navigation.setSpacing(5);
        navigation.setPadding(new Insets(5, 5, 5, 5));
        navigation.setStyle("-fx-border-color: black");
        navigation.setStyle("-fx-border-style: hidden solid hidden hidden");

        menuLabel = addLabel("Menu:", "Arial", 16);
        menuLabel.setStyle("-fx-font-weight: bold");

        navigation.getChildren().addAll(menuLabel, spendingButton, savingButton, analysisButton, historyButton);

        //Top Bar
        monthlySpendingText = addLabel("Your Monthly Spending: $0.00", "Arial", 24);

        topBar.setLeft(navigation);
        topBar.setCenter(monthlySpendingText);
        topBar.setStyle("-fx-border-color: black");

        borderPane.setTop(topBar);

        //Content
        

        return scene;
    }

    public Scene createSavingScene(Stage stage, Rectangle2D screensize) {
        Label monthlySavingsText;

        BorderPane borderPane = new BorderPane();
        BorderPane topBar = new BorderPane();
        Scene scene = new Scene(borderPane, screensize.getWidth(), screensize.getHeight());

        //Navigation Buttons
        navButtonPrefWidth = 120;
        navButtonPrefHeight = 30;

        spendingButton = addButton("Spending", navButtonPrefWidth, navButtonPrefHeight);
        spendingButton.setOnAction(event -> switchScenes(spendingScene, "Spending"));

        savingButton = addButton("Saving", navButtonPrefWidth, navButtonPrefHeight);

        analysisButton = addButton("Analysis", navButtonPrefWidth, navButtonPrefHeight);
        analysisButton.setOnAction(event -> switchScenes(analysisScene, "Analysis"));

        historyButton = addButton("History", navButtonPrefWidth, navButtonPrefHeight);
        historyButton.setOnAction(event -> switchScenes(historyScene, "History"));

        //Add this line to event handler to make currently selected button transparent;
        savingButton.setStyle("-fx-background-color: transparent");

        //Navigation Pane
        VBox navigation = new VBox();
        navigation.setAlignment(Pos.CENTER);
        navigation.setSpacing(5);
        navigation.setPadding(new Insets(5, 5, 5, 5));
        navigation.setStyle("-fx-border-color: black");
        navigation.setStyle("-fx-border-style: hidden solid hidden hidden");

        menuLabel = addLabel("Menu:", "Arial", 16);
        menuLabel.setStyle("-fx-font-weight: bold");

        navigation.getChildren().addAll(menuLabel, spendingButton, savingButton, analysisButton, historyButton);

        //Top Bar
        monthlySavingsText = addLabel("Your Monthly Savings: $0.00", "Arial", 24);

        topBar.setLeft(navigation);
        topBar.setCenter(monthlySavingsText);
        topBar.setStyle("-fx-border-color: black");

        borderPane.setTop(topBar);

        return scene;
    }

    public Scene createAnalysisScene(Stage stage, Rectangle2D screensize) {
        Label analysisLabelText;

        BorderPane borderPane = new BorderPane();
        BorderPane topBar = new BorderPane();
        Scene scene = new Scene(borderPane, screensize.getWidth(), screensize.getHeight());

        //Navigation Buttons
        navButtonPrefWidth = 120;
        navButtonPrefHeight = 30;

        spendingButton = addButton("Spending", navButtonPrefWidth, navButtonPrefHeight);
        spendingButton.setOnAction(event -> switchScenes(spendingScene, "Spending"));

        savingButton = addButton("Saving", navButtonPrefWidth, navButtonPrefHeight);
        savingButton.setOnAction(event -> switchScenes(savingScene, "Saving"));

        analysisButton = addButton("Analysis", navButtonPrefWidth, navButtonPrefHeight);

        historyButton = addButton("History", navButtonPrefWidth, navButtonPrefHeight);
        historyButton.setOnAction(event -> switchScenes(historyScene, "History"));

        //Add this line to event handler to make currently selected button transparent;
        analysisButton.setStyle("-fx-background-color: transparent");

        //Navigation Pane
        VBox navigation = new VBox();
        navigation.setAlignment(Pos.CENTER);
        navigation.setSpacing(5);
        navigation.setPadding(new Insets(5, 5, 5, 5));
        navigation.setStyle("-fx-border-color: black");
        navigation.setStyle("-fx-border-style: hidden solid hidden hidden");

        menuLabel = addLabel("Menu:", "Arial", 16);
        menuLabel.setStyle("-fx-font-weight: bold");

        navigation.getChildren().addAll(menuLabel, spendingButton, savingButton, analysisButton, historyButton);

        //Top Bar
        analysisLabelText = addLabel("Analysis Page", "Arial", 24);

        topBar.setLeft(navigation);
        topBar.setCenter(analysisLabelText);
        topBar.setStyle("-fx-border-color: black");

        borderPane.setTop(topBar);

        return scene;
    }

    public Scene createHistoryScene(Stage stage, Rectangle2D screensize) {
        Label balanceText;

        BorderPane borderPane = new BorderPane();
        BorderPane topBar = new BorderPane();
        Scene scene = new Scene(borderPane, screensize.getWidth(), screensize.getHeight());

        //Navigation Buttons
        navButtonPrefWidth = 120;
        navButtonPrefHeight = 30;

        spendingButton = addButton("Spending", navButtonPrefWidth, navButtonPrefHeight);
        spendingButton.setOnAction(event -> switchScenes(spendingScene, "Spending"));

        savingButton = addButton("Saving", navButtonPrefWidth, navButtonPrefHeight);
        savingButton.setOnAction(event -> switchScenes(savingScene, "Saving"));

        analysisButton = addButton("Analysis", navButtonPrefWidth, navButtonPrefHeight);
        analysisButton.setOnAction(event -> switchScenes(analysisScene, "Analysis"));

        historyButton = addButton("History", navButtonPrefWidth, navButtonPrefHeight);

        //Add this line to event handler to make currently selected button transparent;
        historyButton.setStyle("-fx-background-color: transparent");

        //Navigation Pane
        VBox navigation = new VBox();
        navigation.setAlignment(Pos.CENTER);
        navigation.setSpacing(5);
        navigation.setPadding(new Insets(5, 5, 5, 5));
        navigation.setStyle("-fx-border-color: black");
        navigation.setStyle("-fx-border-style: hidden solid hidden hidden");

        menuLabel = addLabel("Menu:", "Arial", 16);
        menuLabel.setStyle("-fx-font-weight: bold");

        navigation.getChildren().addAll(menuLabel, spendingButton, savingButton, analysisButton, historyButton);

        //Top Bar
        balanceText = addLabel("Remaining Balance:\n$0.00", "Arial", 24);

        topBar.setLeft(navigation);
        topBar.setCenter(balanceText);
        topBar.setStyle("-fx-border-color: black");

        borderPane.setTop(topBar);

        return scene;
    }

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
}
