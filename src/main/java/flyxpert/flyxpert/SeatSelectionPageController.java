package flyxpert.flyxpert;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
//import jdk.internal.access.JavaNetHttpCookieAccess;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class SeatSelectionPageController implements Initializable {

        private static final String lightPurple = new String("#E9E8FC");
        private static final String darkPurple = new String("#3F3CE0");
        private static final String lightBlue = new String("#66E4D9");
        private static final String darkBlue = new String("#4682E4");
        private static final String lightYellow = new String("#E9F108");
        private static final String darkYellow = new String("#FDC506");

        private Seat[][] economySeats = new Seat[4][4];
        private Seat[][] businessSeats = new Seat[4][4];
        private Seat[][] firstClassSeats = new Seat[4][4];


        private int size = Passengers.passengers.size();
        private int index = 0;


        // for manual tests
        void creatArbitrary() throws ParseException {
                for (int i = 0; i < 3; i++) {
                        Passengers arbitraryPassenger = new Passengers();

                        arbitraryPassenger.setName("Arbitrary Passenger" + i);
                        SimpleDateFormat DOBFormat = new SimpleDateFormat("dd/MM/yy");;
                        arbitraryPassenger.setDOB(DOBFormat.parse("01/01/90"));
                        arbitraryPassenger.setPhoneNumber("123-456-7890");

                        Passengers.passengers.add(arbitraryPassenger);
                        //     System.out.println(passengers.size());
                }

        }


        @FXML
        GridPane gp;
        @FXML
        GridPane gp2;
        @FXML
        GridPane bus1;
        @FXML
        GridPane bus2;
        @FXML
        GridPane fir1;
        @FXML
        GridPane fir2;

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


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                nextSeat.setBorder(createPurpleBorder());
              /*  try {
                        creatArbitrary();
                }
                catch (ParseException e) {
                        throw new RuntimeException(e);
                }*/
                fillSeatMap();
        }

        private javafx.scene.layout.Border createPurpleBorder() {
                javafx.scene.layout.BorderStroke borderStroke = new javafx.scene.layout.BorderStroke(
                        Color.web(darkPurple),
                        BorderStrokeStyle.DASHED,
                        null,
                        new javafx.scene.layout.BorderWidths(1),
                        null
                );
                return new javafx.scene.layout.Border(borderStroke);
        }

        public void fillCell(GridPane g, Seat[][] s, int col, int row, String colCode, String type, int val) {
                int column = col + val;
                s[row][column] = new Seat();
                s[row][column].setRec(new Rectangle(30, 40, Color.web(colCode)));
                s[row][column].setRow(row);
                s[row][column].setCol(column);
                s[row][column].setType(type);

                s[row][column].getRec().setArcWidth(12);
                s[row][column].getRec().setArcHeight(12);

                String fstHalf = String.valueOf(row + 1 + 4 * (type.equals("business") ? 1 : 0) + 8 * (type.equals("first") ? 1 : 0));
                s[row][column].setPrimaryKey(fstHalf + (char) (column + 'A'));

                s[row][column].getRec().setOnMouseClicked(event -> seatClicked(s[row][column]));
                g.add(s[row][column].getRec(), col, row);
        }

        private static String getRandomString(String option1, String option2) {
                Random random = new Random();
                boolean randomBoolean = random.nextBoolean();
                return randomBoolean ? option1 : option2;
        }

        public void fillSeatMap() {
                for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 2; j++) {

                                String r = getRandomString(lightPurple, darkPurple);

                                fillCell(gp, economySeats, j, i, r, "economy", 0);

                                r = getRandomString(lightPurple, darkPurple);
                                fillCell(gp2, economySeats, j, i, r, "economy", 2);

                                r = getRandomString(lightBlue, darkBlue);
                                fillCell(bus1, businessSeats, j, i, r, "business", 0);

                                r = getRandomString(lightBlue, darkBlue);
                                fillCell(bus2, businessSeats, j, i, r, "business", 2);

                                r = getRandomString(lightYellow, darkYellow);
                                fillCell(fir1, firstClassSeats, j, i, r, "first", 0);

                                r = getRandomString(lightYellow, darkYellow);
                                fillCell(fir2, firstClassSeats, j, i, r, "first", 2);
                        }
                }
        }

        public void change(int row, int col, Seat[][] s, Paint cmp, Seat seat) {
                if (Passengers.passengers.isEmpty()) {
                        System.out.println("empty asshole");
                        return;
                }
                if (s[row][col].getRec().getFill().equals(cmp) && Passengers.passengers.get(index).getSeat() == null) {

                        s[row][col].getRec().setFill(Color.GRAY);
                        Passengers.passengers.get(index).setSeat(seat);
                }
                else if (s[row][col].getRec().getFill().equals(Color.GRAY) && Passengers.passengers.get(index).getSeat() != null && Passengers.passengers.get(index).getSeat().getPrimaryKey().equals(s[row][col].getPrimaryKey())) {

                        s[row][col].getRec().setFill(cmp);
                        Passengers.passengers.get(index).setSeat(null);
                }
        }
        public void seatClicked(Seat seat) {
                int row = seat.getRow();
                int col = seat.getCol();
                if (seat.getType().equals("economy")) {
                        Paint cmp = Color.web(darkPurple);
                        // here, set passenger seat to this only if it is his seat i.e. his turn
                        change(row, col, economySeats, cmp, seat);
                }
                else if (seat.getType().equals("business")) {
                        Paint cmp = Color.web(darkBlue);

                        change(row, col, businessSeats, cmp, seat);
                } else {
                        Paint cmp = Color.web(darkYellow);

                        change(row, col, firstClassSeats, cmp, seat);
                }

                seatNumber.setText(seat.getPrimaryKey());
        }

        public void nextSeatClicked() {
                // can't do if hadn't chosen a seat
                if (Passengers.passengers.get(index).getSeat() == null) {
                        // show warning
                        noSeatChosen.setVisible(true);
                        return;
                }
                ++index;
                noSeatChosen.setVisible(false);
                if (index == size) {

                      // TODO switch to next scene

                      // System.out.println("hello");
                        return;
                }
                passengerCount.setText("Passenger" + (index + 1));

                // display next passenger name
                passengerName.setText(Passengers.passengers.get(index).getName());

                if (index == size - 1) {
                        nextSeat.setText("Proceed to Payment");
                }
        }

        // TODO logic for proceedToPaymentClicked
}
