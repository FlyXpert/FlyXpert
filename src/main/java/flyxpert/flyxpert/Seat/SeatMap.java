package flyxpert.flyxpert.Seat;

import flyxpert.flyxpert.Flight.Flight;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static flyxpert.flyxpert.Miscellaneous.Palette.*;

public abstract class SeatMap {

         protected Seat[][] economySeats = new Seat[200][200];
         protected Seat[][] businessSeats = new Seat[200][200];
         protected Seat[][] firstClassSeats = new Seat[200][200];

         protected Seat[][] seats = new Seat[200][200];
         protected boolean[][] vis = new boolean[200][200];

        /**
         * Performs a Depth-First Search (DFS) traversal to add seat objects to a specified overlay Pane.
         * The seats are represented as rectangles and are organized based on their type (business, economy, first class).
         * The method recursively explores adjacent seats and initializes their properties such as type, color, and primary key.
         *
         * @param overlay The Pane where seat rectangles will be added.
         * @param x       The starting x-coordinate for the seat rectangle.
         * @param y       The starting y-coordinate for the seat rectangle.
         * @param i       The row index of the seat in the seating arrangement.
         * @param j       The column index of the seat in the seating arrangement.
         */
        public void dfsAddSeats(Pane overlay, int x, int y, int i, int j) {
                // Mark the current seat as visited
                vis[i][j] = true;

                // Base case: If the current seat is beyond the seating arrangement boundaries, return
                if (i >= 24 || j >= 4)
                        return;

                // Create a new seat object and initialize its properties
                seats[i][j] = new Seat();
                seats[i][j].setRec(new Rectangle(30, 40));
                seats[i][j].setRow(i);
                seats[i][j].setCol(j);

                // Set the appearance of the seat rectangle
                seats[i][j].getRec().setArcWidth(12);
                seats[i][j].getRec().setArcHeight(12);
                seats[i][j].getRec().setLayoutX(x);
                seats[i][j].getRec().setLayoutY(y);
                seats[i][j].getRec().setOnMouseClicked(event -> seatClicked(seats[i][j]));
                seats[i][j].getRec().setOnMouseEntered(event -> {
                        seats[i][j].getRec().setOpacity(.75);
                });

                seats[i][j].getRec().setOnMouseExited(event -> {
                        seats[i][j].getRec().setOpacity(1);
                });

                // Determine the type and color of the seat based on its position in the seating arrangement
                int d = i / 4;
                Paint toBe = null;

                switch (d) {
                        case 0: {
                                // Business class seat
                                toBe = Color.web(GREEN);
                                SeatType seatType = new SeatType();
                                seatType.setColor(toBe);
                                seatType.setName("business");
                                seats[i][j].setType(seatType);
                                seats[i][j].getRec().setFill(toBe);
                                String fstHalf = String.valueOf(i + 1);
                                seats[i][j].setPrimaryKey(fstHalf + (char) (j + 'A'));
                                businessSeats[i][j] = seats[i][j];
                                break;
                        }
                        case 1, 2, 3, 4: {
                                // Economy class seat
                                toBe = Color.web(DARK_PURPLE);
                                SeatType seatType = new SeatType();
                                seatType.setColor(toBe);
                                seatType.setName("economy");
                                seats[i][j].setType(seatType);
                                seats[i][j].getRec().setFill(toBe);
                                String fstHalf = String.valueOf(i + 1);
                                seats[i][j].setPrimaryKey(fstHalf + (char) (j + 'A'));
                                economySeats[i][j] = seats[i][j];
                                break;
                        }
                        case 5: {
                                // First-class seat
                                toBe = Color.web(RED);
                                SeatType seatType = new SeatType();
                                seatType.setColor(toBe);
                                seatType.setName("firstClass");
                                seats[i][j].setType(seatType);
                                seats[i][j].getRec().setFill(toBe);
                                String fstHalf = String.valueOf(i + 1);
                                seats[i][j].setPrimaryKey(fstHalf + (char) (j + 'A'));
                                firstClassSeats[i][j] = seats[i][j];
                                break;
                        }
                }

                if (Flight.flights.get(Flight.selectedFlightIndex).getSeatsAvailability()[i][j] == false)
                        seats[i][j].getRec().setFill(Color.GRAY);

                // Add the seat rectangle to the overlay Pane
                overlay.getChildren().add(seats[i][j].getRec());

                // Recursive calls to explore adjacent seats in the downward and rightward directions
                int add = 0;

                // Recursive call to explore adjacent seats in the rightward direction
                if (j + 1 < 100 && !vis[i][j + 1]) {
                        // Add additional space for the second column
                        if (j + 1 == 2)
                                add = 25;
                        dfsAddSeats(overlay, x + 40 + add, y, i, j + 1);
                }

                // Reset the additional space for the next recursive call
                add = 0;
                if (i + 1 < 100 && !vis[i + 1][j] && (j % 4) == 0) {
                        // Add additional space between rows if the current row is a multiple of 4
                        if ((i + 1) % 4 == 0) {
                                add = 30;
                                // Special case for row 3 to accommodate a larger gap
                                if (i == 3)
                                        add = 60;
                        }
                        dfsAddSeats(overlay, x, y + 50 + add, i + 1, j);
                }
        }

        public abstract void seatClicked(Seat seat);
}
