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
//    @FXML
//    private Label numberOfPassenger;
    @FXML
    private Label economyClassPrice;
    @FXML
    private Label bussniessClassPrice;
    @FXML
    private Label firstClassPrice;
//    @FXML
//    private Label priceWithTaxes;
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
    @FXML
    Label economySeatsCount;
    @FXML
    Label businessSeatsCount;
    @FXML
    Label firstClassSeatsCount;
    @FXML
    Label timeOfTheTrip;
    @FXML
    Label FromTo;
    @FXML
    Label arrivalDate;
    private BookingConfirmation bookingConfirmation;
    int subTotalMoney = 0;
    int economyPriceMoney = 0;
    int businessPriceMoney = 0;
    int firstClassPriceMoney = 0;

    public void displayTicketInfo(User user, Flight currentFlight, Payment payment, int economySeatsCount, int businessSeatsCount, int firstClassSeatsCount){
        this.bookingConfirmation = new BookingConfirmation(user, currentFlight, payment, economySeatsCount, businessSeatsCount, firstClassSeatsCount);
        setPassengerDetails();
        calculateTotalsAndSetPrices(economySeatsCount, businessSeatsCount, firstClassSeatsCount);
        setPaymentMethod();
        setDepartingDate();
        setPassengersSeatsCount();
        this.bookingConfirmation.setSubTotalMoney(this.subTotalMoney);
        BookingConfirmation.bookingRecords.add(this.bookingConfirmation);
        calculateTimeOfTheTrip();
        setFromToTime();
        setArrivalDate();
    }
    private void setPaymentMethod(){
        if(bookingConfirmation.getPayment().getPaymentMethod() instanceof Paypal){
            Image image = new Image(getClass().getResourceAsStream("PaypalMethod.png"));
            this.paymentMethood.setImage(image);
            Paypal paypal = (Paypal) bookingConfirmation.getPayment().getPaymentMethod();
            this.nameOnCard.setText("");
            this.cardNumber.setText(paypal.getEmail());
            this.expirationDate.setText("");
            amountTotal.setText(Double.toString(paypal.calculateTotalPriceWithFees(subTotalMoney)));

        }
        else if(bookingConfirmation.getPayment().getPaymentMethod() instanceof Card){
            Image image = new Image(getClass().getResourceAsStream("CardMethod.png"));
            this.paymentMethood.setImage(image);
            Card card = (Card) bookingConfirmation.getPayment().getPaymentMethod();
            this.nameOnCard.setText(card.getOwnerName());
            this.expirationDate.setText(card.getExpirationDate());
            hideCardNumber(card);
            amountTotal.setText(Double.toString(card.calculateTotalPriceWithFees(subTotalMoney)));
        }
    }
    private void calculateTotalsAndSetPrices(int economySeatsCount, int businessSeatsCount, int firstClassSeatsCount){
        economyPriceMoney = (economySeatsCount * bookingConfirmation.getFlight().getEconomyPrice());
        businessPriceMoney = (businessSeatsCount * bookingConfirmation.getFlight().getBusinessPrice());
        firstClassPriceMoney = (firstClassSeatsCount * bookingConfirmation.getFlight().getFirstClassPrice());
        this.economyClassPrice.setText("$" + (economyPriceMoney));
        this.bussniessClassPrice.setText("$" + (businessPriceMoney));
        this.firstClassPrice.setText("$" + (firstClassPriceMoney));
        subTotalMoney = economyPriceMoney + businessPriceMoney + firstClassPriceMoney;
        this.subTotal.setText("$" + (subTotalMoney));
    }
    private void hideCardNumber(Card card){
        String lastFourDigits = card.getNumber().substring(card.getNumber().length() - 4);
        this.cardNumber.setText("*".repeat(card.getNumber().length() - 4) + lastFourDigits);
    }
    private void setPassengerDetails(){
        this.userName.setText(bookingConfirmation.getUser().getUserName());
        this.airLineName.setText(bookingConfirmation.getFlight().getAirlineName());
        this.airLineName1.setText("Flying through " + bookingConfirmation.getFlight().getAirlineName());
        this.destination.setText(bookingConfirmation.getFlight().getArrivalAirport().getName());
        this.price.setText("$" + bookingConfirmation.getFlight().getEconomyPrice());
        this.bookingNumber.setText(Integer.toString(bookingConfirmation.getBookingNumber()));
    }
    private void setDepartingDate(){
        this.departingDate.setText(bookingConfirmation.getFlight().getDepartureDate().getDay() + "/"
                + bookingConfirmation.getFlight().getDepartureDate().getMonth() + "/"
                + bookingConfirmation.getFlight().getDepartureDate().getYear());
    }
    private void setPassengersSeatsCount(){
        this.economySeatsCount.setText(String.valueOf(this.bookingConfirmation.getEconomySeatsCount()));
        this.businessSeatsCount.setText(String.valueOf(this.bookingConfirmation.getBusinessSeatsCount()));
        this.firstClassSeatsCount.setText(String.valueOf(this.bookingConfirmation.getFirstClassSeatsCount()));
    }
    private void calculateTimeOfTheTrip(){
        int timeTaken = (Integer.parseInt(bookingConfirmation.getFlight().getArrivalTime().getHour()))  + 12 - (Integer.parseInt(bookingConfirmation.getFlight().getDepartureTime().getHour()));
        this.timeOfTheTrip.setText(timeTaken + " Hours " + 0 + " minutes ");
    }
    private void setFromToTime(){
        this.FromTo.setText("From " + bookingConfirmation.getFlight().getDepartureTime().toString()
            + " To " + bookingConfirmation.getFlight().getArrivalTime().toString());
    }
    private void setArrivalDate(){
        arrivalDate.setText(bookingConfirmation.getFlight().getArrivalDate().toString());
    }
}
