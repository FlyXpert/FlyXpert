package flyxpert.flyxpert;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static flyxpert.flyxpert.BookingConfirmation.bookingRecords;
import static flyxpert.flyxpert.SceneSwitcher.stage;
import static flyxpert.flyxpert.User.currentUser;

public class UserHistoryController implements Initializable {

        int positionX = 0;
        int positionY = 0;
        int width = 558;
        int height = 478 / 3;
        ArrayList<BookingConfirmation> userBookings = new ArrayList<>();
        ArrayList<Integer> bookingsIndices = new ArrayList<>();

        @FXML
        ScrollPane scrollPane;

        @FXML
        Button backButton;

        @FXML
        Label youHaveXFlights;

        Pane overlay = new Pane();
        int userBookingsCount = 0;
        UserHistoryFlight[] userHistoryFlights;
        Button[] deleteButtons;
        HashMap<Integer, Integer> mapIndicesOfUserBookingsToIndicesOfBookingRecords = new HashMap<>();

        /**
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                backButton.setMinWidth(10);
                backButton.setMinHeight(10);
                ImageView back = new ImageView(new Image("back-left-arrow.png"));
                back.setFitHeight(backButton.getMinHeight());
                back.setFitWidth(backButton.getMinWidth());
                back.setPreserveRatio(true);
                backButton.setGraphic(back);

                backButton.setBackground(null);

                backButton.setOnMouseClicked(mouseEvent -> onBackClicked(mouseEvent));

                UserTrie userTrie = UserTrie.getInstance();

                bookingsIndices = userTrie.getBookings(currentUser.getUserName());

                int cur = 0;
                if (bookingsIndices != null) {
                        for (int index : bookingsIndices) {
                                if (bookingRecords.get(index).getCancelled() == false) {
                                        userBookings.add(bookingRecords.get(index));
                                        mapIndicesOfUserBookingsToIndicesOfBookingRecords.put(cur, index);
                                        ++cur;
                                }
                        }
                        userBookingsCount = userBookings.size();
                        System.out.println(userBookingsCount);
                }
                youHaveXFlights.setText("Your Flights ");

                overlay.setPrefSize(scrollPane.getPrefWidth(), height * userBookingsCount);
                overlay.setMaxWidth(scrollPane.getMaxWidth());

                userHistoryFlights = new UserHistoryFlight[userBookingsCount];
                deleteButtons = new Button[userBookingsCount];

                generateBookedFlights();

                scrollPane.setContent(overlay);
        }

        /*UserHistoryFlight[] userHistoryFlights = new UserHistoryFlight[userBookingsCount];
        Button[] deleteButtons = new Button[userBookingsCount];*/
        public void generateBookedFlights() {
                for (int i = 0; i < userBookingsCount; ++i) {
                        System.out.println("i : " + i);
                        Rectangle rec = new Rectangle(positionX, positionY, width, height);
                        rec.setFill(Color.WHITE);
                        overlay.getChildren().add(rec);

                        addFlight(i, rec);

                        positionY += height + 5;
                }
        }

        public void addFlight(int index, Rectangle rec) {
               // Text airlineText = createText(booked[index].getAirlineName(), 20, Color.BLACK, FontWeight.BOLD, "Segoe UI");
                Text airlineText = createText(userBookings.get(index).getAirLineName(), 20, Color.BLACK, FontWeight.BOLD, "Segoe UI");

                Text dateText = createText(userBookings.get(index).getArrivalDate(), 16, Color.BLACK, FontWeight.BOLD, "Segoe UI");

                Text timeText = createText(userBookings.get(index).getDepartureTime(), 16, Color.BLACK, FontWeight.BOLD, "Segoe UI");

                Text arrivalDateText = createText("Arrival Date", 16, Color.GRAY, FontWeight.NORMAL, "Segoe UI");
                // Set the positions of the text elements
                airlineText.setLayoutX(positionX + 30);
                airlineText.setLayoutY(positionY + 50);

                dateText.setLayoutX(positionX + width - dateText.getBoundsInLocal().getWidth() - 100);
                dateText.setLayoutY(positionY + 50);

                timeText.setLayoutX(positionX + width - timeText.getBoundsInLocal().getWidth() - 100);
                timeText.setLayoutY(positionY + 80);

                arrivalDateText.setLayoutX(positionX + width - arrivalDateText.getBoundsInLocal().getWidth() - 100);
                arrivalDateText.setLayoutY(positionY + 110);


                ImageView closeImageView = new ImageView("closeImage.png");
                        System.out.println("count : " + userBookingsCount);
                try {
                        System.out.println("sizeOfUserHistoryFlights : " + userHistoryFlights.length);
                        System.out.println("sizeOfDeleteButtons : " + deleteButtons.length);
                        System.out.println("index : " + index);
                        deleteButtons[index] = new Button();
                        deleteButtons[index].setBackground(null);
                        addDarkenEffect(deleteButtons[index], closeImageView);
                        deleteButtons[index].setGraphic(closeImageView);
                        deleteButtons[index].setLayoutX(positionX + width - 80);
                        deleteButtons[index].setLayoutY(positionY + 50);

                        deleteButtons[index].setOnMouseClicked(event -> onXClicked(index));

                        userHistoryFlights[index] = new UserHistoryFlight();
                        userHistoryFlights[index].setRec(rec);
                        userHistoryFlights[index].setX(closeImageView);
                        userHistoryFlights[index].setDelete(deleteButtons[index]);
                        userHistoryFlights[index].setAirlineText(airlineText);
                        userHistoryFlights[index].setDateText(dateText);
                        userHistoryFlights[index].setTimeText(timeText);
                        userHistoryFlights[index].setArrivalDateText(arrivalDateText);


                        overlay.getChildren().addAll(userHistoryFlights[index].getAirlineText(), userHistoryFlights[index].getDateText(),
                                userHistoryFlights[index].getTimeText(), userHistoryFlights[index].getArrivalDateText(), userHistoryFlights[index].getDelete());
                }
                catch (ArrayIndexOutOfBoundsException e) {
                        e.getMessage();
                }
        }

