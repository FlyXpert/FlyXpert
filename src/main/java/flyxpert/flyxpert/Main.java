package flyxpert.flyxpert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        Reader reader = new Reader();
        reader.readAllFiles();
        ReadExternalConfig externalConfig = new ReadExternalConfig();
        launch(args);
        Writer writer = new Writer();
        writer.writeToAllFiles();
    }

    public void start(Stage stage) throws IOException {
        Parent paymentPageRoot = FXMLLoader.load(getClass().getResource("PaymentPage/PaymentPage.fxml"));
        Scene paymentPagescene = new Scene(paymentPageRoot);
        paymentPagescene.getStylesheets().add(ReadExternalConfig.config.getProperty("paymentPageCssPath"));

        stage.setTitle(ReadExternalConfig.config.getProperty("systemTitle"));
        stage.setScene(paymentPagescene);
        stage.setResizable(false);
        stage.show();
    }
}