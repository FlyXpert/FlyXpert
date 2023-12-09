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

        private static final String lightPurple = new String("#E9E8FC");
        private static final String darkPurple = new String("#3F3CE0");
       // private static final String lightBlue = new String("#66E4D9");
        private static final String green = new String("#4AD0B8");
       // private static final String lightYellow = new String("#E9F108");
        private static final String darkYellow = new String("#FDC506");
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


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                try {
                        seatNumber.setText("--");
                        passengerName.setText(Passengers.passengers.get(0).getName());
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
        private void dfsAddSeats(Pane overlay, int x, int y, int i, int j) {
                vis[i][j] = true;
                if (i >= 24 || j >= 4)
                        return;

                seats[i][j] = new Seat();
                seats[i][j].setRec(new Rectangle(30, 40));
                seats[i][j].setRow(i);
                seats[i][j].setCol(j);

                seats[i][j].getRec().setArcWidth(12);
                seats[i][j].getRec().setArcHeight(12);
                seats[i][j].getRec().setLayoutX(x);
                seats[i][j].getRec().setLayoutY(y);
                seats[i][j].getRec().setOnMouseClicked(event -> seatClicked(seats[i][j]));

                int d = i / 4;
                Paint toBe = null;

                switch (d) {
                        case 0 :  {
                                toBe = Color.web(green);
                                seats[i][j].setType("business");
                                seats[i][j].getRec().setFill(toBe);
                                String fstHalf = String.valueOf(i + 1 + 4);
                                seats[i][j].setPrimaryKey(fstHalf + (char) (j + 'A'));
                                businessSeats[i][j] = seats[i][j];
                                break;
                        }
                        case 1, 2, 3, 4 :  {
                                toBe = Color.web(darkPurple);
                                seats[i][j].setType("economy");
                                seats[i][j].getRec().setFill(toBe);
                                String fstHalf = String.valueOf(i + 1);
                                seats[i][j].setPrimaryKey(fstHalf + (char) (j + 'A'));
                                economySeats[i][j] = seats[i][j];

                                break;
                        }
                        case 5 :  {
                                toBe = Color.web(red);
                                seats[i][j].setType("firstClass");
                                seats[i][j].getRec().setFill(toBe);
                                String fstHalf = String.valueOf(i + 1 + 8);
                                seats[i][j].setPrimaryKey(fstHalf + (char) (j + 'A'));
                                firstClassSeats[i][j] = seats[i][j];

                                break;
                        }
                }

                overlay.getChildren().add(seats[i][j].getRec());

                int add = 0;

                if (i + 1 < 100 && !vis[i + 1][j]) {
                        if ((i + 1) % 4 == 0) {
                                add = 30;
                                if (i == 3)
                                        add = 60;
                        }
                        dfsAddSeats(overlay, x, y + 50 + add, i + 1, j);
                }
                add = 0;
                if (j + 1 < 100 && !vis[i][j + 1]) {
                        if (j + 1 == 2)
                                add = 25;
                        dfsAddSeats(overlay, x + 40 + add, y, i, j + 1);
                }
        }

        /*public void makeSeat(GridPane g, Seat[][] s, int col, int row, String colCode, String type, int val) {
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
        }*/

        // shall be replaced with real info coming
       /* private static String getRandomString(String option1, String option2) {
                Random random = new Random();
                boolean randomBoolean = random.nextBoolean();
                return randomBoolean ? option1 : option2;
        }*/

        /*public void fillSeatMap() {
                for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 2; j++) {

                                String r = getRandomString(lightPurple, darkPurple);

                                makeSeat(economyGridPane1, economySeats, j, i, r, "economy", 0);

                                r = getRandomString(lightPurple, darkPurple);
                                makeSeat(economyGridPane2, economySeats, j, i, r, "economy", 2);

                                r = getRandomString(lightPurple, green);
                                makeSeat(businessGridPane1, businessSeats, j, i, r, "business", 0);

                                r = getRandomString(lightPurple, green);
                                makeSeat(businessGridPane2, businessSeats, j, i, r, "business", 2);

                                r = getRandomString(lightPurple, darkYellow);
                                makeSeat(firstClassGridPane1, firstClassSeats, j, i, r, "first", 0);

                                r = getRandomString(lightPurple, darkYellow);
                                makeSeat(firstClassGridPane2, firstClassSeats, j, i, r, "first", 2);
                        }
                }
        }*/

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
                System.out.println("hellllllllll");
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

        private ImageView imageView(String image) {
                Image i = new Image(image);
                ImageView iv = new ImageView(i);
                return iv;
        }

        public void nextSeatClicked() {
                // can't do if hadn't chosen a seat

                if (nextSeat.getOpacity() != 1)
                        return;

                if (index == size - 2) {
                        nextSeat.setText("Proceed to Payment");
                        nextSeat.setOpacity(.75);
                        ++index;
                        return;
                }

                try {
                        if (Passengers.passengers.get(index).getSeat() == null) {
                                // show warning
                                noSeatChosen.setVisible(true);
                                return;
                        }
                }
                catch(Exception e) {
                        System.out.println("No more passengers!");
                }

                noSeatChosen.setVisible(false);
                if (index == size - 1) {

                      // TODO switch to next scene

                      // System.out.println("don't click me, shithead");
                       return;
                }
                passengerCount.setText("Passenger " + (index + 1));

                // display next passenger name
                passengerName.setText(Passengers.passengers.get(index).getName());
                nextSeat.setOpacity(.75);
        }

        // TODO logic for proceedToPaymentClicked
}
