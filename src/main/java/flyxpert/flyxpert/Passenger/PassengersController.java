package flyxpert.flyxpert.Passenger;

import flyxpert.flyxpert.Miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import flyxpert.flyxpert.Validators.*;
import javafx.scene.input.MouseEvent;

public class PassengersController
{
    @FXML
    public Label numOfPassengersDisplay;
    @FXML
    public TextField firstNameTextField;
    @FXML
    public TextField middleNameTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public DatePicker dateOfBirthTextField = new DatePicker();
    @FXML
    public TextField phoneNumberTextField;
    @FXML
    public Label informationWarningText;

    private final DateTimeFormatter dateOfBirthFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final SimpleDateFormat dateOfBirthSDF = new SimpleDateFormat("dd/MM/yy");
    public String dateOfBirthAsString;
    static public int passengersToBeAdded;
    static public int initialPassengersToBeAdded;
    static public int curPassenger = 0;
    private ValidateNumber validatePhoneNumber = new ValidateNumber();
    private ValidateName validateName = new ValidateName();


    public void nextPassengerButtonPress(ActionEvent event) throws ParseException {
        if (passengersToBeAdded > 0 && addPassenger()) {
            passengersToBeAdded--;
            if (curPassenger != initialPassengersToBeAdded) {
                numOfPassengersDisplay.setText("Passenger " + (curPassenger + 1));
            }
            else
            {
                numOfPassengersDisplay.setText("Press Select Seat");
            }
        }
        else {
            if (passengersToBeAdded == 0) {
                informationWarningText.setText("Max Number of Passengers Reached");
            }
        }
    }



    public boolean addPassenger() throws ParseException {

        Passenger.passengers.add(new Passenger());

        if (validateName.validateData(firstNameTextField.getText())) {
            Passenger.passengers.get(curPassenger).setFirstName(firstNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        if (validateName.validateData(middleNameTextField.getText())) {
            Passenger.passengers.get(curPassenger).setMiddleName(middleNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        if (validateName.validateData(lastNameTextField.getText())) {
            Passenger.passengers.get(curPassenger).setLastName(lastNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        try {
            LocalDate localDate = dateOfBirthTextField.getValue();
            dateOfBirthAsString = localDate.format(dateOfBirthFormatter);
            Passenger.passengers.get(curPassenger).setDateOfBirth(dateOfBirthSDF.parse(dateOfBirthAsString));
        }
        catch (NullPointerException e) {
            informationWarningText.setText("Please Enter the Date");
            return false;
        }

        if (validatePhoneNumber.validateData(phoneNumberTextField.getText()) && phoneNumberTextField.getText().length() == 11) {
            Passenger.passengers.get(curPassenger).setPhoneNumber(phoneNumberTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter a Correct Phone Number");
            return false;
        }

        firstNameTextField.setText("");
        middleNameTextField.setText("");
        lastNameTextField.setText("");
        dateOfBirthTextField.setValue(null);
        phoneNumberTextField.setText("");
        curPassenger++;
        informationWarningText.setText("");
        return true;
    }

    public void switchToSeatSelection(ActionEvent event) throws IOException, ParseException {

        if (passengersToBeAdded > 0) {
            informationWarningText.setText("Missing passengers information");
            return;
        }
        SceneSwitcher.switchScene(event, "SeatSelectionPage", null);
    }

    public void onFlightsButtonClicked(MouseEvent event){
        // Reset passengers data
        Passenger.passengers.clear();
        curPassenger = 0;

        SceneSwitcher.switchScene(event, "SearchFlightPage", null);
    }
}