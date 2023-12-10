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
// previous attempt
//    public void displayTicketInfo(String passengerName, String bookingNumber, String airLineName, String paymentMethood, String cardNumber, String expirationDate, String destination, String economyClassPrice, String bussniessClassPrice, String firstClassPrice, String departingDate){
//        setPassengerDetails(passengerName, economyClassPrice, airLineName, destination, bookingNumber);
//        calculateTotalsAndSetPrices(economyClassPrice, bussniessClassPrice, firstClassPrice);
//        setPaymentMethod(paymentMethood, cardNumber, expirationDate);
//        setDepartingDate(departingDate);
//    }
    public void displayTicketInfo(String passengerName, String airLineName, PaymentMethod paymentMethood, String destination, String price, String departingDate){
    this.bookingConfirmation = new BookingConfirmation(passengerName, price, airLineName, paymentMethood, destination, departingDate);
    setPassengerDetails();
    calculateTotalsAndSetPrices();
    setPaymentMethod();
    setDepartingDate();
}
    private void setPaymentMethod(){
        if(bookingConfirmation.getPaypalPayment() != null){
            Image image = new Image(getClass().getResourceAsStream("PaypalMethod.png"));
            this.paymentMethood.setImage(image);
            this.nameOnCard.setText("");
            this.cardNumber.setText(bookingConfirmation.getPaypalPayment().getEmail());
            this.expirationDate.setText("");
        }
        else if(bookingConfirmation.getCardPayment() != null){
            Image image = new Image(getClass().getResourceAsStream("CardMethod.png"));
            this.paymentMethood.setImage(image);
            this.nameOnCard.setText(bookingConfirmation.getCardPayment().getOwnerName());
            this.expirationDate.setText(bookingConfirmation.getCardPayment().getExpirationDate());
            hideCardNumber();
            //this.cardNumber.setText(cardNumber);
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
    private void hideCardNumber(){
        String lastFourDigits = bookingConfirmation.getCardPayment().getNumber().substring(bookingConfirmation.getCardPayment().getNumber().length() - 4);
        this.cardNumber.setText("*".repeat(bookingConfirmation.getCardPayment().getNumber().length() - 4) + lastFourDigits);
    }
    private void setPassengerDetails(){
        this.passengerName.setText(bookingConfirmation.getName());
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