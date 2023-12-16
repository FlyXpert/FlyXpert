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
import java.util.ResourceBundle;

import static flyxpert.flyxpert.BookingConfirmation.bookingRecords;

import static flyxpert.flyxpert.SceneSwitcher.stage;
import static flyxpert.flyxpert.User.currentUser;

public class UserHistoryController implements Initializable {

        //Flight[] booked = new Flight[size];
        int positionX = 0;
        int positionY = 0;
        int width = 558;
        int height = 478 / 3;
        ArrayList<BookingConfirmation> userBookings = new ArrayList<>();
        static int size = bookingRecords.size();

        @FXML
        ScrollPane scrollPane;

        @FXML
        Button backButton;

        @FXML
        Label youHaveXFlights;

        static Pane overlay = new Pane();

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

                int userBookingsCount = 0;
                for (int i = 0; i < size; i++) {
                        if (bookingRecords.get(i).getUserName().equals(currentUser.getUserName())) {
                                userBookings.add(bookingRecords.get(i));
                                ++userBookingsCount;
                                System.out.println(currentUser.getUserName());
                        }
                }
                youHaveXFlights.setText("You have " + userBookingsCount + " flights");

                overlay.setPrefSize(scrollPane.getPrefWidth(), height * size);
                overlay.setMaxWidth(scrollPane.getMaxWidth());

                generateBookedFlights();

                scrollPane.setContent(overlay);

                System.out.println(size);


        }

        public void generateBookedFlights() {
                for (int i = 0; i < size; ++i) {
                        Rectangle rec = new Rectangle(positionX, positionY, width, height);
                        rec.setFill(Color.WHITE);
                        overlay.getChildren().add(rec);

                        addFlight(i, rec);

                        positionY += height + 5;
                }
        }

        static UserHistoryFlight[] f = new UserHistoryFlight[size];
        Button[] deleteButtons = new Button[size];

        public void addFlight(int index, Rectangle rec) {
                double x = rec.getLayoutX() + 50;
                double y = rec.getLayoutX() + 20;

               // Text airlineText = createText(booked[index].getAirlineName(), 20, Color.BLACK, FontWeight.BOLD, "Segoe UI");
                Text airlineText = createText(bookingRecords.get(index).getAirLineName(), 20, Color.BLACK, FontWeight.BOLD, "Segoe UI");

                Text dateText = createText(bookingRecords.get(index).getArrivalDate(), 16, Color.BLACK, FontWeight.BOLD, "Segoe UI");

                Text timeText = createText(bookingRecords.get(index).getDepartureTime(), 16, Color.BLACK, FontWeight.BOLD, "Segoe UI");

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
                deleteButtons[index] = new Button();
                deleteButtons[index].setBackground(null);
                addDarkenEffect(deleteButtons[index], closeImageView);
                deleteButtons[index].setGraphic(closeImageView);
                deleteButtons[index].setLayoutX(positionX + width - 80);
                deleteButtons[index].setLayoutY(positionY + 50);

                deleteButtons[index].setOnMouseClicked(event -> onXClicked(index));

                f[index] = new UserHistoryFlight();
                f[index].setRec(rec);
                f[index].setX(closeImageView);
                f[index].setDelete(deleteButtons[index]);
                f[index].setAirlineText(airlineText);
                f[index].setDateText(dateText);
                f[index].setTimeText(timeText);
                f[index].setArrivalDateText(arrivalDateText);

                overlay.getChildren().addAll(f[index].getAirlineText(), f[index].getDateText(),
                        f[index].getTimeText(), f[index].getArrivalDateText(), f[index].getDelete());
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

        static boolean confirmed = false;

        public static void onXClicked(int index) {
                SceneSwitcher.createPopUp("ConfirmDelete");
                ConfirmBookingDeleteController.setData(index);
        }

        public static void confirm(int index) {
                // TODO : remove from bookingRecords

                removeFlightFromScreen(index);
                System.out.println(index);

                confirmed = false;
        }

        public static void removeFlightFromScreen(int index) {
                overlay.getChildren().remove(f[index].getX());

                overlay.getChildren().remove(f[index].getRec());
                overlay.getChildren().remove(f[index].getDelete());

                overlay.getChildren().remove(f[index].getAirlineText());
                overlay.getChildren().remove(f[index].getDateText());
                overlay.getChildren().remove(f[index].getTimeText());
                overlay.getChildren().remove(f[index].getArrivalDateText());

                raiseBelowFlights(index + 1);

                //--size;

                // TODO : change the up label to curCnt
        }

        public static void raiseBelowFlights(int index) {
                System.out.println(1000 * index);
                System.out.println(1000 * size);
                for (int i = index; i < size; ++i) {
                        double tmpY = f[i].getRec().getLayoutY();
                        f[i].getRec().setLayoutY(tmpY - (478 / 3 + 5));
                        f[i].getX().setLayoutY(f[i].getX().getLayoutY() - (478 / 3 + 5));
                        f[i].getDelete().setLayoutY(f[i].getDelete().getLayoutY() - (478 / 3 + 5));
                        f[i].getAirlineText().setLayoutY(f[i].getAirlineText().getLayoutY() - (478 / 3 + 5));
                        f[i].getDateText().setLayoutY(f[i].getDateText().getLayoutY() - (478 / 3 + 5));
                        f[i].getTimeText().setLayoutY(f[i].getTimeText().getLayoutY() - (478 / 3 + 5));
                        f[i].getArrivalDateText().setLayoutY(f[i].getArrivalDateText().getLayoutY() - (478 / 3 + 5));
                }
                // TODO : shorten the pane
                overlay.setPrefHeight(overlay.getPrefHeight() - (478 / 3 + 5));
        }

        public void onBackClicked(MouseEvent event) {
                SceneSwitcher.switchScene(event, "SearchFlightPage", null);
        }
}
