package flyxpert.flyxpert.Admin;

import flyxpert.flyxpert.Flight.Flight;
import flyxpert.flyxpert.Main;
import flyxpert.flyxpert.Miscellaneous.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminOptionsController implements Initializable {

        @FXML
        private Label airlineLabel;
        @FXML
        private Label dayLabel;
        @FXML
        private Label timeLabel;
        @FXML
        private ImageView deleteIcon;
        @FXML
        private ImageView seatIcon;
        @FXML
        private ImageView editIcon;
        @FXML
        private Label warningLabel;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                imagesViewStyling();
                seatIcon.setOnMouseClicked(mouseEvent -> switchToAdminSeatMap());
                //deleteIcon.setOnMouseClicked(mouseEvent -> deleteFlight());
        }
        static Stage adminMainStage;

        public static void handleHBoxClick(Flight flight, int indexOfFlight, Stage mainStage) throws IOException {

                Flight.selectedFlight = flight;
                Flight.selectedFlightIndex = indexOfFlight;
                adminMainStage = mainStage;

                Stage newStage = new Stage();
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.initStyle(StageStyle.UTILITY);
                newStage.setTitle("Admin Options");
                newStage.setResizable(false);
                newStage.setX(593.0);
                newStage.setY(312.5);

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminOptions.fxml"));
                Scene newScene = new Scene(fxmlLoader.load());
                AdminOptionsController controller = fxmlLoader.getController();

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

        private void imagesViewStyling() {
                AdminOptionsController.setImageViewHover(deleteIcon);
                AdminOptionsController.setImageViewHover(seatIcon);
                AdminOptionsController.setImageViewHover(editIcon);
        }

        private static void setImageViewHover(ImageView imageView) {
                imageView.setOnMouseEntered(event -> {
                        imageView.setStyle("-fx-opacity: 0.5");
                });

                imageView.setOnMouseExited(event -> {
                        imageView.setStyle("-fx-opacity: 1");
                });
        }

        public void switchToAdminSeatMap()  {
                SceneSwitcher.createPopUp("AdminSeatMap");
        }

        public void switchToAdminEditFlight(MouseEvent event) {
                SceneSwitcher.switchScene(event, "AdminEditFlightScene", adminMainStage);
        }

        public void deleteFlight(MouseEvent event){

             if(Flight.selectedFlight.getAvailableSeats() == 96){
                  warningLabel.setText("");
                  Flight.flights.remove(Flight.selectedFlightIndex);
                  SceneSwitcher.switchScene(event, "AdminPage", adminMainStage);
             }
             else{
                   warningLabel.setText("The flight is filled with passengers");
             }
        }
}
