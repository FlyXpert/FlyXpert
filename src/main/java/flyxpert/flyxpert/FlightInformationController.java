package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FlightInformationController{
    @FXML
    private VBox vbox;

    public void fillDataOfFlights(){

        try {
               for(Flight flight : Flight.flights) {
                   vbox.getChildren().add(createFlight(flight));
               }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private HBox createFlight(Flight flight){
        HBox hbox = new HBox();
        hbox.setStyle("-fx-border-color: black;");

        Label airLineLabel = new Label(flight.getAirlineName() + " Airlines");

        FlightInformationController.setLabelStyle(airLineLabel);
        hbox.getChildren().add(airLineLabel);

        Label timeLabel = new Label(flight.getDepartureTime().getHour() + ":" + flight.getDepartureTime().getMinutes()
                + " " + flight.getDepartureTime().getPeriod()
                + " - " + flight.getArrivalTime().getHour() + ":" + flight.getArrivalTime().getMinutes()
                + " " + flight.getArrivalTime().getPeriod());

        FlightInformationController.setLabelStyle(timeLabel);
        hbox.getChildren().add(timeLabel);

        Label dayLabel = new Label(flight.getArrivalDay().getDay()
                + "-" + flight.getArrivalDay().getMonth()
                + "-" + flight.getArrivalDay().getYear());


        FlightInformationController.setLabelStyle(dayLabel);
        hbox.getChildren().add(dayLabel);

        Label priceLabel = new Label(Integer.toString(flight.getEconomyPrice()));

        FlightInformationController.setLabelStyle(priceLabel);
        hbox.getChildren().add(priceLabel);

        hbox.setOnMouseClicked(event -> {
            try {
                DetailsConfirmationController.handleHBoxClick(flight);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return hbox;
    }

    private static Label setLabelStyle(Label label) {
        label.setStyle("-fx-padding: 20;");

        return label;
    }



    @FXML
    private MenuButton fromWhereMenuButton;
    @FXML
    private MenuButton whereToMenuButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField numOfPassengers;
    @FXML
    private Label invalidInputMsg;
    @FXML
    private MenuButton maxDepartTimeButton;
    @FXML
    private MenuButton maxArrivalTimeButton;
    @FXML
    private MenuButton airlinesButton;
    @FXML
    private MenuButton seatClassButton;
    @FXML
    private Button searchButton;

    public boolean validNum(TextField numOfPassengers) {

        String text = numOfPassengers.getText().trim();

        if (!text.isEmpty()) {
            try {
                int value = Integer.parseInt(text);
                invalidInputMsg.setVisible(false);
                return value > 0 && value < 100;

            } catch (NumberFormatException e) {
                invalidInputMsg.setVisible(true);
                return false;
            }
        }
        return false;
    }
    public void enableSearchButton() {
        boolean isDatePickerUsed = datePicker.getValue() != null;
        boolean isFromWhereSelected = !fromWhereMenuButton.getText().equals("From Where ?");
        boolean isWhereToSelected = !whereToMenuButton.getText().equals("Where to ?");
        boolean isValidNumOfPassengers = validNum(numOfPassengers);
        if (isDatePickerUsed && isFromWhereSelected && isWhereToSelected && isValidNumOfPassengers) {
            searchButton.setDisable(false);
        }
        else {
            searchButton.setDisable(true);
        }
    }
    @FXML
    private void setFromWhereMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        fromWhereMenuButton.setText(menuItem.getText());
    }
    @FXML
    private void setWhereToMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        whereToMenuButton.setText(menuItem.getText());
    }
    @FXML
    private void setMaxDepartTimeButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        maxDepartTimeButton.setText(menuItem.getText());
    }
    @FXML
    private void setMaxArrivalTimeButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        maxArrivalTimeButton.setText(menuItem.getText());
    }
    @FXML
    private void setAirlinesButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        airlinesButton.setText(menuItem.getText());
    }
    @FXML
    private void setSeatClassButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        seatClassButton.setText(menuItem.getText());
    }
}
