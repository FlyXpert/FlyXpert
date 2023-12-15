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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class UserHistoryController implements Initializable {

        Flight[] booked = new Flight[10];
        int positionX = 0;
        int positionY = 0;
        int width = 558;
        int height = 478 / 3;
        int size = booked.length;

        @FXML
        ScrollPane scrollPane;

        @FXML
        Button backButton;

        Pane overlay = new Pane();

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

                backButton.setOnMouseClicked(mouseEvent -> onBackClicked());

                for (int i = 0; i < size; i++) {
                        booked[i] = new Flight(Flight.flights.get(i));
                }

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

        public void addFlight(int index, Rectangle rec) {
                double x = rec.getLayoutX() + 50;
                double y = rec.getLayoutX() + 20;

                Label airlineLabel = new Label();
                Label arrivalDayLabel = new Label();
                Label flightTimeLabel = new Label();

                airlineLabel.setLayoutX(x);
                airlineLabel.setLayoutY(y);

                airlineLabel.setText(booked[index].getAirlineName());
                airlineLabel.setFont(new Font(20));


                arrivalDayLabel.setLayoutX(x + 300);
                arrivalDayLabel.setLayoutY(y);
                arrivalDayLabel.setText(booked[index].getArrivalDate().getDay()
                        + "-" + booked[index].getArrivalDate().getMonth()
                        + "-" + booked[index].getArrivalDate().getYear());


                flightTimeLabel.setLayoutX(x + 300);
                flightTimeLabel.setLayoutX(y + 100);
                flightTimeLabel.setText(booked[index].getDepartureTime().getHour() + ":" + booked[index].getDepartureTime().getMinutes()
                        + " " + booked[index].getDepartureTime().getPeriod()
                        + " - " + booked[index].getArrivalTime().getHour() + ":" + booked[index].getArrivalTime().getMinutes()
                        + " " + booked[index].getArrivalTime().getPeriod());


                Text airlineText = createText(booked[index].getAirlineName(), 20, Color.BLACK, FontWeight.BOLD, "Segoe UI");

                Text dateText = createText(booked[index].getArrivalDate().getDay()
                        + "-" + booked[index].getArrivalDate().getMonth()
                        + "-" + booked[index].getArrivalDate().getYear(), 16, Color.BLACK, FontWeight.BOLD, "Segoe UI");

                Text timeText = createText(booked[index].getDepartureTime().getHour() + ":" + booked[index].getDepartureTime().getMinutes()
                        + " " + booked[index].getDepartureTime().getPeriod()
                        + " - " + booked[index].getArrivalTime().getHour() + ":" + booked[index].getArrivalTime().getMinutes()
                        + " " + booked[index].getArrivalTime().getPeriod(), 16, Color.BLACK, FontWeight.BOLD, "Segoe UI");

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
                Button delete = new Button();
                delete.setBackground(null);
                addDarkenEffect(delete, closeImageView);
                delete.setGraphic(closeImageView);
                delete.setLayoutX(positionX + width - 80);
                delete.setLayoutY(positionY + 50);

                delete.setOnMouseClicked(mouseEvent -> onXClicked());

                overlay.getChildren().addAll(airlineText, dateText, timeText, arrivalDateText, delete);
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


        public void onXClicked() {
                // TODO : delete flight from user
        }

        public void onBackClicked() {
                // TODO : switch to previous scene
        }
}
