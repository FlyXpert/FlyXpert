package flyxpert.flyxpert.Miscellaneous;

import flyxpert.flyxpert.Flight.FlightInformationController;
import flyxpert.flyxpert.Main;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

        public static Stage stage = new Stage();
        static Scene scene;
        static Parent root;

        /**
         * A function to switch between 2 scenes
         * @param event The Action event (The one passed to your function) which occurred to trigger scene switching (Button press, etc.)
         * @param newFxml The FXML you are going to switch to (without the .fxml part) (Ex: PaymentPage)
         * @param mainStageIfPopUpExist IF you are switching from a pop-up window, then pass your main stage, IF NOT then pass null
         */
        public static void switchScene(Event event, String newFxml, Stage mainStageIfPopUpExist) {
                try {
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource(newFxml + ".fxml"));
                        scene = new Scene(loader.load());

                        // Reset the Flight Search Page
                        if(newFxml.equals("SearchFlightPage")){
                                FlightInformationController flightInformationController = loader.getController();
                                flightInformationController.fillDataOfFlights();
                        }
                } catch (IOException e) {
                        System.out.printf("Unable to import %s.fxml in SceneSwitcher OR there is an error in %s.fxml or it's controller!", newFxml, newFxml);
                        e.printStackTrace();
                }

                if (mainStageIfPopUpExist != null) {
                        stage = mainStageIfPopUpExist;
                        Stage popUpStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        popUpStage.close();
                } else
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();
        }

        public static void createPopUp(String newFxml) {
                try {
                        root = FXMLLoader.load(Main.class.getResource(newFxml + ".fxml"));
                } catch (IOException e) {
                        System.out.printf("Unable to import %s.fxml in SceneSwitcher", newFxml);
                        e.printStackTrace();
                }
                stage = new Stage();
                scene = new Scene(root);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOnShown(e -> stage.getScene().getRoot().requestFocus());
                stage.setResizable(false);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
        }
}
