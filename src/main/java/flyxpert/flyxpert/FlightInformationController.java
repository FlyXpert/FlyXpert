package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class FlightInformationController implements Initializable {
    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillDataOfFlights();
    }

    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrollPane;
    public static boolean programStarted = false;

    public void fillDataOfFlights() {
        setScrollPane(scrollPane);
        if (!programStarted) {
            programStarted = true;
            showAllFlights();
        }
        else {
            clearOldData();
            showDesiredFlights();
        }
        vboxStyling(vbox);
    }
    private void showAllFlights() {
        fillVbox();
        fillMenuButtonsItems();
    }
    private void showDesiredFlights() {
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
    private void clearOldData() {
        vbox.getChildren().clear();
    }
    private void fillVbox() {
        try {
            for(Flight flight : Flight.flights) {
                vbox.getChildren().add(createFlight(flight));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void vboxStyling(VBox vbox) {
        vbox.setSpacing(20);
        vbox.setStyle("-fx-background-color: white;");
    }
    private void setScrollPane(ScrollPane scrollPane) {
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
    private HBox createFlight(Flight flight){
        HBox hbox = new HBox();
        setHboxLabels(hbox, flight);
        hboxStyling(hbox);
        setHboxOnAction(hbox, flight);
        return hbox;
    }
    private void hboxStyling(HBox hbox) {
        hboxStyle(hbox);
        hboxHover(hbox);
        hboxClicked(hbox);
    }
    private void hboxStyle(HBox hbox) {
        hbox.setStyle("-fx-background-color: white;" +
                " -fx-border-width: 1;" +
                " -fx-border-color: black;");
    }
    private void hboxHover(HBox hbox) {
        hbox.setOnMouseEntered(event ->{
            hbox.setStyle("-fx-background-color: #605DEC; -fx-border-color: black;");
            hbox.getChildren().forEach(node -> {
                if (node instanceof Label) {
                    setLabelStyle((Label) node, "white");
                }
            });
        });

        hbox.setOnMouseExited(event ->{
            hbox.setStyle("-fx-background-color: white; -fx-border-color: black;");
            hbox.getChildren().forEach(node -> {
                if (node instanceof Label) {
                    setLabelStyle((Label) node, "black");
                }
            });
        });
    }
    private void hboxClicked(HBox hbox) {
        hbox.setOnMousePressed(event ->{
            hbox.setStyle("-fx-background-color: #4422ac; -fx-border-color: white;");
            hbox.getChildren().forEach(node -> {
                if (node instanceof Label) {
                    setLabelStyle((Label) node, "white");
                }
            });
        });
        hbox.setOnMouseReleased(event ->{
            hbox.setStyle("-fx-background-color: #605DEC; -fx-border-color: black;");
            hbox.getChildren().forEach(node -> {
                if (node instanceof Label) {
                    setLabelStyle((Label) node, "white");
                }
            });
        });
    }
    private static Label setLabelStyle(Label label, String color)  {
        if(color.equals("white")) {
            label.setStyle("-fx-pref-width: 250;" +
                    "-fx-padding: 25;" +
                    " -fx-border-width: 5;" +
                    " -fx-alignment: center;" +
                    " -fx-font-size: 14; " +
                    "-fx-text-fill: white");
            return label;
        }
        label.setStyle("-fx-pref-width: 250;" +
                "-fx-padding: 25;" +
                " -fx-border-width: 5;" +
                " -fx-alignment: center;" +
                " -fx-font-size: 14;" +
                "-fx-text-fill: black");
        return label;
    }
    private void setHboxLabels(HBox hbox, Flight flight) {
        setAirlineLabel(hbox, flight);
        setTimeLabel(hbox, flight);
        setDateLabel(hbox, flight);
        setPriceLabel(hbox, flight);
    }
    private void setAirlineLabel(HBox hbox, Flight flight) {
        Label airLineLabel = createAirlineLabel(flight);
        FlightInformationController.setLabelStyle(airLineLabel,"black");
        hbox.getChildren().add(airLineLabel);
    }
    private void setTimeLabel(HBox hbox, Flight flight) {
        Label timeLabel = createTimeLabel(flight);
        FlightInformationController.setLabelStyle(timeLabel,"black");
        hbox.getChildren().add(timeLabel);
    }
    private void setDateLabel(HBox hbox, Flight flight) {
        Label dateLabel = createDateLabel(flight);
        FlightInformationController.setLabelStyle(dateLabel,"black");
        hbox.getChildren().add(dateLabel);
    }
    private void setPriceLabel(HBox hbox, Flight flight) {
        Label priceLabel = createPriceLabel(flight);

        if (seatClassButton.getText().equals("Business class")) {
            priceLabel.setText(Integer.toString(flight.getBusinessPrice()));
        }
        else if (seatClassButton.getText().equals("First class")) {
            priceLabel.setText(Integer.toString(flight.getFirstClassPrice()));
        }
        FlightInformationController.setLabelStyle(priceLabel,"black");
        hbox.getChildren().add(priceLabel);
    }
    private Label createAirlineLabel(Flight flight) {
        return new Label(flight.getAirlineName() + " Airlines");
    }
    private Label createTimeLabel(Flight flight) {
        return new Label(flight.getDepartureTime().getHour() + ":" + flight.getDepartureTime().getMinutes()
                + " " + flight.getDepartureTime().getPeriod()
                + " - " + flight.getArrivalTime().getHour() + ":" + flight.getArrivalTime().getMinutes()
                + " " + flight.getArrivalTime().getPeriod());
    }
    private Label createDateLabel(Flight flight) {
        return new Label(flight.getDepartureDate().getDay()
                + "-" + flight.getDepartureDate().getMonth()
                + "-" + flight.getDepartureDate().getYear());
    }
    private Label createPriceLabel(Flight flight) {
        return new Label(Integer.toString(flight.getEconomyPrice()));
    }
    private void setHboxOnAction(HBox hbox, Flight flight) {
        hbox.setOnMouseClicked(event -> {
            try {
                if(numOfPassengers.getText().isBlank()) {
                    invalidInputOfPassengersMsg.setVisible(true);
                }
                else{
                    invalidInputOfPassengersMsg.setVisible(false);
                    if(validNum(maxPrice) || maxPrice.getText().isBlank()) {
                        invalidInputForPriceMsg.setVisible(false);
                        DetailsConfirmationController.handleHBoxClick(flight, getNumberOfPassengers());
                    }
                    else {
                        invalidInputForPriceMsg.setVisible(true);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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

    Map<String, Boolean> departureAirports = new HashMap<String, Boolean>();
    Map<String, Boolean> arrivalAirports = new HashMap<String, Boolean>();
    Map<String, Boolean> airlines = new HashMap<String, Boolean>();


    private void fillMaps() {
        for (Flight flight : Flight.flights) {
            fillDepartureAirportsMap(flight);
            fillArrivalAirportsMap(flight);
            fillAirlinesMap(flight);
        }
    }
    private void fillDepartureAirportsMap(Flight flight) {
        if(departureAirports.get(flight.getDepartureAirport().getCode()) == null) {
            MenuItem departureAirportCode = new MenuItem(flight.getDepartureAirport().getCode());
            departureAirportCode.setOnAction(this::setFromWhereMenuButtonText);
            fromWhereMenuButton.getItems().add(departureAirportCode);
            departureAirports.put(flight.getDepartureAirport().getCode() , true);
        }
    }
    private void fillArrivalAirportsMap(Flight flight) {
        if(arrivalAirports.get(flight.getArrivalAirport().getCode()) == null) {
            MenuItem arrivalAirportCode = new MenuItem(flight.getArrivalAirport().getCode());
            arrivalAirportCode.setOnAction(this::setWhereToMenuButtonText);
            whereToMenuButton.getItems().add(arrivalAirportCode);
            arrivalAirports.put(flight.getArrivalAirport().getCode() , true);
        }
    }
    private void fillAirlinesMap(Flight flight) {
        if(airlines.get(flight.getAirlineName()) == null){
            MenuItem airlines = new MenuItem(flight.getAirlineName());
            airlines.setOnAction(this::setAirlinesMenuButtonText);
            airlinesButton.getItems().add(airlines);
            this.airlines.put(flight.getAirlineName(), true);
        }
    }
    private void fillTimeButtons() {
        fillMaxArrivalTimeMenuContext();
        fillMaxDepartureTimeMenuContext();
    }
    private void fillMaxDepartureTimeMenuContext() {
        setTimes("departure");
    }
    private void fillMaxArrivalTimeMenuContext() {
        setTimes("arrival");
    }
    private void setTimes(String type) {
        for (int i = 1; i <= 24; i++) {
            MenuItem time = setTime(i);
            prependZeroIfNeeded(time, i);
            appendPeriod(time, i);
            switch (type) {
                case "departure":
                    setDepartureTimeItemOnAction(time);
                    addItemToMenu(time, maxDepartTimeButton);
                    break;
                case "arrival":
                    setArrivalTimeItemOnAction(time);
                    addItemToMenu(time, maxArrivalTimeButton);
                    break;
            }
        }
    }
    private void setDepartureTimeItemOnAction(MenuItem time) {
        time.setOnAction(this::setMaxArrivalTimeMenuButtonText);
    }
    private void setArrivalTimeItemOnAction(MenuItem time) {
        time.setOnAction(this::setMaxArrivalTimeMenuButtonText);
    }
    private void addItemToMenu(MenuItem item, MenuButton menuButton) {
        menuButton.getItems().add(item);
    }
    private MenuItem setTime(int i) {
        return new MenuItem(Integer.toString(i));
    }
    private void prependZeroIfNeeded(MenuItem time, int i) {
        if ((i % 13) < 10){
            time.setText("0" + time.getText());
        }
    }
    private void appendPeriod(MenuItem time, int i) {
        if (i < 13) {
            appendAM(time);
        }
        else {
            appendPM(time);
        }
    }
    private void appendAM(MenuItem time) {
        time.setText(time.getText() + " AM");
    }
    private void appendPM(MenuItem time) {
        time.setText(time.getText() + " PM");
    }
    public void fillMenuButtonsItems() {
        fillMaps();
        departureAirports.clear();
        arrivalAirports.clear();
        airlines.clear();
        fillTimeButtons();
    }
    private boolean desiredDepartureAirportCode(Flight flight) {
        return getDepartureAirportCode().equals(flight.getDepartureAirport().getCode());
    }
    private boolean desiredArrivalAirportCode(Flight flight) {
        return getArrivalAirportCode().equals(flight.getArrivalAirport().getCode());
    }
    private boolean desiredDepartureDate(Flight flight) {
        if(!getDepartureDate().getDay().equals(flight.getDepartureDate().getDay())) {
            return false;
        }
        if(!getDepartureDate().getMonth().equals(flight.getDepartureDate().getMonth())) {
            return false;
        }
        if(!getDepartureDate().getYear().equals(flight.getDepartureDate().getYear())) {
            return false;
        }
        return true;
    }
    private boolean validNumOfPassengers(Flight flight) {
        return getNumberOfPassengers() <= flight.getAvailableSeats();
    }
    private boolean desiredMaxDepartureTime(Flight flight) {
        if(!maxDepartTimeButton.getText().equals("Max departure time")) {
            return validTime(getMaxDepartureTime(), flight.getDepartureTime());
        }
        return true;
    }
    private boolean desiredMaxArrivalTime(Flight flight) {
        if (!maxArrivalTimeButton.getText().equals("Max arrival time")) {
            return validTime(getMaxArrivalTime(), flight.getArrivalTime());
        }
        return true;
    }
    private boolean desiredAirline(Flight flight) {
        if (!airlinesButton.getText().equals("Airlines")) {
            return getAirline().equals(flight.getAirlineName());
        }
        return true;
    }
    private boolean validMaxPrice(Flight flight) {
        if(!maxPrice.getText().isEmpty()) {
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
    public boolean desiredSearchData(Flight flight) {
        if(!desiredDepartureAirportCode(flight)) {
            return false;
        }
        if(!desiredArrivalAirportCode(flight)) {
            return false;
        }
        if(!desiredDepartureDate(flight)) {
            return false;
        }
        if(!validNumOfPassengers(flight)) {
            return false;
        }
        if(!desiredMaxDepartureTime(flight)) {
            return false;
        }
        if(!desiredMaxArrivalTime(flight)) {
            return false;
        }
        if(!desiredAirline(flight)) {
            return false;
        }
        if(!validMaxPrice(flight)) {
            return false;
        }
        return true;
    }
    public void enableSearchButton() {
        if(!fromWhereUsed()) {
            searchButton.setDisable(false);
            return;
        }
        if(!whereToUsed()) {
            searchButton.setDisable(false);
            return;
        }
        if(!datePickerUsed()) {
            searchButton.setDisable(false);
            return;
        }
        if(!validNum(numOfPassengers)) {
            searchButton.setDisable(false);
            return;
        }
        if(!validMaxPrice()) {
            searchButton.setDisable(false);
            return;
        }
        searchButton.setDisable(true);
    }
    private boolean datePickerUsed() {
        return datePicker.getValue() != null;
    }
    private boolean fromWhereUsed() {
        return !fromWhereMenuButton.getText().equals("From Where ?");
    }
    private boolean whereToUsed() {
        return !whereToMenuButton.getText().equals("Where to ?");
    }
    private boolean validMaxPrice() {
        return maxPrice.getText().isEmpty() || validNum(maxPrice);
    }
    private boolean validNum(String text) {
        try {
            int value = Integer.parseInt(text);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean validNum(TextField numOfPassengers) {
        String text = numOfPassengers.getText().trim();
        if (!text.isEmpty()) {
            return validNum(text);
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
        String day = chosenDay();
        String month = chosenMonth();
        String year = chosenYear();
        prependZeroIfNeeded(day);
        prependZeroIfNeeded(month);
        return new NewDate (day, month, year);
    }
    private String chosenDay() {
        return Integer.toString(datePicker.getValue().getDayOfMonth());
    }
    private String chosenMonth() {
        return Integer.toString(datePicker.getValue().getMonth().getValue());
    }
    private String chosenYear() {
        return Integer.toString(datePicker.getValue().getYear());
    }
    private void prependZeroIfNeeded(String item) {
        if (Integer.parseInt(item) < 10) {
            item = "0" + item;
        }
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
