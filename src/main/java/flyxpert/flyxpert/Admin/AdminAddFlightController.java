package flyxpert.flyxpert.Admin;

import flyxpert.flyxpert.Miscellaneous.NewDate;
import flyxpert.flyxpert.Miscellaneous.SceneSwitcher;
import flyxpert.flyxpert.Miscellaneous.Time;
import flyxpert.flyxpert.Flight.Flight;
import flyxpert.flyxpert.Flight.FlightInformationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import flyxpert.flyxpert.Validators.*;
import javafx.scene.input.MouseEvent;
import flyxpert.flyxpert.Flight.*;
public class AdminAddFlightController {

    @FXML
    public TextField airlineTextFieldAdd;
    @FXML
    public TextField departureAirportCodeTextFieldAdd;
    @FXML
    public TextField departureAirportNameTextFieldAdd;
    @FXML
    public TextField departureAirportLocationTextFieldAdd;
    @FXML
    public TextField arrivalAirportCodeTextFieldAdd;
    @FXML
    public TextField arrivalAirportNameTextFieldAdd;
    @FXML
    public TextField arrivalAirportLocationTextFieldAdd;
    @FXML
    public TextField departureTimeTextFieldAdd;
    @FXML
    public TextField arrivalTimeTextFieldAdd;
    @FXML
    public TextField economySeatPriceTextFieldAdd;
    @FXML
    public TextField businessSeatPriceTextFieldAdd;
    @FXML
    public TextField firstClassSeatPriceTextFieldAdd;
    @FXML
    public DatePicker departureDateDatePicker;
    @FXML
    public DatePicker arrivalDateDatePicker;
    @FXML
    public Label warningLabel;
    @FXML
    public Label successfulLabel;
    @FXML
    public Button submitButton;



    private Flight flightToBeAdded;
    private Airport departureAirportToBeAdded;
    private Airport arrivalAirportToBeAdded;
    private String departureAirportCodeToBeAdded;
    private String arrivalAirportCodeToBeAdded;
    private String departureAirportNameToBeAdded;
    private String arrivalAirportNameToBeAdded;
    private String departureAirportLocationToBeAdded;
    private String arrivalAirportLocationToBeAdded;
    private String airlineNameToBeAdded;
    private Time departureTimeToBeAdded;
    private Time arrivalTimeToBeAdded;
    private NewDate arrivalDateToBeAdded;
    private NewDate departureDateToBeAdded;
    private int economyPriceToBeAdded;
    private int bussinessPriceToBeAdded;
    private int firstClassPriceToBeAdded;
    private ValidateNumber validateNumber = new ValidateNumber();
    private ValidateName validateStrings = new ValidateName();
    private ValidateAirports validateAirports = new ValidateAirports();
    private ValidateTime validateTime = new ValidateTime();
    String pattern = "^(1[0-2]|0?[1-9]):([0-5][0-9])(AM|PM)$";
    Pattern timePattern = Pattern.compile(pattern);
    private boolean[][] tmpSeatsAvailability = new boolean[24][4];

