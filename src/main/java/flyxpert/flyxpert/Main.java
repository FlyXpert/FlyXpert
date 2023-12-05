package flyxpert.flyxpert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {
    public static void main(String[] args) {
        Reader reader = new Reader();
        reader.readAllFiles();
        launch(args);
        Writer writer = new Writer();
        writer.writeToAllFiles();
    }

    public void start(Stage stage) throws IOException {
        //External configuration
        FileReader config_reader = new FileReader("FlyXpert.properties");
        Properties config = new Properties();
        config.load(config_reader);

        Parent paymentPageRoot = FXMLLoader.load(getClass().getResource("PaymentPage/PaymentPage.fxml"));
        Scene paymentPagescene = new Scene(paymentPageRoot);
        paymentPagescene.getStylesheets().add(config.getProperty("paymentPageCssPath"));

        stage.setTitle(config.getProperty("systemTitle"));
        stage.setScene(paymentPagescene);
        stage.show();
    }
}