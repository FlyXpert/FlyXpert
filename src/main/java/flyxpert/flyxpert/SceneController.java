package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class SceneController {

        static Stage stage = new Stage();
        static Scene scene;
        static Parent root;
        public void switchToSeatSelection(ActionEvent event, ArrayList<Passengers> passengers) throws IOException {
                root = FXMLLoader.load(SceneController.class.getResource("SeatSelectionPage.fxml"));
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                SeatSelectionPageController s = new SeatSelectionPageController();
                s.setData(passengers);
        }
}
