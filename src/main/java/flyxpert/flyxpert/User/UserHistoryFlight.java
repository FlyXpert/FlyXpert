package flyxpert.flyxpert.User;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class UserHistoryFlight {
        private Rectangle rec;
        private ImageView x;
        private Text airlineText;
        private Text dateText;
        private Text timeText;
        private Text arrivalDateText;
        private Button delete;

        // Getter and Setter for rec
        public Rectangle getRec() {
                return rec;
        }

        public void setRec(Rectangle rec) {
                this.rec = rec;
        }

        // Getter and Setter for x
        public ImageView getX() {
                return x;
        }

        public void setX(ImageView x) {
                this.x = x;
        }

        public Text getAirlineText() {
                return airlineText;
        }

        // Setter for airlineText
        public void setAirlineText(Text airlineText) {
                this.airlineText = airlineText;
        }

        // Getter for dateText
        public Text getDateText() {
                return dateText;
        }

        // Setter for dateText
        public void setDateText(Text dateText) {
                this.dateText = dateText;
        }

        // Getter for timeText
        public Text getTimeText() {
                return timeText;
        }

        // Setter for timeText
        public void setTimeText(Text timeText) {
                this.timeText = timeText;
        }

        public Text getArrivalDateText() {
                return arrivalDateText;
        }

        public void setArrivalDateText(Text arrivalDateText) {
                this.arrivalDateText = arrivalDateText;
        }

        public Button getDelete() {
                return delete;
        }

        public void setDelete(Button delete) {
                this.delete = delete;
        }
}
