package flyxpert.flyxpert;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DetailsConfirmationController {
    @FXML
    private  Label airlineLabel;
    @FXML
    private  Label dayLabel;
    @FXML
    private Label timeLabel;
    public static void handleHBoxClick(Flight flight) throws IOException {


        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initStyle(StageStyle.UTILITY);
        newStage.setTitle("Flight Details");
        newStage.setResizable(false);
        newStage.setX(593.0);
        newStage.setY(312.5);

        FXMLLoader fxmlLoader = new FXMLLoader(DetailsConfirmationController.class.getResource("DetailsConfirmation.fxml"));
        Scene newScene = new Scene(fxmlLoader.load());
        DetailsConfirmationController controller = fxmlLoader.getController();

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
    }
}
