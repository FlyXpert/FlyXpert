package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.Map;

public class FlightInformationController{
    @FXML
    private VBox vbox;
    public static boolean programStarted = false;

    public void fillDataOfFlights(){

        if(!programStarted) {

            programStarted = true;

        try {
               for(Flight flight : Flight.flights) {
                   vbox.getChildren().add(createFlight(flight));
               }
            } catch (Exception e) {
                e.printStackTrace();
            }

            fillMenuButtonsItems();

        }
        else {

            vbox.getChildren().clear();

            try {
                for(Flight flight : Flight.flights) {
                    if(desiredSearchData(flight)) {
                        vbox.getChildren().add(createFlight(flight));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        Label dayLabel = new Label(flight.getDepartureDate().getDay()
                + "-" + flight.getDepartureDate().getMonth()
                + "-" + flight.getDepartureDate().getYear());


        FlightInformationController.setLabelStyle(dayLabel);
        hbox.getChildren().add(dayLabel);

        Label priceLabel = new Label(Integer.toString(flight.getEconomyPrice()));

        if (seatClassButton.getText().equals("Business class")) {
            priceLabel.setText(Integer.toString(flight.getBusinessPrice()));
        }
        else if (seatClassButton.getText().equals("First class")) {
            priceLabel.setText(Integer.toString(flight.getFirstClassPrice()));
        }

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

    private static Label setLabelStyle(Label label)  {
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

    Map<String, Boolean> uniqueDepartureAirports = new HashMap<String, Boolean>();
    Map<String, Boolean> uniqueArrivalAirports = new HashMap<String, Boolean>();
    Map<String, Boolean> uniqueAirlines = new HashMap<String, Boolean>();
    public void fillMenuButtonsItems() {

        for (Flight flight : Flight.flights) {

            // filling the Departure Airport Code Button items

            if(uniqueDepartureAirports.get(flight.getDepartureAirport().getCode()) == null) {

                MenuItem departureAirportCode = new MenuItem(flight.getDepartureAirport().getCode());
                departureAirportCode.setOnAction(this::setFromWhereMenuButtonText);
                fromWhereMenuButton.getItems().add(departureAirportCode);
                uniqueDepartureAirports.put(flight.getDepartureAirport().getCode() , true);
            }


            // filling the Arrival Airport Code Button items

            if(uniqueArrivalAirports.get(flight.getArrivalAirport().getCode()) == null) {

                MenuItem arrivalAirportCode = new MenuItem(flight.getArrivalAirport().getCode());
                arrivalAirportCode.setOnAction(this::setWhereToMenuButtonText);
                whereToMenuButton.getItems().add(arrivalAirportCode);
                uniqueArrivalAirports.put(flight.getArrivalAirport().getCode() , true);
            }


            // filling the Airlines Button items

            if(uniqueAirlines.get(flight.getAirlineName()) == null){

                MenuItem airlines = new MenuItem(flight.getAirlineName());
                airlines.setOnAction(this::setAirlinesMenuButtonText);
                airlinesButton.getItems().add(airlines);
                uniqueAirlines.put(flight.getAirlineName(), true);
            }

        }

        uniqueDepartureAirports.clear();
        uniqueArrivalAirports.clear();
        uniqueAirlines.clear();

        for(int i=1 ; i <= 24 ; i++){

            MenuItem time = new MenuItem();

            if(i < 13){
                if(i<10){
                    time.setText("0" + Integer.toString(i) + " AM");
                }
                else {
                    time.setText(Integer.toString(i) + " AM");
                }
            }
            else{
                if(i<22){
                    time.setText("0" + Integer.toString(i-12) + " PM");
                }
                else {
                    time.setText(Integer.toString(i-12) + " PM");
                }
            }

            time.setOnAction(this::setMaxDepartTimeMenuButtonText);
            maxDepartTimeButton.getItems().add(time);

        }

        for(int i=1 ; i <= 24 ; i++){

            MenuItem time = new MenuItem();

            if(i < 13){
                if(i<10){
                    time.setText("0" + Integer.toString(i) + " AM");
                }
                else {
                    time.setText(Integer.toString(i) + " AM");
                }
            }
            else{
                if(i<22){
                    time.setText("0" + Integer.toString(i-12) + " PM");
                }
                else {
                    time.setText(Integer.toString(i-12) + " PM");
                }
            }

            time.setOnAction(this::setMaxArrivalTimeMenuButtonText);
            maxArrivalTimeButton.getItems().add(time);

        }
    }
    public boolean desiredSearchData(Flight flight) {


        // checking departure Airport code

        if (!getDepartureAirportCode().equals(flight.getDepartureAirport().getCode())) {
            return false;
        }


        // checking arrival Airport code

        if (!getArrivalAirportCode().equals(flight.getArrivalAirport().getCode())) {
            return false;
        }


        // checking departure date

        if(!getDepartureDate().getDay().equals(flight.getDepartureDate().getDay())) {
            return false;
        }
        if(!getDepartureDate().getMonth().equals(flight.getDepartureDate().getMonth())) {
            return false;
        }
        if(!getDepartureDate().getYear().equals(flight.getDepartureDate().getYear())) {
            return false;
        }


        // checking if number of passengers is valid

        if (getNumberOfPassengers() > flight.getAvailableSeats()) {
            return false;
        }


        // checking for the maximum departure time

        if(!maxDepartTimeButton.getText().equals("Max departure time")) {
            if(!validTime(getMaxDepartureTime(), flight.getDepartureTime())) {
                return false;
            }
        }


        // checking for the maximum arrival time

        if (!maxArrivalTimeButton.getText().equals("Max arrival time")) {
            if(!validTime(getMaxArrivalTime(), flight.getArrivalTime())) {
                return false;
            }
        }


        // checking for the Airline

        if (!airlinesButton.getText().equals("Airlines")) {

            if (!getAirline().equals(flight.getAirlineName())) {
                return false;
            }

        }


        // checking for the maximum price

        if(!maxPrice.getText().isEmpty()) {


            // checking if the user provided his seat class

            if(!getSeatClass().equals("Seat class")) {

                if(getSeatClass().equals("Economy class")) {
                    if(getMaxPrice() < flight.getEconomyPrice()) {
                        return false;
                    }
                }

                if(getSeatClass().equals("Business class")) {
                    if(getMaxPrice() < flight.getBusinessPrice()) {
                        return false;
                    }
                }

                if(getSeatClass().equals("First class")) {
                    if(getMaxPrice() < flight.getFirstClassPrice()) {
                        return false;
                    }
                }

            }
            else if (getMaxPrice() < flight.getEconomyPrice()) {
                System.out.println("return false");
                return false;
            }

        }


        return true;

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

    private boolean validTime(Time desiredMaxTime, Time flightTime) {


        String hour = desiredMaxTime.getHour();

        String period = desiredMaxTime.getPeriod();


        if (period.equals("PM")) {

            if(flightTime.getPeriod().equals("PM")) {

                if (flightTime.getHour().compareTo(hour) > 0) {
                    return false;
                }
            }
        }
        if (period.equals("AM")) {

            if (flightTime.getPeriod().equals("PM")) {
                return false;
            }

            if (flightTime.getHour().compareTo(hour) > 0) {
                return false;
            }

        }
        return true;
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
    private NewDate getDepartureDate() {

        String day = Integer.toString(datePicker.getValue().getDayOfMonth());

        String month = Integer.toString(datePicker.getValue().getMonth().getValue());

        String year = Integer.toString(datePicker.getValue().getYear());

        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }

        return new NewDate (day, month, year);
    }
    private int getNumberOfPassengers() {
        return Integer.parseInt(numOfPassengers.getText());
    }
    private Time getMaxDepartureTime() {
        String time = maxDepartTimeButton.getText();
        return new Time(String.valueOf(time.charAt(0)) + String.valueOf(time.charAt(1)),
                "00",String.valueOf(time.charAt(3)) + String.valueOf(time.charAt(4)));
    }
    private Time getMaxArrivalTime() {
        String time = maxArrivalTimeButton.getText();

        String hour = String.valueOf(time.charAt(0)) + String.valueOf(time.charAt(1));
        String minutes = "00";
        String year = String.valueOf(time.charAt(3)) + String.valueOf(time.charAt(4));

        return new Time(hour, minutes, year);
    }
    private String getAirline() {
        return airlinesButton.getText();
    }
    private String getSeatClass() {
        return seatClassButton.getText();
    }
    private int getMaxPrice() {
        return Integer.parseInt(maxPrice.getText());
    }
}
