package flyxpert.flyxpert.Payment;

import flyxpert.flyxpert.Miscellaneous.SceneSwitcher;
import flyxpert.flyxpert.*;
import flyxpert.flyxpert.Booking.BookingConfirmationController;
import flyxpert.flyxpert.File.ReadExternalConfig;
import flyxpert.flyxpert.Flight.Flight;
import flyxpert.flyxpert.Passenger.Passenger;
import flyxpert.flyxpert.Passenger.PassengersController;
import flyxpert.flyxpert.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable{

    @FXML
    Button cardPaymentButton;
    @FXML
    Button paypalPaymentButton;
    @FXML
    Button payButton;
    @FXML
    ImageView cardIcon;
    @FXML
    ImageView paypalIcon;
    @FXML
    TextField cardOwnerNameTextField;
    @FXML
    TextField cardNumberTextField;
    @FXML
    TextField cardExpirationDateTextField;
    @FXML
    TextField cardCcvTextField;
    @FXML
    TextField paypalEmailTextField;
    @FXML
    Label cardExpirationDateLabel;
    @FXML
    Label cardNameOrPaypalEmailError;
    @FXML
    Label cardNumberError;
    @FXML
    Label cardExpirationDateError;
    @FXML
    Label cardCcvError;
    @FXML
    Label airlineLabel;
    @FXML
    Label arrivalDayLabel;
    @FXML
    Label flightTimeLabel;
    @FXML
    Label economySeatsNumberLabel;
    @FXML
    Label businessSeatsNumberLabel;
    @FXML
    Label firstClassSeatsNumberLabel;
    @FXML
    Label economySeatPriceLabel;
    @FXML
    Label businessSeatPriceLabel;
    @FXML
    Label firstClassSeatPriceLabel;
    @FXML
    Label subtotalLabel;
    @FXML
    Label totalLabel;

    private final Flight SELECTED_FLIGHT = Flight.flights.get(Flight.selectedFlightIndex);
    private int paymentSubtotal;
    private int paymentTotal;
    private int economySeatsCount = 0;
    private int businessSeatsCount = 0;
    private int firstClassSeatsCount = 0;
    Card card = new Card();
    Paypal paypal = new Paypal();
    public void initialize(URL url, ResourceBundle resourceBundle){
        // Setting the flight summary box info from the selected flight
        airlineLabel.setText(SELECTED_FLIGHT.getAirlineName());
        arrivalDayLabel.setText(SELECTED_FLIGHT.getArrivalDate().getDay()
                + "-" + SELECTED_FLIGHT.getArrivalDate().getMonth()
                + "-" + SELECTED_FLIGHT.getArrivalDate().getYear());
        flightTimeLabel.setText(SELECTED_FLIGHT.getDepartureTime().getHour() + ":" + SELECTED_FLIGHT.getDepartureTime().getMinutes()
                + " " + SELECTED_FLIGHT.getDepartureTime().getPeriod()
                + " - " + SELECTED_FLIGHT.getArrivalTime().getHour() + ":" + SELECTED_FLIGHT.getArrivalTime().getMinutes()
                + " " + SELECTED_FLIGHT.getArrivalTime().getPeriod());

        // Setting the payment summary from the passengers booked in the selected flight

        for (Passenger p : Passenger.passengers){
            String currentPassengerSeatType = p.getSeat().getType().getName();
            switch (currentPassengerSeatType) {
                case "economy" -> economySeatsCount++;
                case "business" -> businessSeatsCount++;
                case "firstClass" -> firstClassSeatsCount++;
            }
        }

        economySeatsNumberLabel.setText(String.valueOf(economySeatsCount));
        businessSeatsNumberLabel.setText(String.valueOf(businessSeatsCount));
        firstClassSeatsNumberLabel.setText(String.valueOf(firstClassSeatsCount));
        economySeatPriceLabel.setText("$" + String.valueOf(SELECTED_FLIGHT.getEconomyPrice()));
        businessSeatPriceLabel.setText("$" + String.valueOf(SELECTED_FLIGHT.getBusinessPrice()));
        firstClassSeatPriceLabel.setText("$" + String.valueOf(SELECTED_FLIGHT.getFirstClassPrice()));

        int economySubtotal = economySeatsCount * SELECTED_FLIGHT.getEconomyPrice();
        int businessSubtotal = businessSeatsCount * SELECTED_FLIGHT.getBusinessPrice();
        int firstClassSubtotal = firstClassSeatsCount * SELECTED_FLIGHT.getFirstClassPrice();
        paymentSubtotal = economySubtotal + businessSubtotal + firstClassSubtotal;
        subtotalLabel.setText("$" + String.valueOf(paymentSubtotal));

        paymentTotal = (int) card.calculateTotalPriceWithFees(paymentSubtotal);
        totalLabel.setText("$" + String.valueOf(paymentTotal));
    }

    private String currentPaymentMethod = "card";

    // Reading the constant image paths and the colors from an external configuration file (FlyXpert.properties) using class ReadExternalConfiguration
    final Image CARD_LOGO_WHITE = new Image(ReadExternalConfig.config.getProperty("cardIconWhitePath"));
    final Image CARD_LOGO_PURPLE = new Image(ReadExternalConfig.config.getProperty("cardIconPurplePath"));
    final Image PAYPAL_LOGO_WHITE = new Image(ReadExternalConfig.config.getProperty("paypalIconWhitePath"));
    final Image PAYPAL_LOGO_PURPLE = new Image(ReadExternalConfig.config.getProperty("paypalIconPurplePath"));
    final String MAIN_BLUE_COLOR = ReadExternalConfig.config.getProperty("mainBlueColor");
    final String MAIN_WHITE_COLOR = ReadExternalConfig.config.getProperty("mainWhiteColor");
    final String HOVER_BLUE_COLOR = ReadExternalConfig.config.getProperty("hoverBlueColor");
    final String HOVER_GREY_COLOR = ReadExternalConfig.config.getProperty("hoverGreyColor");


    public void onCardButtonClick(MouseEvent e){
        currentPaymentMethod = "card";
        cardPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_BLUE_COLOR, MAIN_WHITE_COLOR));
        paypalPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_WHITE_COLOR, MAIN_BLUE_COLOR));
        cardIcon.setImage(CARD_LOGO_WHITE);
        cardIcon.setFitWidth(20);
        cardIcon.setFitHeight(15);
        paypalIcon.setImage(PAYPAL_LOGO_PURPLE);
        paypalIcon.setFitWidth(19);
        paypalIcon.setFitHeight(20);
        cardOwnerNameTextField.setVisible(true);
        cardNumberTextField.setVisible(true);
        cardExpirationDateTextField.setVisible(true);
        cardExpirationDateLabel.setVisible(true);
        cardCcvTextField.setVisible(true);
        paypalEmailTextField.setVisible(false);
        cardCcvError.setVisible(false);
        cardNumberError.setVisible(false);
        cardExpirationDateError.setVisible(false);
        cardNameOrPaypalEmailError.setVisible(false);

        paymentTotal = (int) card.calculateTotalPriceWithFees(paymentSubtotal);
        totalLabel.setText("$" + String.valueOf(paymentTotal));
    }

    public void onPaypalButtonClick(MouseEvent e){
        currentPaymentMethod = "paypal";
        paypalPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_BLUE_COLOR, MAIN_WHITE_COLOR));
        cardPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_WHITE_COLOR, MAIN_BLUE_COLOR));
        cardIcon.setImage(CARD_LOGO_PURPLE);
        cardIcon.setFitWidth(26);
        cardIcon.setFitHeight(22);
        paypalIcon.setImage(PAYPAL_LOGO_WHITE);
        paypalIcon.setFitWidth(24);
        paypalIcon.setFitHeight(22);
        cardOwnerNameTextField.setVisible(false);
        cardNumberTextField.setVisible(false);
        cardExpirationDateTextField.setVisible(false);
        cardExpirationDateLabel.setVisible(false);
        cardCcvTextField.setVisible(false);
        cardCcvError.setVisible(false);
        cardNumberError.setVisible(false);
        cardExpirationDateError.setVisible(false);
        cardNameOrPaypalEmailError.setVisible(false);
        paypalEmailTextField.setVisible(true);

        paymentTotal = (int) paypal.calculateTotalPriceWithFees(paymentSubtotal);
        totalLabel.setText("$" + String.valueOf(paymentTotal));
    }

    // Regex patterns for validation on user inputs
    final String CARD_NAME_PATTERN = "[A-Z a-z]+";
    final String CARD_NUMBER_PATTERN = "\\d{16}";
    final String CARD_EXPIRATION_DATE_PATTERN = "\\d{2}/\\d{2}";
    final String CARD_CCV_PATTERN = "\\d{3}";
    final String EMAIL_PATTERN = "\\w+@\\w+\\.com";

    private Scene bookingConfirmationScene;
    private Stage bookingConfirmationStage;
    private Parent bookingConfirmationRoot;
    boolean userInputIsCorrect;
    public void onPayButtonClick(MouseEvent mouseEvent){
        String cardOwnerName = cardOwnerNameTextField.getText();
        String cardNumber = cardNumberTextField.getText();
        String cardExpirationDate = cardExpirationDateTextField.getText();
        String cardCcv = cardCcvTextField.getText();
        String paypalEmail = paypalEmailTextField.getText();

        if(currentPaymentMethod.equals("card")){
            // Checking if user input passes all validation tests
            userInputIsCorrect = true;
            userInputIsCorrect &= cardOwnerName.matches(CARD_NAME_PATTERN);
            userInputIsCorrect &= cardNumber.matches(CARD_NUMBER_PATTERN);
            userInputIsCorrect &= cardExpirationDate.matches(CARD_EXPIRATION_DATE_PATTERN);
            userInputIsCorrect &= cardCcv.matches(CARD_CCV_PATTERN);

            if(!cardOwnerNameTextField.getText().matches(CARD_NAME_PATTERN)){
                cardNameOrPaypalEmailError.setText("Please don't leave this field empty or enter a valid name with no special characters (Ex: Fly Xpert)");
                cardNameOrPaypalEmailError.setVisible(true);
            }
            else{
                cardNameOrPaypalEmailError.setVisible(false);
            }

            if(!cardNumberTextField.getText().matches(CARD_NUMBER_PATTERN)){
                cardNumberError.setVisible(true);
            }
            else{
                cardNumberError.setVisible(false);
            }

            if(!cardExpirationDateTextField.getText().matches(CARD_EXPIRATION_DATE_PATTERN)){
                cardExpirationDateError.setVisible(true);
            }
            else{
                cardExpirationDateError.setVisible(false);
            }

            if(!cardCcvTextField.getText().matches(CARD_CCV_PATTERN)){
                cardCcvError.setVisible(true);
            }
            else {
                cardCcvError.setVisible(false);
            }
        }
        else{
            if(!paypalEmail.matches(EMAIL_PATTERN)){
                cardNameOrPaypalEmailError.setText("Please don't leave this field empty or enter a valid email (Ex: FlyXpert@gmail.com)");
                cardNameOrPaypalEmailError.setVisible(true);
                userInputIsCorrect = false;
            }
            else{
                cardNameOrPaypalEmailError.setVisible(false);
                userInputIsCorrect = true;
            }
        }

        if(userInputIsCorrect){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BookingConfirmationController.fxml"));
            try{
                bookingConfirmationRoot = fxmlLoader.load();
            }
            catch(IOException ioException){
                System.out.println("Unable to load BookingConfirmationController.fxml");
            }

            BookingConfirmationController bookingConfirmationController = fxmlLoader.getController();
            Payment payment;
            if(currentPaymentMethod.equals("card")){
                card.setOwnerName(cardOwnerName);
                card.setNumber(cardNumber);
                card.setExpirationDate(cardExpirationDate);
                card.setCcv(cardCcv);
                payment = new Payment(paymentTotal, card);
            }
            else{
                paypal.setEmail(paypalEmail);
                payment = new Payment(paymentTotal, paypal);
            }

            for(int i = 0; i < Passenger.passengers.size(); i++)
            {
                Ticket ticket = new Ticket(i + 1,paymentTotal, "Not Expired", Passenger.passengers.get(i), Flight.flights.get(Flight.selectedFlightIndex));
                ticket.readTicket(ticket);
            }
            bookingConfirmationController.displayTicketInfo(User.currentUser, Flight.flights.get(Flight.selectedFlightIndex), payment, economySeatsCount, businessSeatsCount, firstClassSeatsCount);


            bookingConfirmationStage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
            bookingConfirmationScene = new Scene(bookingConfirmationRoot);
            bookingConfirmationScene.getStylesheets().add(Main.class.getResource("ButtonBookingClassStyle.css").toExternalForm());
            bookingConfirmationStage.setTitle(ReadExternalConfig.config.getProperty("systemTitle"));
            bookingConfirmationStage.setResizable(false);

            bookingConfirmationStage.setScene(bookingConfirmationScene);
            bookingConfirmationStage.show();
        }
    }

    public void onPayButtonMouseEntered(MouseEvent e) {
        payButton.setStyle(String.format("-fx-background-color: %s", HOVER_BLUE_COLOR));
    }

    public void onPayButtonMouseExited(MouseEvent e) {
        payButton.setStyle(String.format("-fx-background-color: %s", MAIN_BLUE_COLOR));
    }

    public void onCardButtonMouseEntered(MouseEvent e) {
        if(currentPaymentMethod.equals("card")){
            cardPaymentButton.setStyle(String.format("-fx-background-color: %s", HOVER_BLUE_COLOR));
        }
        else{
            cardPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", HOVER_GREY_COLOR, MAIN_BLUE_COLOR));
        }
    }

    public void onCardButtonMouseExited(MouseEvent e) {
        if(currentPaymentMethod.equals("card")){
            cardPaymentButton.setStyle(String.format("-fx-background-color: %s", MAIN_BLUE_COLOR));
        }
        else{
            cardPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_WHITE_COLOR, MAIN_BLUE_COLOR));
        }
    }

    public void onPaypalButtonMouseEntered(MouseEvent e) {
        if(currentPaymentMethod.equals("paypal")){
            paypalPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", HOVER_BLUE_COLOR, MAIN_WHITE_COLOR));
        }
        else{
            paypalPaymentButton.setStyle(String.format("-fx-background-color: %s", HOVER_GREY_COLOR));
        }
    }

    public void onPaypalButtonMouseExited(MouseEvent e) {
        if(currentPaymentMethod.equals("paypal")){
            paypalPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_BLUE_COLOR, MAIN_WHITE_COLOR));
        }
        else{
            paypalPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_WHITE_COLOR, MAIN_BLUE_COLOR));
        }
    }

    public void onFlightButtonPressed(MouseEvent event){
        // Reset passengers data
        Passenger.passengers.clear();
        PassengersController.curPassenger = 0;

        SceneSwitcher.switchScene(event, "SearchFlightPage", null);
    }
}
