package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController
{
    @FXML
    private TextField signUpUsernameTextField,
            signUpEmailTextField, signUpPasswordTextField, signInUsernameTextField, signInPasswordTextField;
    @FXML
    private Button signUpButton;
    @FXML
    private Label signUpLabel, signUpUsernameValidator, emailVaidatorLabel, passwordValidatorLabel, signInPasswordValidator, signInUsernameValidator;

    public void onSignUp(ActionEvent event) throws IOException
    {
        Stage signUpStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageController.class.getResource("/flyxpert/flyxpert/HomePage/HomePageSignUp.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        signUpStage.setTitle("Sign-Up Window");
        signUpStage.initModality(Modality.APPLICATION_MODAL);
        // signUpStage.initStyle(StageStyle.UNDECORATED);
        signUpStage.setOnShown(e -> signUpStage.getScene().getRoot().requestFocus());
        signUpStage.setResizable(false);
        signUpStage.setScene(scene);
        signUpStage.show();
        System.out.println("Signed up");
    }
    public void onSignIn(ActionEvent event) throws IOException
    {
        Stage signInStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageController.class.getResource("/flyxpert/flyxpert/HomePage/HomePageSignIn.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        signInStage.setTitle("Sign-In Window");
        signInStage.initModality(Modality.APPLICATION_MODAL);
        // signInStage.initStyle(StageStyle.UNDECORATED);
        signInStage.setOnShown(e -> signInStage.getScene().getRoot().requestFocus());
        signInStage.setResizable(false);
        signInStage.setScene(scene);
        signInStage.show();

        System.out.println("Signed In");
    }
    public void onSignInButton(ActionEvent e)
    {
        String userName = signInUsernameTextField.getText();
        String password = signInPasswordTextField.getText();
        User user = new User(userName, null, password);
        if(User.isFound(user) == null && User.exists(userName))
        {
            signInPasswordValidator.setText("Incorrect Password!");
            signInPasswordValidator.setTextFill(Color.RED);
            signInPasswordTextField.setText("");
        }
        else if(User.isFound(user) != null)
        {
            signInPasswordValidator.setText("");
            signInUsernameValidator.setText("");
            System.out.println("You've successfully logged in");
        }
        else
        {
            signInUsernameValidator.setText("Incorrect username/email");
            signInUsernameValidator.setTextFill(Color.RED);
            System.out.println("User not found");
        }

        System.out.println(signInUsernameTextField.getText());
        System.out.println(signInPasswordTextField.getText());
        System.out.println("Working");
    }
    public void onSignUpButton (ActionEvent e)
    {
        String userName = signUpUsernameTextField.getText();
        String email = signUpEmailTextField.getText();
        String password = signUpPasswordTextField.getText();
        //Some validations to be done
        if(!(email.endsWith(".com") && email.contains("@")))
        {
            emailVaidatorLabel.setText("Incorrect email, follow this format [John-Doe@gmail.com]");
            emailVaidatorLabel.setTextFill(Color.RED);
        }
        else if(User.exists(email)) {
            emailVaidatorLabel.setText("Email already exist, choose another");
            emailVaidatorLabel.setTextFill(Color.RED);
        }
        else
            emailVaidatorLabel.setText("");
        if(password.length() < 12)
        {
            passwordValidatorLabel.setTextFill(Color.RED);
            passwordValidatorLabel.setText("Password length MUST be at least 12 characters long");
        }
        else
            passwordValidatorLabel.setText("");

        if(userName.isEmpty())
        {
            System.out.println("IT's empty");
            signUpUsernameValidator.setTextFill(Color.RED);
            signUpUsernameValidator.setText("Username cannot be empty");
        }

        else if(User.exists(userName))
        {
            signUpUsernameValidator.setTextFill(Color.RED);
            signUpUsernameValidator.setText("Username already exist, choose another");
        }
        else
            signUpUsernameValidator.setText("");

        if(!User.exists(email) && !User.exists(userName) && password.length() >= 12
                && email.endsWith(".com") && email.contains("@") && !userName.isEmpty())
        {
            System.out.println(signUpEmailTextField.getText());
            System.out.println(signUpUsernameTextField.getText());
            System.out.println(signUpPasswordTextField.getText());
            signUpLabel.setText("You have successfully signed up!");


            ((Stage) signUpButton.getScene().getWindow()).close();
            User user = new User(userName, email, password);
            User.userList.add(user);
        }

    }

}
