package flyxpert.flyxpert;

import javafx.scene.paint.Paint;

public class Type {
        String name;
        Paint color;

        void setName(String name) {
                this.name = name;
        }

        String getName() {
                return this.name;
        }
        void setColor(Paint color) {
                this.color = color;
        }

        Paint getColor() {
                return this.color;
        }
}
