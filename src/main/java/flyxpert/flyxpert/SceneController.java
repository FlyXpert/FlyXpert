package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;


public class SceneController {

        static Stage stage = new Stage();
        static Scene scene;
        static Parent root;
        public static void switchScene(ActionEvent event, String newStage, Stage other) throws IOException, ParseException {

                root = FXMLLoader.load(SeatSelectionPageController.class.getResource(newStage + ".fxml"));
                if (other != null) {
                        stage = other;
                        Stage tmp = (Stage)((Node) event.getSource()).getScene().getWindow();
                        tmp.close();
                }
                else
                        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
        }
}
