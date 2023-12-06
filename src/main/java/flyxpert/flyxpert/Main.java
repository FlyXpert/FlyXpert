package flyxpert.flyxpert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        reader.readAllFiles();
        launch(args);
        Writer writer = new Writer();
        writer.writeToAllFiles();
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/flyxpert/flyxpert/HomePage/HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FlyXpert!");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
}