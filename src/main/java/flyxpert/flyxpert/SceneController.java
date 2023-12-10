package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;


public class SceneController {

        static Stage stage = new Stage();
        static Scene scene;
        static Parent root;
        public static void switchToSeatSelection(ActionEvent event) throws IOException, ParseException {

                root = FXMLLoader.load(SeatSelectionPageController.class.getResource("SeatSelectionPage.fxml"));
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }

        public static void switchToPassengerInfo(ActionEvent event) throws IOException, ParseException {

                root = FXMLLoader.load(SeatSelectionPageController.class.getResource("Passengers.fxml"));
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }

        public static void switchToSearchFlightPage(ActionEvent event, Stage currentStage) throws IOException, ParseException {
                currentStage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SearchFlightPage.fxml"));
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader.load());
                FlightInformationController flightInformationController = fxmlLoader.getController();
                flightInformationController.fillDataOfFlights();
                stage.setScene(scene);
                stage.show();
        }
}
