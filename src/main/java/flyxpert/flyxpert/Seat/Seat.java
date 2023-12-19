package flyxpert.flyxpert.Seat;

import javafx.scene.shape.Rectangle;

public class Seat {
         private Rectangle rec;
         private String primaryKey;
         private String flightId;
         private int row;
         private int col;
         private SeatType seatType;

         protected void setType(SeatType seatType) {
                 this.seatType = seatType;
         }
         public SeatType getType() {
                 return this.seatType;
         }
        protected void setRec(Rectangle rec) {
                this.rec = rec;
        }
        public void setPrimaryKey(String primaryKey) {
                this.primaryKey = primaryKey;
        }
        protected void setFlightId(String id) {
                this.flightId = id;
        }
        protected void setRow(int row) {
                this.row = row;
        }
        protected void setCol(int col) {
                this.col = col;
        }
        public Rectangle getRec() {
                return this.rec;
        }
        public String getPrimaryKey () {
                return this.primaryKey;
        }
        protected String getFlightId() {
                return this.flightId;
        }
        public int getRow() {
                return this.row;
        }
        public int getCol() {
                return this.col;
        }
}
