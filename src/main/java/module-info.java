module com.unb.budgetmaster.budgetmaster2043 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.unb.budgetmaster.budgetmaster2043 to javafx.fxml;
    exports com.unb.budgetmaster.budgetmaster2043;
}