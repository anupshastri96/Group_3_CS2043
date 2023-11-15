package com.unb.budgetmaster.budgetmaster2043;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BudgetMasterController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}