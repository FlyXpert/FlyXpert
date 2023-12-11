package flyxpert.flyxpert;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
//import jdk.internal.access.JavaNetHttpCookieAccess;

import java.io.IOException;
import java.net.CookiePolicy;
import java.net.URL;
import java.security.spec.PSSParameterSpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class SeatSelectionPageController implements Initializable {

        private static final String darkPurple = new String("#3F3CE0");
        private static final String green = new String("#4AD0B8");
        private static final String red = new String("#F2588C");

        private Seat[][] economySeats = new Seat[200][200];
        private Seat[][] businessSeats = new Seat[200][200];
        private Seat[][] firstClassSeats = new Seat[200][200];


        private int size = Passengers.passengers.size();
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
                        seatNumber.setText("--");
                        passengerName.setText(Passengers.passengers.get(0).getFirstName());
                        if (index >= size - 2) {
                                nextSeat.setText("Proceed to Payment");
                        }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                }

                Pane overlay = new Pane();
                overlay.getChildren().add(imageView("totPlane.jpg"));

                dfsAddSeats(overlay, 960, 540, 0, 0);

                scrollPane.setContent(overlay);
        }


        private Seat[][] seats = new Seat[200][200];
        private boolean[][] vis = new boolean[200][200];

        /**
         * Performs a Depth-First Search (DFS) traversal to add seat objects to a specified overlay Pane.
         * The seats are represented as rectangles and are organized based on their type (business, economy, first class).
         * The method recursively explores adjacent seats and initializes their properties such as type, color, and primary key.
         *
         * @param overlay The Pane where seat rectangles will be added.
         * @param x       The starting x-coordinate for the seat rectangle.
         * @param y       The starting y-coordinate for the seat rectangle.
         * @param i       The row index of the seat in the seating arrangement.
         * @param j       The column index of the seat in the seating arrangement.
         */
        private void dfsAddSeats(Pane overlay, int x, int y, int i, int j) {
                // Mark the current seat as visited
                vis[i][j] = true;

                // Base case: If the current seat is beyond the seating arrangement boundaries, return
                if (i >= 24 || j >= 4)
                        return;

                // Create a new seat object and initialize its properties
                seats[i][j] = new Seat();
                seats[i][j].setRec(new Rectangle(30, 40));
                seats[i][j].setRow(i);
                seats[i][j].setCol(j);

                // Set the appearance of the seat rectangle
                seats[i][j].getRec().setArcWidth(12);
                seats[i][j].getRec().setArcHeight(12);
                seats[i][j].getRec().setLayoutX(x);
                seats[i][j].getRec().setLayoutY(y);
                seats[i][j].getRec().setOnMouseClicked(event -> seatClicked(seats[i][j]));

                // Determine the type and color of the seat based on its position in the seating arrangement
                int d = i / 4;
                Paint toBe = null;

                switch (d) {
                        case 0: {
                                // Business class seat
                                toBe = Color.web(green);
                                seats[i][j].setType("business");
                                seats[i][j].getRec().setFill(toBe);
                                String fstHalf = String.valueOf(i + 1 + 4);
                                seats[i][j].setPrimaryKey(fstHalf + (char) (j + 'A'));
                                businessSeats[i][j] = seats[i][j];
                                break;
                        }
                        case 1, 2, 3, 4: {
                                // Economy class seat
                                toBe = Color.web(darkPurple);
                                seats[i][j].setType("economy");
                                seats[i][j].getRec().setFill(toBe);
                                String fstHalf = String.valueOf(i + 1);
                                seats[i][j].setPrimaryKey(fstHalf + (char) (j + 'A'));
                                economySeats[i][j] = seats[i][j];
                                break;
                        }
                        case 5: {
                                // First-class seat
                                toBe = Color.web(red);
                                seats[i][j].setType("firstClass");
                                seats[i][j].getRec().setFill(toBe);
                                String fstHalf = String.valueOf(i + 1 + 8);
                                seats[i][j].setPrimaryKey(fstHalf + (char) (j + 'A'));
                                firstClassSeats[i][j] = seats[i][j];
                                break;
                        }
                }

                // Add the seat rectangle to the overlay Pane
                overlay.getChildren().add(seats[i][j].getRec());

                // Recursive calls to explore adjacent seats in the downward and rightward directions
                int add = 0;
                if (i + 1 < 100 && !vis[i + 1][j]) {
                        // Add additional space between rows if the current row is a multiple of 4
                        if ((i + 1) % 4 == 0) {
                                add = 30;
                                // Special case for row 3 to accommodate a larger gap
                                if (i == 3)
                                        add = 60;
                        }
                        dfsAddSeats(overlay, x, y + 50 + add, i + 1, j);
                }

                // Reset the additional space for the next recursive call
                add = 0;

                // Recursive call to explore adjacent seats in the rightward direction
                if (j + 1 < 100 && !vis[i][j + 1]) {
                        // Add additional space for the second column
                        if (j + 1 == 2)
                                add = 25;
                        dfsAddSeats(overlay, x + 40 + add, y, i, j + 1);
                }
        }



        public void changeSeatColorAndAssignSeatToPassenger(int row, int col, Seat[][] s, Paint cmp, Seat seat) {
                try {
                        if (s[row][col].getRec().getFill().equals(cmp) && Passengers.passengers.get(index).getSeat() == null) {

                                s[row][col].getRec().setFill(Color.GRAY);
                                Passengers.passengers.get(index).setSeat(seat);
                                seatNumber.setText(seat.getPrimaryKey());
                                nextSeat.setOpacity(1);
                        }
                        else if (s[row][col].getRec().getFill().equals(Color.GRAY) && Passengers.passengers.get(index).getSeat() != null && Passengers.passengers.get(index).getSeat().getPrimaryKey().equals(s[row][col].getPrimaryKey())) {

                                s[row][col].getRec().setFill(cmp);
                                Passengers.passengers.get(index).setSeat(null);
                                seatNumber.setText("--");
                                nextSeat.setOpacity(.75);
                        }
                }
                catch(Exception e) {
                        System.out.println("No more passengers!");
                }
        }
        public void seatClicked(Seat seat) {
                int row = seat.getRow();
                int col = seat.getCol();
                if (seat.getType().equals("economy")) {
                        Paint cmp = Color.web(darkPurple);
                        // here, set passenger seat to this only if it is his seat i.e. his turn
                        changeSeatColorAndAssignSeatToPassenger(row, col, economySeats, cmp, seat);
                }
                else if (seat.getType().equals("business")) {
                        Paint cmp = Color.web(green);

                        changeSeatColorAndAssignSeatToPassenger(row, col, businessSeats, cmp, seat);
                }
                else {
                        Paint cmp = Color.web(red);

                        changeSeatColorAndAssignSeatToPassenger(row, col, firstClassSeats, cmp, seat);
                }
        }


        /**
         * Handles the click event when the "Next Seat" button is clicked. This method is responsible for managing the
         * progression of passengers through the seat selection process and potentially transitioning to the payment phase.
         */
        public void nextSeatClicked() {
                // Check if a seat has been chosen
                if (nextSeat.getOpacity() != 1)
                        return;

                // Print debugging information
                System.out.println(index);
                System.out.println(size);

                // If the current passenger has selected a seat, proceed
                if (nextSeat.getOpacity() != 1)
                        return;

                // If the current passenger is the second to last one, update the button text and opacity
                if (index == size - 2) {
                        nextSeat.setText("Proceed to Payment");
                        nextSeat.setOpacity(.75);
                        ++index;
                        return;
                }

                try {
                        // Check if the current passenger has selected a seat
                        if (Passengers.passengers.get(index).getSeat() == null) {
                                // Display a warning if no seat has been chosen for the current passenger
                                noSeatChosen.setVisible(true);
                                return;
                        }
                } catch (Exception e) {
                        // Handle the case when there are no more passengers
                        System.out.println("No more passengers!");
                }

                // Hide the "No Seat Chosen" warning
                noSeatChosen.setVisible(false);

                // If the current passenger is the last one, transition to the next scene (TODO: implement scene transition)
                if (index == size - 1) {
                        // TODO: Implement the logic to switch to the next scene
                        return;
                }

                // Update the passenger count label
                passengerCount.setText("Passenger " + (index + 1));

                // Display the name of the next passenger
                passengerName.setText(Passengers.passengers.get(index).getFirstName());

                // Update the opacity of the "Next Seat" button
                nextSeat.setOpacity(.75);
        }
}
