package flyxpert.flyxpert;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrollPane;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillDataOfFlights();
    }

    public void adminSeatMap() {

    }

    public void fillDataOfFlights() {
        FlightInformationController.setScrollPane(scrollPane);
        showAllFlights();
        FlightInformationController.vboxStyling(vbox);
    }

    private void showAllFlights() {
        try {
            for(Flight flight : Flight.flights) {
                vbox.getChildren().add(createFlight(flight));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private HBox createFlight(Flight flight){
        HBox hbox = new HBox();
        setHboxLabels(hbox, flight);
        FlightInformationController.hboxStyling(hbox);
        setHboxOnAction(hbox, flight);
        return hbox;
    }


    private void setHboxLabels(HBox hbox, Flight flight) {
        FlightInformationController.setAirlineLabel(hbox, flight);
        setDepartureAirportLabel(hbox,flight);
        setArrivalAirportLabel(hbox,flight);
        FlightInformationController.setDateLabel(hbox, flight);
        FlightInformationController.setTimeLabel(hbox, flight);
    }

    private void setDepartureAirportLabel(HBox hbox, Flight flight){
        Label departureAirprotLabel = new Label(flight.getDepartureAirport().getCode());
        FlightInformationController.setLabelStyle(departureAirprotLabel,"black");
        hbox.getChildren().add(departureAirprotLabel);
    }

    private void setArrivalAirportLabel(HBox hbox, Flight flight){
        Label arrivalAirprotLabel = new Label(flight.getArrivalAirport().getCode());
        FlightInformationController.setLabelStyle(arrivalAirprotLabel,"black");
        hbox.getChildren().add(arrivalAirprotLabel);
    }

    private void setHboxOnAction(HBox hbox, Flight flight) {
        hbox.setOnMouseClicked(event -> {
            try {
                AdminOptionsController.handleHBoxClick(flight);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}