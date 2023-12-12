package flyxpert.flyxpert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Main extends Application {
    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new Reader();
        reader.readAllFiles();
        ReadExternalConfig externalConfig = new ReadExternalConfig();
        launch(args);
        Writer writer = new Writer();
        writer.writeToAllFiles();
    }
    public void start(Stage stage) throws IOException
    {
        //HomePageController.homePageScene(stage);
        Parent root;
        root = FXMLLoader.load(AdminSeatMapController.class.getResource("AdminSeatMap.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}