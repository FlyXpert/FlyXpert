package flyxpert.flyxpert;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightInformationController implements Initializable {
    @FXML
    private ImageView worldImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/world.png");
        Image image = new Image(file.toURI().toString());
        worldImageView.setImage(image);
    }
}
