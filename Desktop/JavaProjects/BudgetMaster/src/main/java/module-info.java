module com.unb.budgetmaster.budgetmaster {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.unb.budgetmaster.budgetmaster to javafx.fxml;
    exports com.unb.budgetmaster.budgetmaster;
}