package flyxpert.flyxpert;

import java.util.ArrayList;
import  java.util.Date;
import java.util.Random;

public class BookingConfirmation {
    public static int lastBookingNumber = 1111234;
    private int bookingNumber;
    private Price economyClassPrice;
    private Payment payment;
    private User user;
    private Flight flight;
    int subTotalMoney = 0;
    int economySeatsCount = 0;
    int businessSeatsCount = 0;
    int firstClassSeatsCount = 0;
    public ArrayList<Passenger> bookingPassengers = new ArrayList<>();
    public static ArrayList<BookingConfirmation> bookingRecords = new ArrayList<>();

    public BookingConfirmation(User user, Flight currentFlight,  Payment payment, int economySeatsCount, int businessSeatsCount, int firstClassSeatsCount){;
        this.user = new User(user);
        this.flight = new Flight(currentFlight);
//        this.economyClassPrice = new Price(price);
        this.bookingNumber = lastBookingNumber++;
        this.payment = payment;
        this.economySeatsCount = economySeatsCount;
        this.businessSeatsCount = businessSeatsCount;
        this.firstClassSeatsCount = firstClassSeatsCount;
        setBoookingPassengers();
    }
    public int getBookingNumber() {
        return bookingNumber;
    }
    private void setBoookingPassengers(){
        this.bookingPassengers.addAll(Passenger.passengers);
    }
//    public Price getPrice() {
//        return this.economyClassPrice;
//    }
    public Payment getPayment(){
        return payment;
    }
    public User getUser() {
        return user;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setSubTotalMoney(int subTotalMoney){
        this.subTotalMoney = subTotalMoney;
    }
    public int getEconomySeatsCount() {
        return economySeatsCount;
    }
    public int getBusinessSeatsCount() {
        return businessSeatsCount;
    }
    public int getFirstClassSeatsCount() {
        return firstClassSeatsCount;
    }
}