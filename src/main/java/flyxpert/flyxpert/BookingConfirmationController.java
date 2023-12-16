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
        this.bookingConfirmation = new BookingConfirmation(user.getUserName(), String.valueOf(currentFlight.getFlightNumber()), currentFlight.getAirLineName(), currentFlight.getDepartureAirport().getName(), currentFlight.getArrivalAirport().getName(), currentFlight.getDepartureDate().toString(), currentFlight.getArrivalDate().toString(), currentFlight.getDepartureTime().toString(), currentFlight.getArrivalTime().toString(), String.valueOf(payment.getPaymentID()), payment.toString(), String.valueOf(payment.getPaymentAmount()), payment.getPaymentStatus(), economySeatsCount, businessSeatsCount, firstClassSeatsCount);
        setPassengerDetails(user, currentFlight);
        calculateTotalsAndSetPrices(economySeatsCount, businessSeatsCount, firstClassSeatsCount, currentFlight);
        setPaymentMethod(payment.getPaymentMethod());
        setDepartingDate(currentFlight);
        setPassengersSeatsCount();
        this.bookingConfirmation.setSubTotalMoney(this.subTotalMoney);
        calculateTimeOfTheTrip(currentFlight);
        setFromToTime(currentFlight);
        setArrivalDate(currentFlight);
        this.bookingConfirmation.setAvaliableSeats(currentFlight);
        this.bookingConfirmation.setBoookingPassengers(Passenger.passengers);
        BookingConfirmation.bookingRecords.add(this.bookingConfirmation);

        UserTrie userTrie = UserTrie.getInstance();
        userTrie.insertBooking(user.getUserName(), BookingConfirmation.bookingRecords.size() - 1);

    }
    private void setPaymentMethod(PaymentMethod payment){
        if(payment instanceof Paypal){
            Image image = new Image(getClass().getResourceAsStream("PaypalMethod.png"));
            this.paymentMethood.setImage(image);
            Paypal paypal = (Paypal) payment;
            this.nameOnCard.setText("");
            this.cardNumber.setText(paypal.getEmail());
            this.expirationDate.setText("");
            amountTotal.setText(Double.toString(paypal.calculateTotalPriceWithFees(subTotalMoney)));

        }
        else if(payment instanceof Card){
            Image image = new Image(getClass().getResourceAsStream("CardMethod.png"));
            this.paymentMethood.setImage(image);
            Card card = (Card) payment;
            this.nameOnCard.setText(card.getOwnerName());
            this.expirationDate.setText(card.getExpirationDate());
            hideCardNumber(card);
            amountTotal.setText(Double.toString(card.calculateTotalPriceWithFees(subTotalMoney)));
        }
    }

    private void calculateTotalsAndSetPrices(int economySeatsCount, int businessSeatsCount, int firstClassSeatsCount, Flight currentFlight){
        economyPriceMoney = (economySeatsCount * currentFlight.getEconomyPrice());
        businessPriceMoney = (businessSeatsCount * currentFlight.getBusinessPrice());
        firstClassPriceMoney = (firstClassSeatsCount * currentFlight.getFirstClassPrice());
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

    private void setPassengerDetails(User user, Flight currentFlight){
        this.userName.setText(user.getUserName());
        this.airLineName.setText(currentFlight.getAirlineName());
        this.airLineName1.setText("Flying through " + currentFlight.getAirlineName());
        this.destination.setText(currentFlight.getArrivalAirport().getName());
        this.price.setText("$" + currentFlight.getEconomyPrice());
        this.bookingNumber.setText(Integer.toString(bookingConfirmation.getBookingNumber()));
    }
    private void setDepartingDate(Flight currentFlight){
        this.departingDate.setText(currentFlight.getDepartureDate().toString());
    }
    private void setPassengersSeatsCount(){
        this.economySeatsCount.setText(String.valueOf(this.bookingConfirmation.getEconomySeatsCount()));
        this.businessSeatsCount.setText(String.valueOf(this.bookingConfirmation.getBusinessSeatsCount()));
        this.firstClassSeatsCount.setText(String.valueOf(this.bookingConfirmation.getFirstClassSeatsCount()));
    }

    private void calculateTimeOfTheTrip(Flight currentFlight){
        int timeTaken = (Integer.parseInt(currentFlight.getArrivalTime().getHour()))  + 12 - (Integer.parseInt(currentFlight.getDepartureTime().getHour()));
        this.timeOfTheTrip.setText(timeTaken + " Hours " + 0 + " minutes ");
    }
    private void setFromToTime(Flight currentFlight){
        this.FromTo.setText("From " + currentFlight.getDepartureTime().toString()
            + " To " + currentFlight.getArrivalTime().toString());
    }
    private void setArrivalDate(Flight currentFlight){
        arrivalDate.setText(currentFlight.getArrivalDate().toString());
    }
}
