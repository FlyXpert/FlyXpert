package flyxpert.flyxpert;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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
    public TextField DOBTextField;
    @FXML
    public TextField phoneNumberTextField;
    @FXML
    public Label informationWarningText;

    private final SimpleDateFormat DOBFormat = new SimpleDateFormat("dd/MM/yy");
    static public int passengersToBeAdded;
    static public int initialPassengersToBeAdded;
    static private int curPassenger = 0;


    public void NextPassengerButtonPress(ActionEvent event) throws ParseException {
        if (passengersToBeAdded > 0 && AddPassenger()) {
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



    public Boolean AddPassenger() throws ParseException {

        Passengers.passengers.add(new Passengers());

        if (Validators.validateName(firstNameTextField.getText())) {
            Passengers.passengers.get(curPassenger).setFirstName(firstNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        if (Validators.validateName(middleNameTextField.getText())) {
            Passengers.passengers.get(curPassenger).setMiddleName(middleNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        if (Validators.validateName(lastNameTextField.getText())) {
            Passengers.passengers.get(curPassenger).setLastName(lastNameTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter Your Name");
            return false;
        }

        DOBFormat.setLenient(false);
        try {
            DOBFormat.parse(DOBTextField.getText());
            Passengers.passengers.get(curPassenger).setDOB(DOBFormat.parse(DOBTextField.getText()));
        }
        catch (ParseException e) {
            informationWarningText.setText("Please Enter the Date in the Correct Format");
            return false;
        }

        if (Validators.validatePhoneNumber(phoneNumberTextField.getText())) {
            Passengers.passengers.get(curPassenger).setPhoneNumber(phoneNumberTextField.getText());
        }
        else {
            informationWarningText.setText("Please Enter a Correct Phone Number");
            return false;
        }
        firstNameTextField.setText("");
        middleNameTextField.setText("");
        lastNameTextField.setText("");
        DOBTextField.setText("");
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
        SceneController.switchToSeatSelection(event);
    }
}
