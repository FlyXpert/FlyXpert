package flyxpert.flyxpert;

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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class SeatSelectionPageController implements Initializable {

        private static String lightPurple = new String("#E9E8FC");
        private static String darkPurple = new String("#3F3CE0");
        private static String lightBlue = new String("#66E4D9");
        private static String darkBlue = new String("#4682E4");
        private static String lightYellow = new String("#E9F108");
        private static String darkYellow = new String("#FDC506");

        private Seat[][] economySeats = new Seat[4][4];
        private Seat[][] busiSeats = new Seat[4][4];
        private Seat[][] firstClassSeats = new Seat[4][4];

      /*  void getData () {

        }*/

        int idx = 0;
        ArrayList<Integer> tmp;


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



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                nextSeat.setBorder(createPurpleBorder());

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

                String fstHalf = String.valueOf(row + 1 + 4 * (type.equals("busi") ? 1 : 0) + 8 * (type.equals("first") ? 1 : 0));
                s[row][column].setPrimaryKey(fstHalf + (char)(column + 'A'));

                s[row][column].getRec().setOnMouseClicked(event -> seatCicked(s[row][column]));
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
                                fillCell(bus1, busiSeats, j, i, r, "busi", 0);

                                r = getRandomString(lightBlue, darkBlue);
                                fillCell(bus2, busiSeats, j, i, r, "busi", 2);

                                r = getRandomString(lightYellow, darkYellow);
                                fillCell(fir1, firstClassSeats, j, i, r, "first", 0);

                                r = getRandomString(lightYellow, darkYellow);
                                fillCell(fir2, firstClassSeats, j, i, r, "first", 2);
                        }
                }
        }

        // need to have an array of passengers
        public void seatCicked(Seat seat) {
                int row = seat.getRow();
                int col = seat.getCol();
                if (seat.getType().equals("economy")) {
                        Paint cmp = Color.web(darkPurple);

                        // here, set passenger seat to this only if it is his seat i.e. his turn

                        /*if (economySeats[row][col].getFill().equals(cmp)

                                economySeats[row][col].setFill(Color.GRAY);
                          else if (economySeats[row][col].getFill().equals(Color.GRAY)) {
                                if (curPassenger.seatRow == row && curPassenger.seatCol == col) {
                                        economySeats[row][col].setFill(cmp);
                                        curPassenger.seatRow = -1;
                                        curPassenger.seatcol = -1;
                                }
                        }*/

                }
                else if (seat.getType().equals("busi")) {
                        Paint cmp = Color.web(darkBlue);
                        if (busiSeats[row][col].getRec().getFill().equals(cmp))
                                busiSeats[row][col].getRec().setFill(Color.GRAY);
                }
                else {
                        Paint cmp = Color.web(darkYellow);
                        if (firstClassSeats[row][col].getRec().getFill().equals(cmp))
                                firstClassSeats[row][col].getRec().setFill(Color.GRAY);
                }

                // may return if can't the seat is chosen before
                seatNumber.setText(seat.getPrimaryKey());
        }
        void nextSeatClicked() {
                ++idx;
                if (idx == tmp.size()) {
                        // switch to next scene
                        return;
                }

                // display next passenger name

                if (idx == tmp.size() - 1)
                        nextSeat.setText("Proceed to Payment");
        }
}
