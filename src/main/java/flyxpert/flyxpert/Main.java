package flyxpert.flyxpert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        stage.setTitle("Hello!");
        stage.show();
    }
}