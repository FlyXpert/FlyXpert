package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

        static Stage stage = new Stage();
        static Scene scene;
        static Parent root;

        /**
         * A function to switch between 2 scenes
         * @param event The Action event (The one passed to your function) which occurred to trigger scene switching (Button press, etc.)
         * @param newFxml The FXML you are going to switch to (without the .fxml part) (Ex: PaymentPage)
         * @param mainStageIfPopUpExist IF you are switching from a pop-up window, then pass your main stage, IF NOT then pass null
         */
        public static void switchScene(ActionEvent event, String newFxml, Stage mainStageIfPopUpExist) {

                if (newFxml.equals("SearchFlightPage")) {
                        try {
                                stage = mainStageIfPopUpExist;
                                Stage popUpStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                popUpStage.close();

                                FXMLLoader loader = new FXMLLoader(Main.class.getResource("SearchFlightPage.FXML"));
                                scene = new Scene(loader.load());
                                stage.setScene(scene);
                                FlightInformationController flightInformationController = loader.getController();
                                flightInformationController.fillDataOfFlights();
                                stage.centerOnScreen();
                                stage.show();

                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
                else {
                        try {
                                root = FXMLLoader.load(SeatSelectionPageController.class.getResource(newFxml + ".fxml"));
                        } catch (IOException e) {
                                System.out.printf("Unable to import %s.fxml", newFxml);
                        }

                        if (mainStageIfPopUpExist != null) {
                                stage = mainStageIfPopUpExist;
                                Stage popUpStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                popUpStage.close();
                        } else
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        stage.show();
                }
        }
        public static void switchScene(MouseEvent event, String newFxml, Stage mainStageIfPopUpExist) {
                try {
                        root = FXMLLoader.load(SeatSelectionPageController.class.getResource(newFxml + ".fxml"));
                } catch (IOException e) {
                        System.out.printf("Unable to import %s.fxml", newFxml);
                }

                if (mainStageIfPopUpExist != null) {
                        stage = mainStageIfPopUpExist;
                        Stage popUpStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        popUpStage.close();
                } else
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
        }

        public static void createPopUp(String newFxml, int index) {
                try {
                        root = FXMLLoader.load(SeatSelectionPageController.class.getResource(newFxml + ".fxml"));
                } catch (IOException e) {
                        System.out.printf("Unable to import %s.fxml", newFxml);
                }
                stage = new Stage();
                scene = new Scene(root);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOnShown(e -> stage.getScene().getRoot().requestFocus());
                stage.setResizable(false);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

                if (newFxml.equals("AdminSeatMap"))
                        ConfirmDeleteController.setData(index);
        }

}
