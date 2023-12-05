package flyxpert.flyxpert;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new Reader();
        reader.readAllFiles();
        System.out.println(Flight.flights.size());
        launch(args);
        Writer writer = new Writer();
        writer.writeToAllFiles();
    }

    public void start(Stage stage) throws IOException {

        try{
            Parent root= FXMLLoader.load(getClass().getResource("SearchFlightPage.fxml"));

            
            VBox vbox = new VBox();
            //vbox.setSpacing(10);
            //vbox.setPadding(new Insets(10));

            for(Flight flight : Flight.flights){
                vbox.getChildren().add(createFlight(flight));
            }


            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e){
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