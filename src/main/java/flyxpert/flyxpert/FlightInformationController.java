package flyxpert.flyxpert;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.*;
import javafx.scene.control.skin.MenuButtonSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightInformationController{
    @FXML
    private ImageView worldImageView;
    @FXML
    private ImageView airplaneLanding;



    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/world.png");
        Image image = new Image(file.toURI().toString());
        worldImageView.setImage(image);


        File file1 = new File("src/icons8-airplane-landing-24.png");
        Image image1 = new Image(file1.toURI().toString());
        airplaneLanding.setImage(image1);

    }*/

    @FXML
    private VBox vbox;

    public void fillDataOfFlights(){

        try {
               for(Flight flight : Flight.flights) {
                   vbox.getChildren().add(createFlight(flight));
               }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private HBox createFlight(Flight flight){
        HBox hbox = new HBox();
        hbox.setStyle("-fx-border-color: black;");

        Label airLineLabel = new Label(flight.getAirlineName() + " Airlines");

        FlightInformationController.setLabelStyle(airLineLabel);
        hbox.getChildren().add(airLineLabel);

        Label timeLabel = new Label(flight.getDepartureTime().getHour() + ":" + flight.getDepartureTime().getMinutes()
                + " " + flight.getDepartureTime().getPeriod()
                + " - " + flight.getArrivalTime().getHour() + ":" + flight.getArrivalTime().getMinutes()
                + " " + flight.getArrivalTime().getPeriod());

        FlightInformationController.setLabelStyle(timeLabel);
        hbox.getChildren().add(timeLabel);

        Label dayLabel = new Label(flight.getArrivalDay().getDay()
                + "-" + flight.getArrivalDay().getMonth()
                + "-" + flight.getArrivalDay().getYear());


        FlightInformationController.setLabelStyle(dayLabel);
        hbox.getChildren().add(dayLabel);

        Label priceLabel = new Label(Integer.toString(flight.getEconomyPrice()));

        FlightInformationController.setLabelStyle(priceLabel);
        hbox.getChildren().add(priceLabel);

        hbox.setOnMouseClicked(event -> {
            try {
                DetailsConfirmationController.handleHBoxClick(flight);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return hbox;
    }

    private static Label setLabelStyle(Label label) {
        label.setStyle("-fx-padding: 20;");

        return label;
    }








    @FXML
    private MenuButton fromWhereMenuButton;
    @FXML
    private void setMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        fromWhereMenuButton.setText(menuItem.getText());
    }


    @FXML
    private TextField numOfPassengers;
    @FXML
    private Label invalidInput;
    @FXML
    private void checkInput() {
        try {
            int val = Integer.parseInt(numOfPassengers.getText());
            invalidInput.setVisible(false);
        }
        catch (NumberFormatException e) {

            // This is thrown when the String
            // contains characters other than digits
            // System.out.println("Invalid String");
            invalidInput.setVisible(true);
        }
    }
    @FXML
    private void increment() {
        if(!(numOfPassengers.getText().equals("100"))) numOfPassengers.setText(Integer.toString(Integer.parseInt(numOfPassengers.getText())+1));
    }
    @FXML
    private void decrement() {
        if(!(numOfPassengers.getText().equals("1"))) numOfPassengers.setText(Integer.toString(Integer.parseInt(numOfPassengers.getText())-1));
    }



}
