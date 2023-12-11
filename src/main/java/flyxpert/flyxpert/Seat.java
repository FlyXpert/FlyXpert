package flyxpert.flyxpert;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Seat {
         private Rectangle rec;
         private String primaryKey;
         private String flightId;
         private int row;
         private int col;
         private Type type;

         void setType(Type type) {
                 this.type = type;
         }
         Type getType() {
                 return this.type;
         }
        void setRec(Rectangle rec) {
                this.rec = rec;
        }
        void setPrimaryKey(String primaryKey) {
                this.primaryKey = primaryKey;
        }
        void setFlightId(String id) {
                this.flightId = id;
        }
        void setRow(int row) {
                this.row = row;
        }
        void setCol(int col) {
                this.col = col;
        }
        Rectangle getRec() {
                return this.rec;
        }
        String getPrimaryKey () {
                return this.primaryKey;
        }
        String getFlightId() {
                return this.flightId;
        }
        int getRow() {
                return this.row;
        }
        int getCol() {
                return this.col;
        }
}
