package flyxpert.flyxpert.Admin;

import flyxpert.flyxpert.Flight.Flight;
import flyxpert.flyxpert.Flight.FlightInformationController;
import flyxpert.flyxpert.Miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillDataOfFlights();
    }

    public void fillDataOfFlights() {
        FlightInformationController.setScrollPane(scrollPane);
        showAllFlights();
        FlightInformationController.vboxStyling(vbox);
    }

    private void showAllFlights() {
        try {
            int indexOfFlight = 0;
            for(Flight flight : Flight.flights) {
                vbox.getChildren().add(createFlight(flight,indexOfFlight));
                indexOfFlight++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private HBox createFlight(Flight flight,int indexOfFlight){
        HBox hbox = new HBox();
        setHboxLabels(hbox, flight);
        FlightInformationController.hboxStyling(hbox);
        setHboxOnAction(hbox, flight,indexOfFlight);
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

    Stage currentStage;

    private void setHboxOnAction(HBox hbox, Flight flight,int indexOfFlight) {
        hbox.setOnMouseClicked(event -> {
            try {
                currentStage = (Stage) hbox.getScene().getWindow();
                AdminOptionsController.handleHBoxClick(flight,indexOfFlight,currentStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void switchToAdminAddFlight(ActionEvent event)  {
        SceneSwitcher.switchScene(event,"AdminAddFlightScene", null);
    }

    public void onLogoutButtonClicked(MouseEvent event){
        SceneSwitcher.switchScene(event, "HomePage/HomePage", null);
    }
}
