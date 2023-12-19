package flyxpert.flyxpert.Flight;

import flyxpert.flyxpert.Main;
import flyxpert.flyxpert.Passenger.PassengersController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.ParseException;

import static flyxpert.flyxpert.Miscellaneous.SceneSwitcher.stage;
import static flyxpert.flyxpert.Miscellaneous.SceneSwitcher.switchScene;

public class FlightDetailsConfirmationController {
    //static Flight specifiedFlight;


    @FXML
    Label airlineLabel;
    @FXML
    Label dayLabel;
    @FXML
    Label timeLabel;
    public static void handleHBoxClick(Flight flight, int numOfPassengers) throws IOException {
       // specifiedFlight = new Flight(flight);
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initStyle(StageStyle.UTILITY);
        newStage.setTitle("Flight Details");
        newStage.setResizable(false);
        newStage.setX(593.0);
        newStage.setY(312.5);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DetailsConfirmation.fxml"));
        Scene newScene = new Scene(fxmlLoader.load());
        FlightDetailsConfirmationController controller = fxmlLoader.getController();

        controller.airlineLabel.setText(flight.getAirlineName() + " Airlines");

        controller.dayLabel.setText(flight.getArrivalDate().getDay()
                + "-" + flight.getArrivalDate().getMonth()
                + "-" + flight.getArrivalDate().getYear());

        controller.timeLabel.setText(flight.getDepartureTime().getHour() + ":" + flight.getDepartureTime().getMinutes()
                + " " + flight.getDepartureTime().getPeriod()
                + " - " + flight.getArrivalTime().getHour() + ":" + flight.getArrivalTime().getMinutes()
                + " " + flight.getArrivalTime().getPeriod());


        newStage.setScene(newScene);
        newStage.show();
        for(int i = 0; i < Flight.flights.size(); i++){
            if(Flight.flights.get(i).getFlightNumber() == flight.getFlightNumber()){
                Flight.selectedFlightIndex = i;
                break;
            }
        }
        PassengersController.passengersToBeAdded = numOfPassengers;
        PassengersController.initialPassengersToBeAdded = numOfPassengers;
    }

    public void toPassengerInfoScene(ActionEvent actionEvent) throws IOException, ParseException {
        switchScene(actionEvent, "Passengers", stage);
    }
}
