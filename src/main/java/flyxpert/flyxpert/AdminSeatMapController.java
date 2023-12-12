package flyxpert.flyxpert;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminSeatMapController extends SeatMap implements Initializable {
        @FXML
        ScrollPane scrollPane;
        @FXML
        Pane overlay;
        @FXML
        AnchorPane anchorPane;

        @FXML
        Label flightName;

        /**
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                dfsAddSeats(overlay, 363, 540, 0, 0);

                Button discard = new Button();
                Button save = new Button();

                discard.setPrefSize(100, 35);
                save.setPrefSize(100, 35);

                anchorPane.getChildren().add(discard);
                anchorPane.getChildren().add(save);

                AnchorPane.setBottomAnchor(discard, 20.0);
                AnchorPane.setBottomAnchor(save, 20.0);

                AnchorPane.setRightAnchor(discard, 130.0);
                AnchorPane.setRightAnchor(save, 20.0);

                discard.setText("Discard");
                discard.setFont(new Font(18));
                save.setText("Save");
                save.setFont(new Font(18));

                discard.setOnMouseClicked(mouseEvent -> discardClicked());
                save.setOnMouseClicked(mouseEvent -> saveClicked());
                discard.getStylesheets().add("styles.css");
                save.getStylesheets().add("styles.css");
        }

        /**
         * @param seat
         */
        @Override
        public void seatClicked(Seat seat) {
                // TODO : just change seat availability and color unless it is already booked
        }

        public void discardClicked() {

        }

        public void saveClicked() {

        }
}