        private Text createText(String content, double fontSize, Color color, FontWeight fontWeight, String fontFamily) {
                Text text = new Text(content);
                text.setFont(Font.font(fontFamily, fontWeight, fontSize));
                text.setFill(color);
                return text;
        }

        private void addDarkenEffect(Button button, ImageView imageView) {
                ColorInput colorInput = new ColorInput(
                        0, 0,
                        imageView.getImage().getWidth(), imageView.getImage().getHeight(), Color.RED
                );

                Blend blend = new Blend(
                        BlendMode.MULTIPLY
                );
                Blend blend2 = new Blend(
                        BlendMode.OVERLAY
                );
                imageView.setEffect(blend2);

                button.setOnMouseEntered(event -> imageView.setEffect(blend));
                button.setOnMouseExited(event -> imageView.setEffect(blend2));
        }

        // static boolean confirmed = false;

        public void onXClicked(int index) {
                confirm(index);
        }

        public void confirm(int index) {
                if (index >= 0 && index < bookingRecords.size()) {
                        bookingRecords.get(mapIndicesOfUserBookingsToIndicesOfBookingRecords.get(index)).setCancelled(true);
                        removeFlightFromScreen(index);
                }
                userBookingsCount--;
        }

        public void removeFlightFromScreen(int index) {
                overlay.getChildren().remove(userHistoryFlights[index].getX());

                overlay.getChildren().remove(userHistoryFlights[index].getRec());
                overlay.getChildren().remove(userHistoryFlights[index].getDelete());

                overlay.getChildren().remove(userHistoryFlights[index].getAirlineText());
                overlay.getChildren().remove(userHistoryFlights[index].getDateText());
                overlay.getChildren().remove(userHistoryFlights[index].getTimeText());
                overlay.getChildren().remove(userHistoryFlights[index].getArrivalDateText());

                raiseBelowFlights(index + 1);

                //--size;

                // TODO : change the up label to curCnt
        }

        public void raiseBelowFlights(int index) {
                System.out.println(1000 * index);
                System.out.println(1000 * userBookingsCount);
                for (int i = index; i < userBookingsCount; ++i) {
                        double tmpY = userHistoryFlights[i].getRec().getLayoutY();
                        userHistoryFlights[i].getRec().setLayoutY(tmpY - (478 / 3 + 5));
                        userHistoryFlights[i].getX().setLayoutY(userHistoryFlights[i].getX().getLayoutY() - (478 / 3 + 5));
                        userHistoryFlights[i].getDelete().setLayoutY(userHistoryFlights[i].getDelete().getLayoutY() - (478 / 3 + 5));
                        userHistoryFlights[i].getAirlineText().setLayoutY(userHistoryFlights[i].getAirlineText().getLayoutY() - (478 / 3 + 5));
                        userHistoryFlights[i].getDateText().setLayoutY(userHistoryFlights[i].getDateText().getLayoutY() - (478 / 3 + 5));
                        userHistoryFlights[i].getTimeText().setLayoutY(userHistoryFlights[i].getTimeText().getLayoutY() - (478 / 3 + 5));
                        userHistoryFlights[i].getArrivalDateText().setLayoutY(userHistoryFlights[i].getArrivalDateText().getLayoutY() - (478 / 3 + 5));
                }

                overlay.setPrefHeight(overlay.getPrefHeight() - (478 / 3 + 5));
        }

        public void onBackClicked(MouseEvent event) {
                SceneSwitcher.switchScene(event, "SearchFlightPage", null);
        }
}
