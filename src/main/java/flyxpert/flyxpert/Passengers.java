package flyxpert.flyxpert;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        @FXML
        Button SelectSeats;

        private String name;
        private final SimpleDateFormat DOBFormat = new SimpleDateFormat("dd/MM/yy");
        private Date DOB = new Date();
        private String phoneNumber;
        static public int passengersToBeAdded = 2;
        static private int curPassenger = 0;
        static ArrayList<Passengers> passengers = new ArrayList<>();

        private Seat seat;

        public void setSeat(Seat seat) {
                this.seat = seat;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setDOB(Date DOB) {
                this.DOB = DOB;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public String getName() {
                return name;
        }

        public Date getDOB() {
                return DOB;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public Seat getSeat() {
                return this.seat;
        }

        public void NextPassengerButtonPress(ActionEvent event) throws ParseException {
                if (passengersToBeAdded > 0 && AddPassenger()) {
                        passengersToBeAdded--;
                        numOfPassengersDisplay.setText("Passenger " + (curPassenger + 1));
                }
        }

        public Boolean AddPassenger() throws ParseException {

                passengers.set(curPassenger, new Passengers());

                if (firstNameTextField.getText() != null) {
                        passengers.get(curPassenger).name = firstNameTextField.getText();
                        passengers.get(curPassenger).name += " ";
                } else {
                        return false;
                }

                if (middleNameTextField.getText() != null) {
                        passengers.get(curPassenger).name = middleNameTextField.getText();
                        passengers.get(curPassenger).name += " ";
                } else {
                        return false;
                }

                if (lastNameTextField.getText() != null) {
                        passengers.get(curPassenger).name = lastNameTextField.getText();
                } else {
                        return false;
                }

                try {
                        passengers.get(curPassenger).DOB = DOBFormat.parse(DOBTextField.getText());
                } catch (ParseException e) {
                        return false;
                }

                if (phoneNumberTextField.getText() != null) {
                        passengers.get(curPassenger).phoneNumber = phoneNumberTextField.getText();
                } else {
                        return false;
                }
                curPassenger++;
                return true;
        }

        @FXML
        private SeatSelectionPageController SeatSelectionPageController;

        public void switchToSeatSelection(ActionEvent event) throws IOException, ParseException {

                SceneController s = new SceneController();
                s.switchToSeatSelection(event, passengers);

        }

}