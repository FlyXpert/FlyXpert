package flyxpert.flyxpert;

import Validators.ValidateAirports;
import Validators.ValidateName;
import Validators.ValidateNumber;
import Validators.ValidateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminEditFlightController {

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

    private Flight flightToBeEdit;
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
    String timePattern = "^(1[0-2]|0?[1-9]):([0-5][0-9])\\s([AP]M)$";
    Pattern pattern = Pattern.compile(timePattern);

    public void AdminEditFlightAction(ActionEvent event) throws ParseException
    {
        if(AdminEditFlight())
        {
            //go back to the previous scene
        }
    }
    private boolean AdminEditFlight()
    {
        Flight flightToEdit;
        if(validateStrings.validateData(airlineTextFieldEdit.getText()))
        {
            airlineNameToEdit = airlineTextFieldEdit.getText();
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
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        if(validateAirports.validateData(departureAirportNameTextFieldEdit.getText()))
        {
            departureAirportNameToEdit = departureAirportNameTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        if(validateAirports.validateData(departureAirportLocationTextFieldEdit.getText()))
        {
            departureAirportLocationToEdit = departureAirportLocationTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        departureAirportToEdit = new Airport(departureAirportCodeToEdit,departureAirportNameToEdit,departureAirportLocationToEdit);

        if(validateAirports.validateData(arrivalAirportCodeTextFieldEdit.getText()))
        {
            arrivalAirportCodeToEdit = arrivalAirportCodeTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        if(validateAirports.validateData(arrivalAirportNameTextFieldEdit.getText()))
        {
            arrivalAirportNameToEdit = arrivalAirportNameTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        if(validateAirports.validateData(arrivalAirportLocationTextFieldEdit.getText()))
        {
            arrivalAirportLocationToEdit = arrivalAirportLocationTextFieldEdit.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        arrivalAirportToEdit = new Airport(arrivalAirportCodeToEdit,arrivalAirportNameToEdit,arrivalAirportLocationToEdit);

        try {
            LocalDate selectedDate = departureDateDatePicker.getValue();
            String day = String.valueOf(selectedDate.getDayOfMonth());
            String month = String.valueOf(selectedDate.getMonthValue());
            String year = String.valueOf(selectedDate.getYear());
            departureDateToEdit = new NewDate(day,month,year);
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
        }
        catch (NullPointerException e) {
            warningLabel.setText("Please Enter a Correct Date");
            return false;
        }

        if(validateTime.validateData(departureTimeTextFieldEdit.getText()))
        {
            Matcher matcher = pattern.matcher(departureTimeTextFieldEdit.getText());
            String hours = matcher.group(1);
            String minutes = matcher.group(2);
            String period = matcher.group(3);
            departureTimeToEdit = new Time(hours,minutes,period);
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Time (HH:MM AM/PM)");
            return false;
        }

        if(validateTime.validateData(arrivalTimeTextFieldEdit.getText()))
        {
            Matcher matcher = pattern.matcher(arrivalTimeTextFieldEdit.getText());
            String hours = matcher.group(1);
            String minutes = matcher.group(2);
            String period = matcher.group(3);
            arrivalTimeToEdit = new Time(hours,minutes,period);
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Time (HH:MM AM/PM)");
            return false;
        }

        if(validateNumber.validateData(economySeatPriceTextFieldEdit.getText()))
        {
            economyPriceToEdit = Integer.parseInt(economySeatPriceTextFieldEdit.getText());
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Price");
            return false;
        }

        if(validateNumber.validateData(businessSeatPriceTextFieldEdit.getText()))
        {
            bussinessPriceToEdit = Integer.parseInt(businessSeatPriceTextFieldEdit.getText());
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Price");
            return false;
        }

        if(validateNumber.validateData(firstClassSeatPriceTextFieldEdit.getText()))
        {
            firstClassPriceToEdit = Integer.parseInt(firstClassSeatPriceTextFieldEdit.getText());
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Price");
            return false;
        }
        warningLabel.setText("");

        flightToEdit = new Flight(departureAirportToEdit,arrivalAirportToEdit,airlineNameToEdit,departureTimeToEdit,arrivalTimeToEdit,departureDateToEdit,arrivalDateToEdit,economyPriceToEdit,bussinessPriceToEdit,firstClassPriceToEdit);
        /*Flight.flights.add(flightToBeAdded);
        FlightInformationController.uniqueAirlines.put(airlineNameToBeAdded,true);
        FlightInformationController.uniqueArrivalAirports.put(arrivalAirportCodeToBeAdded,true);
        FlightInformationController.uniqueDepartureAirports.put(departureAirportCodeToBeAdded,true);*/
        return true;
    }
}