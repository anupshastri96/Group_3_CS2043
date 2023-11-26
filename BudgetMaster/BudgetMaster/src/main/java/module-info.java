module com.unb.budgetmaster {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;

    opens com.unb.budgetmaster.presentation to javafx.fxml;
    exports com.unb.budgetmaster.presentation;
}