package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Validators.ValidatePhoneNumber;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Validators.*;
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
    static private int curPassenger = 0;
    private ValidatePhoneNumber validatePhoneNumber = new ValidatePhoneNumber();
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



    public Boolean addPassenger() throws ParseException {

        Passengers.passengers.add(new Passengers());

        if (validateName.validateData(firstNameTextField.getText())) {
            Passengers.passengers.get(curPassenger).setFirstName(firstNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        if (validateName.validateData(middleNameTextField.getText())) {
            Passengers.passengers.get(curPassenger).setMiddleName(middleNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        if (validateName.validateData(lastNameTextField.getText())) {
            Passengers.passengers.get(curPassenger).setLastName(lastNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        try {
            LocalDate localDate = dateOfBirthTextField.getValue();
            dateOfBirthAsString = localDate.format(dateOfBirthFormatter);
            Passengers.passengers.get(curPassenger).setDateOfBirth(dateOfBirthSDF.parse(dateOfBirthAsString));
        }
        catch (NullPointerException e) {
            informationWarningText.setText("Please Enter the Date");
            return false;
        }

        if (validatePhoneNumber.validateData(phoneNumberTextField.getText())) {
            Passengers.passengers.get(curPassenger).setPhoneNumber(phoneNumberTextField.getText());
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
}
