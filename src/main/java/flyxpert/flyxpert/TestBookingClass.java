package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class TestBookingClass {
    @FXML
    private TextField passengerName;
    @FXML
    private TextField bookingNumber;
    @FXML
    private TextField price;
    @FXML
    private TextField airLineName;
    @FXML
    private TextField destination;
    @FXML
    private TextField nameOnCard;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField expirationDate;
    @FXML
    private TextField paymentMethood;
    private Scene scene;
    private Stage stage;
    private Parent root;
    public void test(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BookingConfirmationController.fxml"));
        root = fxmlLoader.load();
        BookingConfirmationController bookingConfirmationController = fxmlLoader.getController();
        bookingConfirmationController.bookingControl(passengerName.getText(), bookingNumber.getText(), price.getText(), airLineName.getText(), paymentMethood.getText(), cardNumber.getText(), expirationDate.getText());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("MoeStyle.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
}
