package flyxpert.flyxpert;

import java.util.Date;
import java.util.Random;

public class BookingConfirmation {
    private String name;
    private String bookingNumber;
    private int price;
    private String payingMethod;
    private String airLineName;
    public BookingConfirmation(String name, int price, String airLineName, String payingMethod){;
        this.airLineName = airLineName;
        this.name = name;
        this.price = price;
        this.payingMethod = payingMethod;
        {
            Random random = new Random();
            StringBuilder sb = new StringBuilder(9);
            sb.append(random.nextInt(9) + 1);
            for (int i = 1; i < 9; i++) {
                sb.append(random.nextInt(10)); // Append random digits from 0 to 9
            }
            this.bookingNumber = sb.toString();
            System.out.println("Random Number of Length 9: " + this.bookingNumber);
        }
    }
    public BookingConfirmation(BookingConfirmation bookingConfirmation){;
        this.name = bookingConfirmation.name;
        this.bookingNumber = bookingConfirmation.bookingNumber;
        this.price = bookingConfirmation.price;
        this.airLineName = bookingConfirmation.airLineName;
        this.payingMethod = bookingConfirmation.payingMethod;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setAirLineName(String airLineName) {
        this.airLineName = airLineName;
    }
    public String getName() {
        return name;
    }
    public String getBookingNumber() {
        return bookingNumber;
    }
    public int getPrice() {
        return price;
    }

    public String getAirLineName() {
        return airLineName;
    }
}