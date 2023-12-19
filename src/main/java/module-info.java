module flyxpert.flyxpert {
    requires javafx.controls;
    requires javafx.fxml;
        requires java.xml;
        requires javafx.graphics;


        opens flyxpert.flyxpert to javafx.fxml;
    exports flyxpert.flyxpert;
    exports flyxpert.flyxpert.Admin;
    opens flyxpert.flyxpert.Admin to javafx.fxml;
    exports flyxpert.flyxpert.Flight;
    opens flyxpert.flyxpert.Flight to javafx.fxml;
    exports flyxpert.flyxpert.User;
    opens flyxpert.flyxpert.User to javafx.fxml;
    exports flyxpert.flyxpert.Payment;
    opens flyxpert.flyxpert.Payment to javafx.fxml;
    exports flyxpert.flyxpert.Seat;
    opens flyxpert.flyxpert.Seat to javafx.fxml;
    exports flyxpert.flyxpert.Passenger;
    opens flyxpert.flyxpert.Passenger to javafx.fxml;
    exports flyxpert.flyxpert.File;
    opens flyxpert.flyxpert.File to javafx.fxml;
    exports flyxpert.flyxpert.Booking;
    opens flyxpert.flyxpert.Booking to javafx.fxml;
    exports flyxpert.flyxpert.Miscellaneous;
    opens flyxpert.flyxpert.Miscellaneous to javafx.fxml;
}