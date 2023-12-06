package flyxpert.flyxpert;

import javafx.scene.shape.Rectangle;

public class Seat {
         private Rectangle rec;
         private String primaryKey;
         private String flightId;
         private String type;
         private int row;
         private int col;


        void setRec(Rectangle rec) {
                this.rec = rec;
        }
        void setPrimaryKey(String primaryKey) {
                this.primaryKey = primaryKey;
        }
        void setFlightId(String id) {
                this.flightId = id;
        }
        void setType(String type) {
                this.type = type;
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
        String getType() {
                return this.type;
        }
        int getRow() {
                return this.row;
        }
        int getCol() {
                return this.col;
        }
}
