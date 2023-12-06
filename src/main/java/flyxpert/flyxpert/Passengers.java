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
import java.util.Date;
import java.util.Objects;

public class Passengers {
    @FXML
    private Label numOfPassengersDisplay;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField middleNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField DOBTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private Button nextPassengerButton;

    private String name;
    private final SimpleDateFormat DOBFormat  = new SimpleDateFormat("dd/MM/yy");
    private Date DOB = new Date();
    private int phoneNumber;
    static public int passengersToBeAdded = 2;
    static private int curPassenger = 0;
    private Passengers[] passengerArr = new Passengers[1000];

    public void setName(String name) {
        this.name = name;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public Date getDOB() {
        return DOB;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void NextPassengerButtonPress(ActionEvent event) throws ParseException {
        if(passengersToBeAdded > 0 && AddPassenger())
        {
            passengersToBeAdded--;
            numOfPassengersDisplay.setText("Passenger " + (curPassenger + 1));
        }
    }
    public Boolean AddPassenger() throws ParseException {

        passengerArr[curPassenger] = new Passengers();

        if(firstNameTextField.getText() != null)
        {
            passengerArr[curPassenger].name = firstNameTextField.getText();
            passengerArr[curPassenger].name += " ";
        }
        else
        {
            return false;
        }

        if(middleNameTextField.getText() != null)
        {
            passengerArr[curPassenger].name = middleNameTextField.getText();
            passengerArr[curPassenger].name += " ";
        }
        else
        {
            return false;
        }

        if(lastNameTextField.getText() != null)
        {
            passengerArr[curPassenger].name = lastNameTextField.getText();
        }
        else
        {
            return false;
        }

        try
        {
            passengerArr[curPassenger].DOB = DOBFormat.parse(DOBTextField.getText());
        }
        catch(ParseException e)
        {
            return false;
        }

        if(phoneNumberTextField.getText() != null)
        {
            passengerArr[curPassenger].phoneNumber = Integer.parseInt(phoneNumberTextField.getText());
        }
        else
        {
            return false;
        }
        curPassenger++;
        return true;
    }

}