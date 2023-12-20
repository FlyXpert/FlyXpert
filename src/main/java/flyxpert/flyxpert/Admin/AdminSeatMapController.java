package flyxpert.flyxpert.Admin;

import flyxpert.flyxpert.Flight.Flight;
import flyxpert.flyxpert.Miscellaneous.SceneSwitcher;
import flyxpert.flyxpert.Seat.Seat;
import flyxpert.flyxpert.Seat.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminSeatMapController extends SeatMap implements Initializable {
        ArrayList<Integer> adminReservedSeats = new ArrayList<>();
        boolean[][] justBeenReserved = new boolean[24][4];

        @FXML
        ScrollPane scrollPane;
        @FXML
        Pane overlay;
        @FXML
        AnchorPane anchorPane;

        @FXML
        Label flightName;

        public AdminSeatMapController() throws NoSuchAlgorithmException {
        }

        /**
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                for (int i = 0; i < 24; ++i)
                        for (int j = 0; j < 4; ++j)
                                justBeenReserved[i][j] = false;
                dfsAddSeats(overlay, 363, 540, 0, 0);

                flightName.setText(Flight.flights.get(Flight.selectedFlightIndex).getDepartureAirport().getName()
                 + " to " +
                        Flight.flights.get(Flight.selectedFlightIndex).getArrivalAirport().getName());

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
                discard.getStylesheets().add("Styles.css");
                save.getStylesheets().add("Styles.css");
        }

        /**
         * @param seat
         */
        @Override
        public void seatClicked(Seat seat) {
                // TODO : just change seat availability and color unless it is already booked
                int row = seat.getRow();
                int col = seat.getCol();
                if (seats[row][col].getRec().getFill() != Color.GRAY) {
                        seats[row][col].getRec().setFill(Color.GRAY);
                        adminReservedSeats.add(row * 100 + col);
                        justBeenReserved[row][col] = true;
                }

                else if (justBeenReserved[row][col] == true) {
                        seats[row][col].getRec().setFill(seats[row][col].getType().color);

                        adminReservedSeats.remove((Integer) (row * 100 + col));
                }
        }

        public void discardClicked() {
                SceneSwitcher.stage.close();
        }

        public void saveClicked() {
                int size = adminReservedSeats.size();
                boolean[][] a = new boolean[24][4];

                Flight currentFlight = Flight.flights.get(Flight.selectedFlightIndex);
                currentFlight.setAvailableSeats(currentFlight.getAvailableSeats() - size);
                a = currentFlight.getSeatsAvailability();

                for (int i = 0; i < size; ++i) {
                        a[adminReservedSeats.get(i) / 100][adminReservedSeats.get(i) % 10] = false;
                }
                Flight.flights.get(Flight.selectedFlightIndex).setSeatsAvailability(a);
                SceneSwitcher.stage.close();
        }

        String s = new String();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
}
