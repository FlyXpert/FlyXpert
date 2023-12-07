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
    private Label airlineLabel;
    @FXML
    private Label dayLabel;
    @FXML
    private Label timeLabel;
    public void handleHBoxClick(Flight flight) throws IOException {

        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initStyle(StageStyle.UTILITY);
        newStage.setTitle("Flight Details");
        newStage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DetailsConfirmation.fxml"));
        Scene newScene = new Scene(fxmlLoader.load());
        DetailsConfirmationController controller = fxmlLoader.getController();

        airlineLabel.setText(flight.getAirlineName());

        dayLabel.setText(flight.getArrivalDay().getDay()
                + "-" + flight.getArrivalDay().getMonth()
                + "-" + flight.getArrivalDay().getYear());
        timeLabel.setText(flight.getDepartureTime().getHour() + ":" + flight.getDepartureTime().getMinutes()
                + "-" + flight.getArrivalTime().getHour() + ":" + flight.getArrivalTime().getMinutes());


        newStage.setScene(newScene);
        newStage.show();
    }
}
