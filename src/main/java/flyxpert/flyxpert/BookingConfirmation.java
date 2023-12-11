package flyxpert.flyxpert;

import  java.util.Date;
import java.util.Random;

public class BookingConfirmation {
    private String name; //might delete later
    private static int lastBookingNumber = 1111234;
    private int bookingNumber;
    private String airLineName;
    private Price economyClassPrice;
    private String destination;
    private String departingDate;
    private Payment payment;
    public BookingConfirmation(String name, String price, String airLineName, Payment payment, String destination, String departingDate){;
        this.name = name;
        this.economyClassPrice = new Price(price);
        this.airLineName = airLineName;
        this.departingDate = departingDate;
        this.destination = destination;
        this.bookingNumber = lastBookingNumber++;
        this.payment = payment;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getDepartingDate() {
        return departingDate;
    }
    public int getBookingNumber() {
        return bookingNumber;
    }
    public Price getPrice() {
        return this.economyClassPrice;
    }
    public String getAirLineName() {
        return this.airLineName;
    }
    public String getDestination(){
        return this.destination;
    }
    public Payment getPayment(){
        return payment;
    }
}