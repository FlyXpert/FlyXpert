package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController
{
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
}
