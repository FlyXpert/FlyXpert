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
    private Label userName;
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

    public void displayTicketInfo(String userName, String airLineName, Payment payment, String destination, String price, String departingDate){
        this.bookingConfirmation = new BookingConfirmation(userName, price, airLineName, payment, destination, departingDate);
        setPassengerDetails();
        calculateTotalsAndSetPrices();
        setPaymentMethod();
        setDepartingDate();
    }
    private void setPaymentMethod(){
        if(bookingConfirmation.getPayment().getPaymentMethod() instanceof Paypal){
            Image image = new Image(getClass().getResourceAsStream("PaypalMethod.png"));
            this.paymentMethood.setImage(image);
            Paypal paypal = (Paypal) bookingConfirmation.getPayment().getPaymentMethod();
            this.nameOnCard.setText("");
            this.cardNumber.setText(paypal.getEmail());
            this.expirationDate.setText("");
        }
        else if(bookingConfirmation.getPayment().getPaymentMethod() instanceof Card){
            Image image = new Image(getClass().getResourceAsStream("CardMethod.png"));
            this.paymentMethood.setImage(image);
            Card card = (Card) bookingConfirmation.getPayment().getPaymentMethod();
            this.nameOnCard.setText(card.getOwnerName());
            this.expirationDate.setText(card.getExpirationDate());
            hideCardNumber(card);
        }
    }
    private void calculateTotalsAndSetPrices(){
        float subToatl = (Float.parseFloat(bookingConfirmation.getPrice().getBussniessClassPrice()) + Float.parseFloat(bookingConfirmation.getPrice().getBussniessClassPrice()) + Float.parseFloat(bookingConfirmation.getPrice().getFirstClassPrice()));
        float taxes = (Float.parseFloat(bookingConfirmation.getPrice().getBussniessClassPrice()) + Float.parseFloat(bookingConfirmation.getPrice().getBussniessClassPrice()) + Float.parseFloat(bookingConfirmation.getPrice().getFirstClassPrice())) * 0.09F;
        this.economyClassPrice.setText("$" + bookingConfirmation.getPrice().getEconomyClassPrice());
        this.bussniessClassPrice.setText("$" + bookingConfirmation.getPrice().getBussniessClassPrice());
        this.firstClassPrice.setText("$" + bookingConfirmation.getPrice().getFirstClassPrice());
        this.subTotal.setText("$" + subToatl);
        this.priceWithTaxes.setText("$" + taxes);
        this.amountTotal.setText("$" + (subToatl + taxes));
    }
    private void hideCardNumber(Card card){
        String lastFourDigits = card.getNumber().substring(card.getNumber().length() - 4);
        this.cardNumber.setText("*".repeat(card.getNumber().length() - 4) + lastFourDigits);
    }
    private void setPassengerDetails(){
        this.userName.setText(bookingConfirmation.getName());
        this.airLineName.setText(bookingConfirmation.getAirLineName());
        this.airLineName1.setText("Flying through " + bookingConfirmation.getAirLineName());
        this.destination.setText(bookingConfirmation.getDestination());
        this.price.setText("$" + bookingConfirmation.getPrice().getEconomyClassPrice());
        this.bookingNumber.setText(Integer.toString(bookingConfirmation.getBookingNumber()));
    }
    private void setDepartingDate(){
        this.departingDate.setText(bookingConfirmation.getDepartingDate());
    }
}