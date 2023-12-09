package flyxpert.flyxpert;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
public class PaymentPageController {

    @FXML
    Button cardPaymentButton;
    @FXML
    Button paypalPaymentButton;
    @FXML
    Button payButton;
    @FXML
    ImageView cardIcon;
    @FXML
    ImageView paypalIcon;
    @FXML
    TextField cardOwnerNameTextField;
    @FXML
    TextField cardNumberTextField;
    @FXML
    TextField cardExpirationDateTextField;
    @FXML
    TextField cardCcvTextField;
    @FXML
    TextField paypalEmailTextField;
    @FXML
    Label cardExpirationDateLabel;
    @FXML
    Label cardNameOrPaypalEmailError;
    @FXML
    Label cardNumberError;
    @FXML
    Label cardExpirationDateError;
    @FXML
    Label cardCcvError;

    private String currentPaymentMethod = "card";

    // Reading the constant image paths and the colors from an external configuration file (FlyXpert.properties) using class ReadExternalConfiguration
    final Image CARD_LOGO_WHITE = new Image(ReadExternalConfig.config.getProperty("cardIconWhitePath"));
    final Image CARD_LOGO_PURPLE = new Image(ReadExternalConfig.config.getProperty("cardIconPurplePath"));
    final Image PAYPAL_LOGO_WHITE = new Image(ReadExternalConfig.config.getProperty("paypalIconWhitePath"));
    final Image PAYPAL_LOGO_PURPLE = new Image(ReadExternalConfig.config.getProperty("paypalIconPurplePath"));
    final String MAIN_BLUE_COLOR = ReadExternalConfig.config.getProperty("mainBlueColor");
    final String MAIN_WHITE_COLOR = ReadExternalConfig.config.getProperty("mainWhiteColor");
    final String HOVER_BLUE_COLOR = ReadExternalConfig.config.getProperty("hoverBlueColor");
    final String HOVER_GREY_COLOR = ReadExternalConfig.config.getProperty("hoverGreyColor");

