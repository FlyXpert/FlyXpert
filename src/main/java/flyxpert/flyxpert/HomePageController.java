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
    private Label signUpLabel, emailVaidatorLabel, passwordValidatorLabel;

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
        User user = new User(userName, password, null);
        if(User.isFound(user) != null)
            System.out.println("Successfully signed in!");
        else
            System.out.println("User not found");

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
        if(password.length() < 12)
        {
            passwordValidatorLabel.setTextFill(Color.RED);
            passwordValidatorLabel.setText("Password length MUST be at least 12 characters long");
        }
        if(password.length() >= 12 && email.endsWith(".com") && email.contains("@"))
        {
            System.out.println(signUpEmailTextField.getText());
            System.out.println(signUpUsernameTextField.getText());
            System.out.println(signUpPasswordTextField.getText());
            signUpLabel.setText("You have successfully signed up!");


            ((Stage) signUpButton.getScene().getWindow()).close();
            User user = new User(userName, password, email);
            User.userList.add(user);
        }

    }

}
