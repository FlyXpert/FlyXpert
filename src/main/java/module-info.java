module flyxpert.flyxpert {
    requires javafx.controls;
    requires javafx.fxml;


    opens flyxpert.flyxpert to javafx.fxml;
    exports flyxpert.flyxpert;
}