package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingConfirmationController {
    @FXML
    private Label passengerName;
    @FXML
    private Label bookingNumber;
    @FXML
    private Label price;
    @FXML
    private Label airLineName;
    @FXML
    private Label destination;
    @FXML
    private Label nameOnCard;
    @FXML
    private Label cardNumber;
    @FXML
    private Label expirationDate;
    @FXML
    ImageView paymentMethood;

    private BookingConfirmation bookingConfirmation;
//    public void setBookingConfirmation(BookingConfirmation bookingConfirmation) {
//        this.bookingConfirmation = new BookingConfirmation(bookingConfirmation);
//    }

    public void bookingControl(String passengerName, String bookingNumber, String price, String airLineName, String paymentMethood, String cardNumber, String expirationDate){
        this.passengerName.setText(passengerName);
        this.bookingNumber.setText(bookingNumber);
        this.price.setText("$" + price);
        this.airLineName.setText(airLineName);
        if(paymentMethood.equals("Paypal")){
            Image image = new Image(getClass().getResourceAsStream("PaypalMethod.png"));
            this.paymentMethood.setImage(image);
            this.nameOnCard.setText("");
            this.cardNumber.setText("");
            this.expirationDate.setText("");
        }
        else{
            Image image = new Image(getClass().getResourceAsStream("CardMethod.png"));
            this.paymentMethood.setImage(image);
            this.nameOnCard.setText(this.passengerName.getText());
            this.cardNumber.setText(cardNumber);
            this.expirationDate.setText(expirationDate);
        }
    }
}
