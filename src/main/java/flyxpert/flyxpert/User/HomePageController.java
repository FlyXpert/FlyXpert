package flyxpert.flyxpert.User;

import flyxpert.flyxpert.Main;
import flyxpert.flyxpert.Miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class HomePageController {
    @FXML
    private TextField signUpUsernameTextField;
    @FXML
    private TextField signUpEmailTextField;
    @FXML
    private TextField signUpPasswordTextField;
    @FXML
    TextField signInUsernameTextField;
    @FXML
    TextField signInPasswordTextField;
    @FXML
    private Button homePageSignUpButton;
    @FXML
    private Button homePageSignInButton;
    @FXML
    private Button internalSignUpButton;
    @FXML
    private Button internalSignInButton;
    @FXML
    private Label signUpLabel;
    @FXML
    private Label signUpUsernameValidator;
    @FXML
    private Label emailVaidatorLabel;
    @FXML
    private Label passwordValidatorLabel;
    @FXML
    private Label signInPasswordValidator;
    @FXML
    private Label signInUsernameValidator;
    @FXML
    private Label signUpSuccessLabel;
    private Stage signInStage;
    private Stage signUpStage;
    private static Stage mainStage;
    private static FXMLLoader fxmlLoader;
    private final Color ERROR_COLOR = Color.RED;

    public static void homePageScene(Stage stage) throws IOException
    {
        fxmlLoader = new FXMLLoader(Main.class.getResource("/flyxpert/flyxpert/HomePage/HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FlyXpert!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        mainStage = stage;
    }
    public void homePageOnSignUp(ActionEvent event) throws IOException {
        signUpStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageController.class.getResource("/flyxpert/flyxpert/HomePage/HomePageSignUp.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        signUpStage.setTitle("Sign-Up Window");
        signUpStage.initModality(Modality.APPLICATION_MODAL); //Application modal makes parent stage non-interactable unless the new stage is closed
        // signUpStage.initStyle(StageStyle.UNDECORATED);
        signUpStage.setOnShown(e -> signUpStage.getScene().getRoot().requestFocus()); //Removes cursor from any text field
        signUpStage.setResizable(false);
        signUpStage.setScene(scene);
        signUpStage.show();
        //System.out.println("Signed up");
    }
    public void homePageOnSignIn(ActionEvent event) throws IOException {
        signInStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageController.class.getResource("/flyxpert/flyxpert/HomePage/HomePageSignIn.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        signInStage.setTitle("Sign-In Window");
        signInStage.initModality(Modality.APPLICATION_MODAL);
        // signInStage.initStyle(StageStyle.UNDECORATED);
        signInStage.setOnShown(e -> signInStage.getScene().getRoot().requestFocus());
        signInStage.setResizable(false);
        signInStage.setScene(scene);
        signInStage.show();

        //System.out.println("Signed In");
    }
    public void internalOnSignInButton(ActionEvent e) throws IOException, ParseException {
        String userName = signInUsernameTextField.getText();
        String password = signInPasswordTextField.getText();
        User user = new User(userName, null, password);

        if(userName.equals("admin") && password.equals("admin")){
            signInPasswordValidator.setText("");
            signInUsernameValidator.setText("");
            ((Stage) internalSignInButton.getScene().getWindow()).close();
            SceneSwitcher.switchScene(e, "AdminPage" , mainStage);
        }
        else if(User.searchForUser(user) == null && User.exists(userName)) {
            signInPasswordValidator.setText("Incorrect Password!");
            signInPasswordValidator.setTextFill(ERROR_COLOR);
            signInPasswordTextField.setText("");
        }
        else if(User.searchForUser(user) != null) {
            signInPasswordValidator.setText("");
            signInUsernameValidator.setText("");
            User.currentUser = user;
            if(user.getUserName().equals("admin"))
                User.currentUser.setIsAdmin(true);
            ((Stage) internalSignInButton.getScene().getWindow()).close();
            SceneSwitcher.switchScene(e, "SearchFlightPage" , mainStage);
            //System.out.println("You've successfully logged in");
        }
        else {
            signInUsernameValidator.setText("Incorrect username/email");
            signInUsernameValidator.setTextFill(ERROR_COLOR);
            //System.out.println("User not found");
        }

        //System.out.println(signInUsernameTextField.getText());
        //System.out.println(signInPasswordTextField.getText());
        //System.out.println("Working");
    }
    public void internalOnSignUpButton(ActionEvent e) {
        String userName = signUpUsernameTextField.getText();
        String email = signUpEmailTextField.getText();
        String password = signUpPasswordTextField.getText();
        //Some validations to be done
        if(email == null || !email.matches("\\w+@\\w+\\.com")) {
            emailVaidatorLabel.setText("Incorrect email, follow this format [John-Doe@gmail.com]");
            emailVaidatorLabel.setTextFill(ERROR_COLOR);
        }
        else if(User.exists(email)) {
            emailVaidatorLabel.setText("Email already exist, choose another");
            emailVaidatorLabel.setTextFill(ERROR_COLOR);
        }
        else
            emailVaidatorLabel.setText("");
        if(password.length() < 12) {
            passwordValidatorLabel.setTextFill(ERROR_COLOR);
            passwordValidatorLabel.setText("Password length MUST be at least 12 characters long");
        }
        else
            passwordValidatorLabel.setText("");

        if(userName == null || !userName.matches("[a-zA-Z ,.-]+")) {
            //System.out.println("IT's empty");
            signUpUsernameValidator.setTextFill(ERROR_COLOR);
            signUpUsernameValidator.setText("Incorrect username");
        }

        else if(User.exists(userName)) {
            signUpUsernameValidator.setTextFill(ERROR_COLOR);
            signUpUsernameValidator.setText("Username already exist, choose another");
        }
        else
            signUpUsernameValidator.setText("");

        if(!User.exists(email) && !User.exists(userName) && password.length() >= 12
                && email != null && email.matches("\\w+@\\w+\\.com") && userName != null && !userName.isEmpty()) {
            //System.out.println(signUpEmailTextField.getText());
            //System.out.println(signUpUsernameTextField.getText());
            //System.out.println(signUpPasswordTextField.getText());
            signUpLabel.setText("You have successfully signed up!");

            ((Stage) internalSignUpButton.getScene().getWindow()).close();
            User user = new User(userName, email, password);
            User.userList.add(user);

            try {
                HomePageController homePageController = fxmlLoader.getController();
                homePageController.signUpSuccessLabel.setText("You've successfully signed up!");
                homePageController.signUpSuccessLabel.setTextFill(Color.GREEN);
            }
            catch (NullPointerException exception) {
                System.out.println("Null..");
            }
        }
    }

    public void onSignInButtonHoverEnter(MouseEvent e) {
        //System.out.println("HOVER ON");
        homePageSignInButton.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: #605DEC; -fx-border-radius: 4px;");
    }
    public void onSignInButtonHoverExit(MouseEvent e) {
        //System.out.println("HOVER OFF");
        homePageSignInButton.setStyle("-fx-background-color: #FFF; -fx-border-color: #605DEC; -fx-border-radius: 4px");
    }

    public void onSignUpButtonHoverEnter(MouseEvent e) {
        homePageSignUpButton.setStyle("-fx-background-color: #4743d1");
    }

    public void onSignUpButtonHoverExit(MouseEvent e) {
        homePageSignUpButton.setTextFill(Color.WHITE);
        homePageSignUpButton.setStyle("-fx-background-color: #605DEC; -fx-border-radius: 4px");
    }

    public void internalOnSignInButtonHoverEnter(MouseEvent e) {
        internalSignInButton.setStyle("-fx-background-color: #4743d1");
    }
    public void internalOnSignInButtonHoverExit(MouseEvent e) {
        internalSignInButton.setTextFill(Color.WHITE);
        internalSignInButton.setStyle("-fx-background-color: #605DEC; -fx-border-radius: 4px");
    }
    public void internalOnSignUpButtonHoverEnter(MouseEvent e){
        internalSignUpButton.setStyle("-fx-background-color: #4743d1");
    }

    public void internalOnSignUpButtonHoverExit(MouseEvent e){
        internalSignUpButton.setTextFill(Color.WHITE);
        internalSignUpButton.setStyle("-fx-background-color: #605DEC; -fx-border-radius: 4px");
    }
}
