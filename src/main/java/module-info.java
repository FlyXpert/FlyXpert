module flyxpert.flyxpert {
    requires javafx.controls;
    requires javafx.fxml;
        requires java.xml;
        requires javafx.graphics;


        opens flyxpert.flyxpert to javafx.fxml;
    exports flyxpert.flyxpert;
}