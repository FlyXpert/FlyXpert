package flyxpert.flyxpert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        BookingConfirmation bookingConfirmation = new BookingConfirmation("Mohsen", 123, "JAPAN", "patpal");
        Reader reader = new Reader();
        System.out.println("$" + (200 + 300));
        reader.readAllFiles();
        launch(args);
        Writer writer = new Writer();
        writer.writeToAllFiles();
    }

    public void start(Stage stage) throws IOException
    {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("TestBooking.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("TestBooking.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("MoeStyle.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
}