    public void onCardButtonClick(MouseEvent e){
        currentPaymentMethod = "card";
        cardPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_BLUE_COLOR, MAIN_WHITE_COLOR));
        paypalPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_WHITE_COLOR, MAIN_BLUE_COLOR));
        cardIcon.setImage(CARD_LOGO_WHITE);
        cardIcon.setFitWidth(20);
        cardIcon.setFitHeight(15);
        paypalIcon.setImage(PAYPAL_LOGO_PURPLE);
        paypalIcon.setFitWidth(19);
        paypalIcon.setFitHeight(20);
        cardOwnerNameTextField.setVisible(true);
        cardNumberTextField.setVisible(true);
        cardExpirationDateTextField.setVisible(true);
        cardExpirationDateLabel.setVisible(true);
        cardCcvTextField.setVisible(true);
        paypalEmailTextField.setVisible(false);
        cardCcvError.setVisible(false);
        cardNumberError.setVisible(false);
        cardExpirationDateError.setVisible(false);
        cardNameOrPaypalEmailError.setVisible(false);
    }

    public void onPaypalButtonClick(MouseEvent e){
        currentPaymentMethod = "paypal";
        paypalPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_BLUE_COLOR, MAIN_WHITE_COLOR));
        cardPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_WHITE_COLOR, MAIN_BLUE_COLOR));
        cardIcon.setImage(CARD_LOGO_PURPLE);
        cardIcon.setFitWidth(26);
        cardIcon.setFitHeight(22);
        paypalIcon.setImage(PAYPAL_LOGO_WHITE);
        paypalIcon.setFitWidth(24);
        paypalIcon.setFitHeight(22);
        cardOwnerNameTextField.setVisible(false);
        cardNumberTextField.setVisible(false);
        cardExpirationDateTextField.setVisible(false);
        cardExpirationDateLabel.setVisible(false);
        cardCcvTextField.setVisible(false);
        cardCcvError.setVisible(false);
        cardNumberError.setVisible(false);
        cardExpirationDateError.setVisible(false);
        cardNameOrPaypalEmailError.setVisible(false);
        paypalEmailTextField.setVisible(true);
    }

    // Regex patterns for validation on user inputs
    final String CARD_NAME_PATTERN = "[A-Z a-z]+";
    final String CARD_NUMBER_PATTERN = "\\d{16}";
    final String CARD_EXPIRATION_DATE_PATTERN = "\\d{2}/\\d{2}";
    final String CARD_CCV_PATTERN = "\\d{3}";
    final String EMAIL_PATTERN = "\\w+@\\w+\\.com";

    public void onPayButtonClick(MouseEvent e){
        if(currentPaymentMethod.equals("card")){
            if(!cardOwnerNameTextField.getText().matches(CARD_NAME_PATTERN)){
                cardNameOrPaypalEmailError.setText("Please don't leave this field empty or enter a valid name with no special characters (Ex: Fly Xpert)");
                cardNameOrPaypalEmailError.setVisible(true);
            }
            else{
                cardNameOrPaypalEmailError.setVisible(false);
            }

            if(!cardNumberTextField.getText().matches(CARD_NUMBER_PATTERN)){
                cardNumberError.setVisible(true);
            }
            else{
                cardNumberError.setVisible(false);
            }

            if(!cardExpirationDateTextField.getText().matches(CARD_EXPIRATION_DATE_PATTERN)){
                cardExpirationDateError.setVisible(true);
            }
            else{
                cardExpirationDateError.setVisible(false);
            }

            if(!cardCcvTextField.getText().matches(CARD_CCV_PATTERN)){
                cardCcvError.setVisible(true);
            }
            else{
                cardCcvError.setVisible(false);
            }
        }
        else{
            if(!paypalEmailTextField.getText().matches(EMAIL_PATTERN)){
                cardNameOrPaypalEmailError.setText("Please don't leave this field empty or enter a valid email (Ex: FlyXpert@gmail.com)");
                cardNameOrPaypalEmailError.setVisible(true);
            }
            else{
                cardNameOrPaypalEmailError.setVisible(false);
            }
        }
    }

    public void onPayButtonMouseEntered(MouseEvent e) {
        payButton.setStyle(String.format("-fx-background-color: %s", HOVER_BLUE_COLOR));
    }

    public void onPayButtonMouseExited(MouseEvent e) {
        payButton.setStyle(String.format("-fx-background-color: %s", MAIN_BLUE_COLOR));
    }

    public void onCardButtonMouseEntered(MouseEvent e) {
        if(currentPaymentMethod.equals("card")){
            cardPaymentButton.setStyle(String.format("-fx-background-color: %s", HOVER_BLUE_COLOR));
        }
        else{
            cardPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", HOVER_GREY_COLOR, MAIN_BLUE_COLOR));
        }
    }

    public void onCardButtonMouseExited(MouseEvent e) {
        if(currentPaymentMethod.equals("card")){
            cardPaymentButton.setStyle(String.format("-fx-background-color: %s", HOVER_BLUE_COLOR));
        }
        else{
            cardPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_WHITE_COLOR, MAIN_BLUE_COLOR));
        }
    }

    public void onPaypalButtonMouseEntered(MouseEvent e) {
        if(currentPaymentMethod.equals("paypal")){
            paypalPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", HOVER_BLUE_COLOR, MAIN_WHITE_COLOR));
        }
        else{
            paypalPaymentButton.setStyle(String.format("-fx-background-color: %s", HOVER_GREY_COLOR));
        }
    }

    public void onPaypalButtonMouseExited(MouseEvent e) {
        if(currentPaymentMethod.equals("paypal")){
            paypalPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_BLUE_COLOR, MAIN_WHITE_COLOR));
        }
        else{
            paypalPaymentButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", MAIN_WHITE_COLOR, MAIN_BLUE_COLOR));
        }
    }
}
