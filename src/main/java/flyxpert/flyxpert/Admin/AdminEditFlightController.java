package flyxpert.flyxpert.Admin;

import flyxpert.flyxpert.Miscellaneous.NewDate;
import flyxpert.flyxpert.Miscellaneous.SceneSwitcher;
import flyxpert.flyxpert.Miscellaneous.Time;
import flyxpert.flyxpert.Validators.ValidateAirports;
import flyxpert.flyxpert.Validators.ValidateName;
import flyxpert.flyxpert.Validators.ValidateNumber;
import flyxpert.flyxpert.Validators.ValidateTime;
import flyxpert.flyxpert.Flight.Airport;
import flyxpert.flyxpert.Flight.Flight;
import flyxpert.flyxpert.Flight.FlightInformationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AdminEditFlightController implements Initializable {


    @FXML
    public TextField airlineTextFieldEdit;
    @FXML
    public TextField departureAirportCodeTextFieldEdit;
    @FXML
    public TextField departureAirportNameTextFieldEdit;
    @FXML
    public TextField departureAirportLocationTextFieldEdit;
    @FXML
    public TextField arrivalAirportCodeTextFieldEdit;
    @FXML
    public TextField arrivalAirportNameTextFieldEdit;
    @FXML
    public TextField arrivalAirportLocationTextFieldEdit;
    @FXML
    public TextField departureTimeTextFieldEdit;
    @FXML
    public TextField arrivalTimeTextFieldEdit;
    @FXML
    public TextField economySeatPriceTextFieldEdit;
    @FXML
    public TextField businessSeatPriceTextFieldEdit;
    @FXML
    public TextField firstClassSeatPriceTextFieldEdit;
    @FXML
    public DatePicker departureDateDatePicker;
    @FXML
    public DatePicker arrivalDateDatePicker;
    @FXML
    public Label warningLabel;
    @FXML
    public Label successfullLabel;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
        flightToEdit = Flight.flights.get(Flight.selectedFlightIndex);
    }

    private Flight flightToEdit;
    private Airport departureAirportToEdit;
    private Airport arrivalAirportToEdit;
    private String departureAirportCodeToEdit;
    private String arrivalAirportCodeToEdit;
    private String departureAirportNameToEdit;
    private String arrivalAirportNameToEdit;
    private String departureAirportLocationToEdit;
    private String arrivalAirportLocationToEdit;
    private String airlineNameToEdit;
    private Time departureTimeToEdit;
    private Time arrivalTimeToEdit;
    private NewDate arrivalDateToEdit;
    private NewDate departureDateToEdit;
    private int economyPriceToEdit;
    private int bussinessPriceToEdit;
    private int firstClassPriceToEdit;
    private String departureDateAsString;
    private String arrivalDateAsString;

    private ValidateNumber validateNumber = new ValidateNumber();
    private ValidateName validateStrings = new ValidateName();
    private ValidateAirports validateAirports = new ValidateAirports();
    private ValidateTime validateTime = new ValidateTime();
    String pattern = "^(1[0-2]|0?[1-9]):([0-5][0-9])(AM|PM)$";
    Pattern timePattern = Pattern.compile(pattern);


    public void AdminEditFlightAction(ActionEvent event) throws ParseException
    {
        if(AdminEditFlight())
        {
            warningLabel.setText("");
            successfullLabel.setText("Flight Edited Successfully");
            FlightInformationController.airlines.put(airlineNameToEdit,true);
            FlightInformationController.arrivalAirports.put(arrivalAirportCodeToEdit,true);
            FlightInformationController.departureAirports.put(departureAirportCodeToEdit,true);
        }
    }
    private boolean AdminEditFlight()
    {

        if(validateStrings.validateData(airlineTextFieldEdit.getText()))
        {
            airlineNameToEdit = airlineTextFieldEdit.getText();
            flightToEdit.setAirlineName(airlineTextFieldEdit.getText());
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airline");
            return false;
        }

        if(validateAirports.validateData(departureAirportCodeTextFieldEdit.getText()))
        {
            departureAirportCodeToEdit = departureAirportCodeTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport Code");
            return false;
        }

        if(validateStrings.validateData(departureAirportNameTextFieldEdit.getText()))
        {
            departureAirportNameToEdit = departureAirportNameTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport Name");
            return false;
        }

        if(validateStrings.validateData(departureAirportLocationTextFieldEdit.getText()))
        {
            departureAirportLocationToEdit = departureAirportLocationTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport Location");
            return false;
        }

        departureAirportToEdit = new Airport(departureAirportCodeToEdit,departureAirportNameToEdit,departureAirportLocationToEdit);
        flightToEdit.setDepartureAirport(departureAirportToEdit);

        if(validateAirports.validateData(arrivalAirportCodeTextFieldEdit.getText()))
        {
            arrivalAirportCodeToEdit = arrivalAirportCodeTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport Code");
            return false;
        }

        if(validateStrings.validateData(arrivalAirportNameTextFieldEdit.getText()))
        {
            arrivalAirportNameToEdit = arrivalAirportNameTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport Name");
            return false;
        }

        if(validateStrings.validateData(arrivalAirportLocationTextFieldEdit.getText()))
        {
            arrivalAirportLocationToEdit = arrivalAirportLocationTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport Location");
            return false;
        }

        arrivalAirportToEdit = new Airport(arrivalAirportCodeToEdit,arrivalAirportNameToEdit,arrivalAirportLocationToEdit);
        flightToEdit.setArrivalAirport(arrivalAirportToEdit);

        try {
            LocalDate selectedDate = departureDateDatePicker.getValue();
            String day = String.valueOf(selectedDate.getDayOfMonth());
            String month = String.valueOf(selectedDate.getMonthValue());
            String year = String.valueOf(selectedDate.getYear());
            departureDateToEdit = new NewDate(day,month,year);
            flightToEdit.setDepartureDate(departureDateToEdit);
        }
        catch (NullPointerException e) {
            warningLabel.setText("Please Enter a Correct Date");
            return false;
        }

        try {
            LocalDate selectedDate = arrivalDateDatePicker.getValue();
            String day = String.valueOf(selectedDate.getDayOfMonth());
            String month = String.valueOf(selectedDate.getMonthValue());
            String year = String.valueOf(selectedDate.getYear());
            arrivalDateToEdit = new NewDate(day,month,year);
            flightToEdit.setArrivalDate(arrivalDateToEdit);
        }
        catch (NullPointerException e) {
            warningLabel.setText("Please Enter a Correct Date");
            return false;
        }

        if(validateTime.validateData(departureTimeTextFieldEdit.getText()))
        {

            Matcher matcher = timePattern.matcher(departureTimeTextFieldEdit.getText());
            matcher.find();
            String hours = matcher.group(1);
            String minutes = matcher.group(2);
            String period = matcher.group(3);
            departureTimeToEdit = new Time(hours,minutes,period);
            flightToEdit.setDepartureTime(departureTimeToEdit);
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Time (HH:MMAM/PM)");
            return false;
        }

        if(validateTime.validateData(arrivalTimeTextFieldEdit.getText()))
        {
            Matcher matcher = timePattern.matcher(arrivalTimeTextFieldEdit.getText());
            matcher.find();
            String hours = matcher.group(1);
            String minutes = matcher.group(2);
            String period = matcher.group(3);
            arrivalTimeToEdit = new Time(hours,minutes,period);
            flightToEdit.setArrivalTime(arrivalTimeToEdit);
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Time (HH:MMAM/PM)");
            return false;
        }

        if(validateNumber.validateData(economySeatPriceTextFieldEdit.getText()))
        {
            economyPriceToEdit = Integer.parseInt(economySeatPriceTextFieldEdit.getText());
            flightToEdit.setEconomyPrice(economyPriceToEdit);
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Price");
            return false;
        }

        if(validateNumber.validateData(businessSeatPriceTextFieldEdit.getText()))
        {
            bussinessPriceToEdit = Integer.parseInt(businessSeatPriceTextFieldEdit.getText());
            flightToEdit.setBusinessPrice(bussinessPriceToEdit);
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Price");
            return false;
        }

        if(validateNumber.validateData(firstClassSeatPriceTextFieldEdit.getText()))
        {
            firstClassPriceToEdit = Integer.parseInt(firstClassSeatPriceTextFieldEdit.getText());
            flightToEdit.setFirstClassPrice(firstClassPriceToEdit);
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Price");
            return false;
        }
        return true;
    }
    public void setValues()
    {
        airlineTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getAirLineName());

        departureAirportCodeTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getDepartureAirport().getCode());
        departureAirportNameTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getDepartureAirport().getName());
        departureAirportLocationTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getDepartureAirport().getLocation());

        arrivalAirportCodeTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getArrivalAirport().getCode());
        arrivalAirportNameTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getArrivalAirport().getName());
        arrivalAirportLocationTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getArrivalAirport().getLocation());

        LocalDate departureDate = LocalDate.of(Integer.parseInt(Flight.flights.get(Flight.selectedFlightIndex).getDepartureDate().getYear()), Integer.parseInt(Flight.flights.get(Flight.selectedFlightIndex).getDepartureDate().getMonth()), Integer.parseInt(Flight.flights.get(Flight.selectedFlightIndex).getDepartureDate().getDay()));
        departureDateDatePicker.setValue(departureDate);
        LocalDate arrivalDate = LocalDate.of(Integer.parseInt(Flight.flights.get(Flight.selectedFlightIndex).getArrivalDate().getYear()), Integer.parseInt(Flight.flights.get(Flight.selectedFlightIndex).getArrivalDate().getMonth()), Integer.parseInt(Flight.flights.get(Flight.selectedFlightIndex).getArrivalDate().getDay()));
        arrivalDateDatePicker.setValue(arrivalDate);

        departureTimeTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getDepartureTime().getHour() + ":" + Flight.flights.get(Flight.selectedFlightIndex).getDepartureTime().getMinutes() + Flight.flights.get(Flight.selectedFlightIndex).getDepartureTime().getPeriod());
        arrivalTimeTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getArrivalTime().getHour() + ":" + Flight.flights.get(Flight.selectedFlightIndex).getArrivalTime().getMinutes() + Flight.flights.get(Flight.selectedFlightIndex).getArrivalTime().getPeriod());

        economySeatPriceTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getEconomyPrice()+"");
        businessSeatPriceTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getBusinessPrice()+"");
        firstClassSeatPriceTextFieldEdit.setText(Flight.flights.get(Flight.selectedFlightIndex).getFirstClassPrice()+"");

    }

    public void onBackButtonClicked(MouseEvent event){
        SceneSwitcher.switchScene(event, "AdminPage", null);
    }
}