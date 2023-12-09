package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.Date;

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
        fillMenuButtonsItems();
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
    private Label invalidInputOfPassengersMsg;
    @FXML
    private Label invalidInputForPriceMsg;
    @FXML
    private MenuButton maxDepartTimeButton;
    @FXML
    private MenuButton maxArrivalTimeButton;
    @FXML
    private MenuButton airlinesButton;
    @FXML
    private MenuButton seatClassButton;
    @FXML
    private TextField maxPrice;
    @FXML
    private Button searchButton;

    public void fillMenuButtonsItems() {

        for (Airport airport: Airport.airports) {


            // filling the Departure Airport Code Button items

            MenuItem deptAirportCode = new MenuItem(airport.getCode());
            deptAirportCode.setOnAction(this::setFromWhereMenuButtonText);
            fromWhereMenuButton.getItems().add(deptAirportCode);


            // filling the Arrival Airport Code Button items

            MenuItem arrivalAirportCode = new MenuItem(airport.getCode());
            arrivalAirportCode.setOnAction(this::setWhereToMenuButtonText);
            whereToMenuButton.getItems().add(arrivalAirportCode);


            // filling the Max Departure time Button items

            MenuItem maxDepartTime = new MenuItem(airport.getCode());
            maxDepartTime.setOnAction(this::setMaxDepartTimeMenuButtonText);
            maxDepartTimeButton.getItems().add(maxDepartTime);


            // filling the Max Arrival time Button items

            MenuItem maxArrivalTime = new MenuItem(airport.getCode());
            maxArrivalTime.setOnAction(this::setMaxArrivalTimeMenuButtonText);
            maxArrivalTimeButton.getItems().add(maxArrivalTime);


            // filling the Airlines Button items

            MenuItem airlines = new MenuItem(airport.getCode());
            airlines.setOnAction(this::setAirlinesMenuButtonText);
            airlinesButton.getItems().add(airlines);

        }

    }
    public void searchData() {
        getDepartureAirportCode();
        getArrivalAirportCode();
        getDepartureDate();
        getNumberOfPassengers();
        /*getMaxDepartureTime();
        getMaxArrivalTime();*/
        getAirline();
        getMaxPrice();
    }
    public void enableSearchButton() {

        boolean isDatePickerUsed = datePicker.getValue() != null;
        boolean isFromWhereSelected = !fromWhereMenuButton.getText().equals("From Where ?");
        boolean isWhereToSelected = !whereToMenuButton.getText().equals("Where to ?");
        boolean isValidNumOfPassengers = validNumOfPassengers(numOfPassengers);
        if (isDatePickerUsed && isFromWhereSelected && isWhereToSelected &&
                isValidNumOfPassengers && !invalidInputForPriceMsg.isVisible()) {
            searchButton.setDisable(false);
        }
        else {
            searchButton.setDisable(true);
        }
    }
    private boolean validNumOfPassengers(TextField numOfPassengers) {

        String text = numOfPassengers.getText().trim();

        if (!text.isEmpty()) {

            try {
                int value = Integer.parseInt(text);
                invalidInputOfPassengersMsg.setVisible(false);
                return value > 0 && value < 100;

            } catch (NumberFormatException e) {
                invalidInputOfPassengersMsg.setVisible(true);
                return false;
            }
        }
        return false;
    }
    @FXML
    private void validNumForPrice(ActionEvent actionEvent) {

        String text = maxPrice.getText().trim();

        if (!text.isEmpty()) {

            try {
                int value = Integer.parseInt(text);
                invalidInputForPriceMsg.setVisible(false);
                enableSearchButton();

            } catch (NumberFormatException e) {
                invalidInputForPriceMsg.setVisible(true);
                searchButton.setDisable(true);
            }
        }
        if(text.isEmpty()){
            invalidInputForPriceMsg.setVisible(false);
            enableSearchButton();
        }
    }
    @FXML
    private void setFromWhereMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        fromWhereMenuButton.setText(menuItem.getText());
        enableSearchButton();
    }
    @FXML
    private void setWhereToMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        whereToMenuButton.setText(menuItem.getText());
        enableSearchButton();
    }
    @FXML
    private void setMaxDepartTimeMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        maxDepartTimeButton.setText(menuItem.getText());
    }
    @FXML
    private void setMaxArrivalTimeMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        maxArrivalTimeButton.setText(menuItem.getText());
    }
    @FXML
    private void setAirlinesMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        airlinesButton.setText(menuItem.getText());
    }
    @FXML
    private void setSeatClassMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        seatClassButton.setText(menuItem.getText());
    }
    private String getDepartureAirportCode() {
        return fromWhereMenuButton.getText();
    }
    private String getArrivalAirportCode() {
        return whereToMenuButton.getText();
    }
    private LocalDate getDepartureDate() {
        return datePicker.getValue();
    }
    private int getNumberOfPassengers() {
        return Integer.parseInt(numOfPassengers.getText());
    }
    /*private void setMaxDepartTimeButton() {

    }
    private Time getMaxDepartureTime() {

    }
    private Time getMaxArrivalTime() {

    }*/
    private String getAirline() {
        return airlinesButton.getText();
    }
    private int getMaxPrice() {
        return Integer.parseInt(maxPrice.getText());
    }
}
