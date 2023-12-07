package flyxpert.flyxpert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new Reader();
        reader.readAllFiles();
        launch(args);
        Writer writer = new Writer();
        writer.writeToAllFiles();
    }

    public void start(Stage stage) throws IOException {

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SearchFlightPage.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            FlightInformationController controller = fxmlLoader.getController();
            controller.fillDataOfFlights();

            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setTitle("Flight Search Page");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}