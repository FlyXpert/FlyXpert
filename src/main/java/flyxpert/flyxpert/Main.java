package flyxpert.flyxpert;

import flyxpert.flyxpert.File.ReadExternalConfig;
import flyxpert.flyxpert.File.Reader;
import flyxpert.flyxpert.File.Writer;
import flyxpert.flyxpert.User.HomePageController;
import javafx.application.Application;
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
    public void start(Stage stage) throws IOException {
        HomePageController.homePageScene(stage);
    }
}