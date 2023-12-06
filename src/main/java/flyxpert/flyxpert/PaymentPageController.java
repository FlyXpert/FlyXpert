package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PaymentPageController {

    @FXML
    Button cardPaymentButton, paypalPaymentButton;
    @FXML
    ImageView cardIcon, paypalIcon;
    @FXML
    TextField cardOwnerNameTextField, cardNumberTextField, expirationDateTextField, ccvTextField, emailTextField;
    @FXML
    Label expirationDateLabel;

    public void onCardButtonClick(ActionEvent e){
        cardPaymentButton.setStyle("-fx-background-color: #605dff; -fx-text-fill: white;");
        paypalPaymentButton.setStyle("-fx-background-color: white; -fx-text-fill: #605dff;");
        Image cardWhite = new Image("CardWhite.png");
        cardIcon.setImage(cardWhite);
        cardIcon.setFitWidth(20);
        cardIcon.setFitHeight(15);
        Image paypalPurple = new Image("PaypalPurple.png");
        paypalIcon.setImage(paypalPurple);
        paypalIcon.setFitWidth(19);
        paypalIcon.setFitHeight(20);
        cardOwnerNameTextField.setVisible(true);
        cardNumberTextField.setVisible(true);
        expirationDateTextField.setVisible(true);
        expirationDateLabel.setVisible(true);
        ccvTextField.setVisible(true);
        emailTextField.setVisible(false);
    }

    public void onPaypalButtonClick(ActionEvent e){
        paypalPaymentButton.setStyle("-fx-background-color: #605dff; -fx-text-fill: white;");
        cardPaymentButton.setStyle("-fx-background-color: white; -fx-text-fill: #605dff;");
        Image cardPurple = new Image("CardPurple.png");
        cardIcon.setImage(cardPurple);
        cardIcon.setFitWidth(26);
        cardIcon.setFitHeight(22);
        Image paypalWhite = new Image("PaypalWhite.png");
        paypalIcon.setImage(paypalWhite);
        paypalIcon.setFitWidth(24);
        paypalIcon.setFitHeight(22);
        cardOwnerNameTextField.setVisible(false);
        cardNumberTextField.setVisible(false);
        expirationDateTextField.setVisible(false);
        expirationDateLabel.setVisible(false);
        ccvTextField.setVisible(false);
        emailTextField.setVisible(true);
    }
}
