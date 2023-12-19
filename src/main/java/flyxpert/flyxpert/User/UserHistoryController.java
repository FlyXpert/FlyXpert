package flyxpert.flyxpert.User;

import flyxpert.flyxpert.Booking.BookingConfirmation;
import flyxpert.flyxpert.Miscellaneous.SceneSwitcher;
import flyxpert.flyxpert.Passenger.Passenger;
import flyxpert.flyxpert.Passenger.PassengersController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

import java.net.URL;
import java.util.ResourceBundle;

public class UserHistoryController implements Initializable {

    @FXML
    VBox bookingsVbox;
    @FXML
    Label youHaveXFlights;
    @FXML
    Button backButton;

    private int userBookingsCount = 0;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(BookingConfirmation booking : BookingConfirmation.bookingRecords){
            //System.out.println(booking.getUserName() + " " + User.currentUser.name + " " + booking.getPaymentStatus());
            if(booking.getUserName().equals(User.currentUser.getUserName()) && booking.payment.getPaymentStatus().equals("Payed")) {
                userBookingsCount++;
                HBox bookingHbox = new HBox(5);
                bookingHbox.setAlignment(Pos.CENTER);
                bookingHbox.setPrefHeight(60);
                bookingHbox.setStyle("-fx-background-color: white;" +
                        " -fx-border-width: 0.4;" +
                        " -fx-border-color: grey;");

                Label airline = new Label(booking.getAirLineName());
                airline.setStyle(" -fx-font-size: 18;" +
                        "-fx-text-fill: black;" +
                        "-fx-padding: 15;" +
                        "-fx-font-weight: bold;" +
                        " -fx-border-width: 5");

                Label flightTimeSchedule = new Label(booking.getDepartureTime() + " - " + booking.getArrivalTime());

                flightTimeSchedule.setStyle(" -fx-font-size: 18;" +
                        "-fx-text-fill: black;" +
                        "-fx-padding: 15;" +
                        "-fx-font-weight: bold;" +
                        " -fx-border-width: 5");

                Label arrivalDate = new Label(booking.getArrivalDate());
                arrivalDate.setStyle(" -fx-font-size: 18;" +
                        "-fx-text-fill: black;" +
                        "-fx-padding: 15;" +
                        "-fx-font-weight: bold;" +
                        " -fx-border-width: 5");

                Label arrivalDayText = new Label("Arrival Day");
                arrivalDayText.setStyle(" -fx-font-size: 18;" +
                        "-fx-text-fill: black;" +
                        "-fx-padding: 15;" +
                        "-fx-font-weight: bold;" +
                        " -fx-border-width: 5");

                Image bookingCancelImage = new Image("BookingCancelIcon.png");
                ImageView bookingCancelIcon = new ImageView(bookingCancelImage);
                bookingCancelIcon.setStyle("-fx-padding: 30;");

                bookingCancelIcon.setOnMousePressed(event -> {
                    booking.payment.setPaymentStatus("Refunded");
                    bookingsVbox.getChildren().remove(bookingHbox);
                    youHaveXFlights.setText("You have " + --userBookingsCount + " Flights");
                });

                bookingHbox.getChildren().add(airline);
                bookingHbox.getChildren().add(flightTimeSchedule);
                bookingHbox.getChildren().add(arrivalDayText);
                bookingHbox.getChildren().add(arrivalDate);
                bookingHbox.getChildren().add(bookingCancelIcon);

                bookingsVbox.getChildren().add(bookingHbox);
            }
            youHaveXFlights.setText("You have " + userBookingsCount + " Flights");
        }
    }

    public void onBackButtonPressed(MouseEvent event){
        // Reset passengers data
        Passenger.passengers.clear();
        PassengersController.curPassenger = 0;

        SceneSwitcher.switchScene(event, "SearchFlightPage", null);
    }

    public void onLogoutButtonPressed(MouseEvent event){
        // Reset passengers data
        Passenger.passengers.clear();
        PassengersController.curPassenger = 0;

        SceneSwitcher.switchScene(event, "HomePage/HomePage", null);
    }
}
