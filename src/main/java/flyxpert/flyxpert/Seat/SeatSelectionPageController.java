package flyxpert.flyxpert.Seat;

import flyxpert.flyxpert.Passenger.Passenger;
import flyxpert.flyxpert.Passenger.PassengersController;
import flyxpert.flyxpert.Miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
//import jdk.internal.access.JavaNetHttpCookieAccess;

import java.net.URL;
import java.util.ResourceBundle;

import static flyxpert.flyxpert.Miscellaneous.Palette.*;


public class SeatSelectionPageController extends SeatMap implements Initializable {
        private int size = Passenger.passengers.size();
        private int index = 0;

        // for manual tests
        /*void creatArbitrary() throws ParseException {
                for (int i = 0; i < 3; i++) {
                        Passengers arbitraryPassenger = new Passengers();

                        arbitraryPassenger.setName("Arbitrary Passenger" + i);
                        SimpleDateFormat DOBFormat = new SimpleDateFormat("dd/MM/yy");;
                        arbitraryPassenger.setDOB(DOBFormat.parse("01/01/90"));
                        arbitraryPassenger.setPhoneNumber("123-456-7890");

                        Passengers.passengers.add(arbitraryPassenger);
                        //     System.out.println(passengers.size());
                }

        }*/

        @FXML
        Button nextSeat;

        @FXML
        Label seatNumber;
        @FXML
        Label passengerName;
        @FXML
        Label noSeatChosen;
        @FXML
        Label passengerCount;

        @FXML
        ScrollPane scrollPane;

        private ImageView imageView(String image) {
                Image i = new Image(image);
                ImageView iv = new ImageView(i);
                return iv;
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                try {
                        passengerCount.setText("Passenger 1");
                        nextSeat.setText("Next Passenger");
                        seatNumber.setText("--");
                        passengerName.setText(Passenger.passengers.get(0).getFirstName());
                        if (index > size - 2) {
                                nextSeat.setText("Proceed to Payment");
                        }
                } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                }

                Pane overlay = new Pane();
                overlay.getChildren().add(imageView("Plane.jpg"));

                dfsAddSeats(overlay, 960, 540, 0, 0);

                scrollPane.setContent(overlay);
        }

        public void changeSeatColorAndAssignSeatToPassenger(int row, int col, Seat[][] s, Paint cmp, Seat seat) {
                try {
                        if (s[row][col].getRec().getFill().equals(cmp)) {

                                s[row][col].getRec().setFill(Color.GRAY);
                                if (Passenger.passengers.get(index).getSeat() != null) {
                                        Paint originalColor = Passenger.passengers.get(index).getSeat().getType().getColor();
                                        Passenger.passengers.get(index).getSeat().getRec().setFill(originalColor);
                                }
                                Passenger.passengers.get(index).setSeat(seat);
                                seatNumber.setText(seat.getPrimaryKey());
                                nextSeat.setOpacity(1);
                        }
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
        }

        public void seatClicked(Seat seat) {
                int row = seat.getRow();
                int col = seat.getCol();
                if (seat.getType().getName().equals("economy")) {
                        Paint cmp = Color.web(DARK_PURPLE);
                        // here, set passenger seat to this only if it is his seat i.e. his turn
                        changeSeatColorAndAssignSeatToPassenger(row, col, economySeats, cmp, seat);
                } else if (seat.getType().getName().equals("business")) {
                        Paint cmp = Color.web(GREEN);

                        changeSeatColorAndAssignSeatToPassenger(row, col, businessSeats, cmp, seat);
                } else {
                        Paint cmp = Color.web(RED);

                        changeSeatColorAndAssignSeatToPassenger(row, col, firstClassSeats, cmp, seat);
                }
        }


        /**
         * Handles the click event when the "Next Seat" button is clicked. This method is responsible for managing the
         * progression of passengers through the seat selection process and potentially transitioning to the payment phase.
         */
        public void nextSeatClicked(ActionEvent event) {

                // Print debugging information
                //System.out.println(index);
                //System.out.println(size);


                // Check if the current passenger hasn't selected a seat
                if (nextSeat.getOpacity() != 1) {
                        // Display a warning
                        noSeatChosen.setVisible(true);
                        return;
                }
                // If the current passenger is the last one, transition to the next scene
                if (index == size - 1) {
                        SceneSwitcher.switchScene(event, "PaymentPage/PaymentPage", null);
                        return;
                }
                // If the current passenger is the second to last one, update the button text and opacity
                if (index == size - 2) {
                        nextSeat.setText("Proceed to Payment");

                }

                // Hide the "No Seat Chosen" warning
                noSeatChosen.setVisible(false);


                // Update the passenger count label
                passengerCount.setText("Passenger " + (index + 2));
                ++index;
                seatNumber.setText("--");

                // Display the name of the next passenger
                passengerName.setText(Passenger.passengers.get(index).getFirstName());

                // Update the opacity of the "Next Seat" button
                nextSeat.setOpacity(.75);
        }

        public void onFlightButtonPressed(MouseEvent event){
                // Reset passengers data
                Passenger.passengers.clear();
                PassengersController.curPassenger = 0;

                SceneSwitcher.switchScene(event, "SearchFlightPage", null);
        }
}