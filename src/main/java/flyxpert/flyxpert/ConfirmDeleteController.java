package flyxpert.flyxpert;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;


public class ConfirmDeleteController {

        private static int index = -1;
        public static void setData(int indx) {
                index = indx;
        }

        public void confirmed(ActionEvent e) {
                //UserHistoryController.confirmed = true;

                UserHistoryController.confirm(index);
                Stage s = new Stage();
                s = (Stage) ((Node) e.getSource()).getScene().getWindow();
                s.close();

        }

        public void discarded(ActionEvent e) {
                Stage s = new Stage();
                s = (Stage) ((Node) e.getSource()).getScene().getWindow();
                s.close();
        }
}