    public void AdminAddFlightAction(ActionEvent event) throws ParseException
    {
        if(AdminAddFlight())
        {
            successfulLabel.setText("Added Flight Successfully");
            //go back to the previous scene
        }
    }
    private boolean AdminAddFlight()
    {
        if(validateStrings.validateData(airlineTextFieldAdd.getText()))
        {
            airlineNameToBeAdded = airlineTextFieldAdd.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airline");
            return false;
        }

        if(validateAirports.validateData(departureAirportCodeTextFieldAdd.getText()))
        {
            departureAirportCodeToBeAdded = departureAirportCodeTextFieldAdd.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        if(validateStrings.validateData(departureAirportNameTextFieldAdd.getText()))
        {
            departureAirportNameToBeAdded = departureAirportNameTextFieldAdd.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        if(validateStrings.validateData(departureAirportLocationTextFieldAdd.getText()))
        {
            departureAirportLocationToBeAdded = departureAirportLocationTextFieldAdd.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        departureAirportToBeAdded = new Airport(departureAirportCodeToBeAdded,departureAirportNameToBeAdded,departureAirportLocationToBeAdded);

        if(validateAirports.validateData(arrivalAirportCodeTextFieldAdd.getText()))
        {
            arrivalAirportCodeToBeAdded = arrivalAirportCodeTextFieldAdd.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        if(validateStrings.validateData(arrivalAirportNameTextFieldAdd.getText()))
        {
            arrivalAirportNameToBeAdded = arrivalAirportNameTextFieldAdd.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        if(validateStrings.validateData(arrivalAirportLocationTextFieldAdd.getText()))
        {
            arrivalAirportLocationToBeAdded = arrivalAirportLocationTextFieldAdd.getText();
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Airport");
            return false;
        }

        arrivalAirportToBeAdded = new Airport(arrivalAirportCodeToBeAdded,arrivalAirportNameToBeAdded,arrivalAirportLocationToBeAdded);

        try {
            LocalDate selectedDate = departureDateDatePicker.getValue();
            String day = String.valueOf(selectedDate.getDayOfMonth());
            String month = String.valueOf(selectedDate.getMonthValue());
            String year = String.valueOf(selectedDate.getYear());
            departureDateToBeAdded = new NewDate(day,month,year);
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
            arrivalDateToBeAdded = new NewDate(day,month,year);
        }
        catch (NullPointerException e) {
            warningLabel.setText("Please Enter a Correct Date");
            return false;
        }

        if(validateTime.validateData(departureTimeTextFieldAdd.getText()))
        {

            Matcher matcher = timePattern.matcher(departureTimeTextFieldAdd.getText());
            matcher.find();
            String hours = matcher.group(1);
            String minutes = matcher.group(2);
            String period = matcher.group(3);
            departureTimeToBeAdded = new Time(hours,minutes,period);
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Time (HH:MMAM/PM)");
            return false;
        }

        if(validateTime.validateData(arrivalTimeTextFieldAdd.getText()))
        {
            Matcher matcher = timePattern.matcher(arrivalTimeTextFieldAdd.getText());
            matcher.find();
            String hours = matcher.group(1);
            String minutes = matcher.group(2);
            String period = matcher.group(3);
            arrivalTimeToBeAdded = new Time(hours,minutes,period);
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Time (HH:MMAM/PM)");
            return false;
        }

        if(validateNumber.validateData(economySeatPriceTextFieldAdd.getText()))
        {
            economyPriceToBeAdded = Integer.parseInt(economySeatPriceTextFieldAdd.getText());
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Price");
            return false;
        }

        if(validateNumber.validateData(businessSeatPriceTextFieldAdd.getText()))
        {
            bussinessPriceToBeAdded = Integer.parseInt(businessSeatPriceTextFieldAdd.getText());
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Price");
            return false;
        }

        if(validateNumber.validateData(firstClassSeatPriceTextFieldAdd.getText()))
        {
            firstClassPriceToBeAdded = Integer.parseInt(firstClassSeatPriceTextFieldAdd.getText());
        }
        else
        {
            warningLabel.setText("Please Enter a Correct Price");
            return false;
        }
        warningLabel.setText("");

        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 4; j++) {
                tmpSeatsAvailability[i][j] = true;
            }
        }

        flightToBeAdded = new Flight(departureAirportToBeAdded,arrivalAirportToBeAdded,airlineNameToBeAdded,departureTimeToBeAdded,arrivalTimeToBeAdded,departureDateToBeAdded,arrivalDateToBeAdded,economyPriceToBeAdded,bussinessPriceToBeAdded,firstClassPriceToBeAdded, 96,Flight.flightsCount + 1,tmpSeatsAvailability);
        Flight.flights.add(flightToBeAdded);
        FlightInformationController.airlines.put(airlineNameToBeAdded,true);
        FlightInformationController.arrivalAirports.put(arrivalAirportCodeToBeAdded,true);
        FlightInformationController.departureAirports.put(departureAirportCodeToBeAdded,true);
        return true;
    }


    public void onBackButtonClicked(MouseEvent event){
        SceneSwitcher.switchScene(event, "AdminPage", null);
    }
}