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
import java.time.LocalDate;
import java.util.Random;

public class BookingConfirmationController {
    @FXML
    private Label passengerName;
    @FXML
    private Label bookingNumber;
    @FXML
    private Label numberOfPassenger;
    @FXML
    private Label economyClassPrice;
    @FXML
    private Label bussniessClassPrice;
    @FXML
    private Label firstClassPrice;
    @FXML
    private Label priceWithTaxes;
    @FXML
    private Label price;
    @FXML
    private Label airLineName;
    @FXML
    private Label airLineName1;
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
    @FXML
    private Label subTotal;
    @FXML
    private Label amountTotal;
    @FXML
    private Label departingDate;
    private BookingConfirmation bookingConfirmation;
    public void displayTicketInfo(String passengerName, String bookingNumber, String airLineName, String paymentMethood, String cardNumber, String expirationDate, String destination, String economyClassPrice, String bussniessClassPrice, String firstClassPrice, String departingDate){
        setPassengerDetails(passengerName, economyClassPrice, airLineName, destination, bookingNumber);
        calculateTotalsAndSetPrices(economyClassPrice, bussniessClassPrice, firstClassPrice);
        setPaymentMethod(paymentMethood, cardNumber, expirationDate);
        setDepartingDate(departingDate);
    }
    private void setPaymentMethod(String paymentMethood, String cardNumber, String expirationDate){
        if(paymentMethood.equals("Paypal")){
            Image image = new Image(getClass().getResourceAsStream("PaypalMethod.png"));
            this.paymentMethood.setImage(image);
            this.nameOnCard.setText("");
            this.cardNumber.setText("example@zhbook.com");
            this.expirationDate.setText("");
        }
        else{
            Image image = new Image(getClass().getResourceAsStream("CardMethod.png"));
            this.paymentMethood.setImage(image);
            this.nameOnCard.setText(this.passengerName.getText());
            this.cardNumber.setText(cardNumber);
            this.expirationDate.setText(expirationDate);
        }
        hideCardNumber(this.cardNumber.getText());
    } //testing
    private void calculateTotalsAndSetPrices(String economyClassPrice, String bussniessClassPrice, String firstClassPrice){
        float subToatl = (Float.parseFloat(economyClassPrice) + Float.parseFloat(bussniessClassPrice) + Float.parseFloat(firstClassPrice));
        float taxes = (Float.parseFloat(economyClassPrice) + Float.parseFloat(bussniessClassPrice) + Float.parseFloat(firstClassPrice)) * 0.09F;
        this.economyClassPrice.setText("$" + economyClassPrice);
        this.bussniessClassPrice.setText("$" + bussniessClassPrice);
        this.firstClassPrice.setText("$" + firstClassPrice);
        this.subTotal.setText("$" + subToatl);
        this.priceWithTaxes.setText("$" + taxes);
        this.amountTotal.setText("$" + (subToatl + taxes));
    }
    private void hideCardNumber(String cardNumber){
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        this.cardNumber.setText("*".repeat(cardNumber.length() - 4) + lastFourDigits);
    }
    private void setPassengerDetails(String passengerName, String price, String airLineName, String destination, String bookingNumber){
        this.passengerName.setText(passengerName);
        this.destination.setText(destination);
        this.price.setText("$" + price);
        this.airLineName.setText(airLineName);
        this.airLineName1.setText("Flying through " + airLineName);
        this.bookingNumber.setText(bookingNumber);
    }
    private void setDepartingDate(String departingDate){
        this.departingDate.setText(departingDate);
    }
}