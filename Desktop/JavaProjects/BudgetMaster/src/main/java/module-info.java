module com.unb.budgetmaster.budgetmaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.unb.budgetmaster.budgetmaster.presentation to javafx.fxml;
    exports com.unb.budgetmaster.budgetmaster.presentation;
}