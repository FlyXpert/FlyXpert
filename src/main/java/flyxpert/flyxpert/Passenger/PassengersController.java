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
            curPassenger++;
            if (curPassenger != initialPassengersToBeAdded) {
                numOfPassengersDisplay.setText("Passenger " + (curPassenger + 1));
            }
            else
            {
                numOfPassengersDisplay.setText("Press Select Seat");
                //System.out.println(Passenger.passengers.size());
            }
        }
        else {
            if (passengersToBeAdded == 0) {
                informationWarningText.setText("Max Number of Passengers Reached");
            }
        }
    }



    public boolean addPassenger() throws ParseException {

        Passenger tmpPassenger = new Passenger();

        if (validateName.validateData(firstNameTextField.getText())) {
           tmpPassenger.setFirstName(firstNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        if (validateName.validateData(middleNameTextField.getText())) {
            tmpPassenger.setMiddleName(middleNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        if (validateName.validateData(lastNameTextField.getText())) {
            tmpPassenger.setLastName(lastNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        try {
            LocalDate localDate = dateOfBirthTextField.getValue();
            dateOfBirthAsString = localDate.format(dateOfBirthFormatter);
            tmpPassenger.setDateOfBirth(dateOfBirthSDF.parse(dateOfBirthAsString));
        }
        catch (NullPointerException | ParseException e) {
            informationWarningText.setText("Please Enter the Date");
            return false;
        }

        if (validatePhoneNumber.validateData(phoneNumberTextField.getText()) && phoneNumberTextField.getText().length() == 11) {
            tmpPassenger.setPhoneNumber(phoneNumberTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter a Correct Phone Number");
            return false;
        }

        Passenger.passengers.add(tmpPassenger);

        firstNameTextField.setText("");
        middleNameTextField.setText("");
        lastNameTextField.setText("");
        dateOfBirthTextField.setValue(null);
        phoneNumberTextField.setText("");
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