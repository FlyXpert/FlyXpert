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
        hbox.setStyle("-fx-border-color: black;");

        Label label1 = new Label(flight.getAirlineName());

        label1 = FlightInformationController.setLabelStyle(label1);
        hbox.getChildren().add(label1);

        Label label2 = new Label(flight.getDepartureTime().getHour() + ":" + flight.getDepartureTime().getMinutes()
                + "-" + flight.getArrivalTime().getHour() + ":" + flight.getArrivalTime().getMinutes());

        label2 = FlightInformationController.setLabelStyle(label2);
        hbox.getChildren().add(label2);

        Label label3 = new Label(flight.getArrivalDay().getDay()
                + "-" + flight.getArrivalDay().getMonth()
                + "-" + flight.getArrivalDay().getYear());


        label3 = FlightInformationController.setLabelStyle(label3);
        hbox.getChildren().add(label3);

        Label label4 = new Label(Integer.toString(flight.getEconomyPrice()));

        label4 = FlightInformationController.setLabelStyle(label4);
        hbox.getChildren().add(label4);

        return hbox;
    }

    private static Label setLabelStyle(Label label) {
        label.setStyle("-fx-padding: 20;");

        return label;
    }
}
