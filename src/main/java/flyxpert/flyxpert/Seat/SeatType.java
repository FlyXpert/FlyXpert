package flyxpert.flyxpert.Seat;

import javafx.scene.paint.Paint;

public class SeatType {
        protected String name;
        public Paint color;

        public void setName(String name) {
                this.name = name;
        }

        public String getName() {
                return this.name;
        }
        public void setColor(Paint color) {
                this.color = color;
        }

        public Paint getColor() {
                return this.color;
        }
}
