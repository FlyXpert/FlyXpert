package flyxpert.flyxpert;

import javafx.scene.shape.Rectangle;

public class Seat {
         private Rectangle rec;
         private String primaryKey;
         private String flightId;
         private int row;
         private int col;
         private SeatType seatType;

         void setType(SeatType seatType) {
                 this.seatType = seatType;
         }
         SeatType getType() {
                 return this.seatType;
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
