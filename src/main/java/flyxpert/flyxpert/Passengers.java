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

    public void NextPassengerButtonPress(ActionEvent event)
    {
        if(passengersToBeAdded > 0)
        {
            AddPassenger();
            passengersToBeAdded--;
            numOfPassengersDisplay.setText("Passenger " + (curPassenger + 1));
        }
    }
    public void AddPassenger()
    {
        Passengers tmpPassenger = new Passengers();
        tmpPassenger.name = firstNameTextField.getText();
        tmpPassenger.name += " ";
        tmpPassenger.name += middleNameTextField.getText();
        tmpPassenger.name += " ";
        tmpPassenger.name += lastNameTextField.getText();
        try
        {
            tmpPassenger.DOB = DOBFormat.parse(DOBTextField.getText());
        }
        catch (ParseException ignored)
        {

        }
        tmpPassenger.phoneNumber = Integer.parseInt(phoneNumberTextField.getText());
        curPassenger++;
    }

    public void start(Stage stage) throws IOException {
        stage.setTitle("Passenger Information");
        Parent root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Passengers.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}