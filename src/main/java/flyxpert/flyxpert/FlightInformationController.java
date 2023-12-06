package flyxpert.flyxpert;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightInformationController implements Initializable {
    @FXML
    private ImageView worldImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/world.png");
        Image image = new Image(file.toURI().toString());
        worldImageView.setImage(image);
    }

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vbox;

    public void fillDataOfFlights(){

        try {
               for(Flight flight : Flight.flights) {
                   vbox.getChildren().add(createFlight(flight));
               }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private HBox createFlight(Flight flight){
        HBox hbox = new HBox();
        hbox.setStyle("-fx-border-color: black; -fx-border-width: 0.5");

        hbox.getChildren().add(new Label(flight.getAirlineName()));

        hbox.getChildren().add(new Label(flight.getDepartureTime().getHour() + ":" + flight.getDepartureTime().getMinutes()
                + "-" + flight.getArrivalTime().getHour() + ":" + flight.getArrivalTime().getMinutes()));

        hbox.getChildren().add(new Label(flight.getArrivalDay().getDay()
                + "-" + flight.getArrivalDay().getMonth()
                + "-" + flight.getArrivalDay().getYear()));

        hbox.getChildren().add(new Label(Integer.toString(flight.getEconomyPrice())));

        return hbox;
    }
}
