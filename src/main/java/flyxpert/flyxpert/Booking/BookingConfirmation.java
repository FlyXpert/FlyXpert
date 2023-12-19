package flyxpert.flyxpert.Booking;

import flyxpert.flyxpert.Flight.Flight;
import flyxpert.flyxpert.Passenger.Passenger;

import java.util.ArrayList;

public class BookingConfirmation {
    public static int lastBookingNumber;
    private int bookingNumber;
    private String userName, flightID, airLineName, departureAirportName, arrivalAirportName, departureDate, arrivalDate, departureTime, arrivalTime, paymentID, paymentMethood, paymentAmount, paymentStatus;
    private int subTotalMoney = 0;
    private int economySeatsCount = 0;
    private int businessSeatsCount = 0;
    private int firstClassSeatsCount = 0;
    public ArrayList<Passenger> bookingPassengers = new ArrayList<>();
    public static ArrayList<BookingConfirmation> bookingRecords = new ArrayList<>();


    public BookingConfirmation(String userName, String flightID, String airLineName, String departureAirportName, String arrivalAirportName, String departureDate, String arrivalDate, String departureTime, String arrivalTime, String paymentID, String paymentMethood, String paymentAmount, String paymentStatus, int economySeatsCount, int businessSeatsCount, int firstClassSeatsCount) {
        this.userName = userName;
        this.bookingNumber = BookingConfirmation.lastBookingNumber++;
        this.flightID = flightID;
        this.airLineName = airLineName;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.paymentID = paymentID;
        this.paymentMethood = paymentMethood;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.economySeatsCount = economySeatsCount;
        this.businessSeatsCount = businessSeatsCount;
        this.firstClassSeatsCount = firstClassSeatsCount;
    }
    public BookingConfirmation(String[] details){
        this.userName = details[0];
        this.bookingNumber = Integer.parseInt(details[1]);
        BookingConfirmation.lastBookingNumber = Integer.parseInt(details[1]) + 1;
        this.flightID = details[2];
        this.airLineName = details[3];
        this.departureAirportName = details[4];
        this.arrivalAirportName = details[5];
        this.departureDate = details[6];
        this.arrivalDate = details[7];
        this.departureTime = details[8];
        this.arrivalTime = details[9];
        this.paymentID = details[10];
        this.paymentMethood = details[11];
        this.paymentAmount = details[12];
        this.paymentStatus = details[13];
        this.economySeatsCount = Integer.parseInt(details[14]);
        this.businessSeatsCount = Integer.parseInt(details[15]);
        this.firstClassSeatsCount = Integer.parseInt(details[16]);
        int sum = this.economySeatsCount + this.businessSeatsCount + this.firstClassSeatsCount;
        readPassengers(details, sum);
    }
//    public BookingConfirmation(User user, Flight currentFlight, Payment payment, int economySeatsCount, int businessSeatsCount, int firstClassSeatsCount){;
//        this.user = new User(user);
////        this.economyClassPrice = new Price(price);
//        this.bookingNumber = lastBookingNumber++;
//        this.payment = payment;
//        this.economySeatsCount = economySeatsCount;
//        this.businessSeatsCount = businessSeatsCount;
//        this.firstClassSeatsCount = firstClassSeatsCount;
//        setBoookingPassengers(currentFlight);
//        this.flight = new Flight(currentFlight);
    //    }
    private void readPassengers(String[] bookingDetails, int numberOfPassengers){
    Passenger passenger;
    for (int i = 1; i <= numberOfPassengers; i++){
        if(i == 1) {
            passenger = new Passenger(
                    bookingDetails[17], bookingDetails[18], bookingDetails[19],
                    bookingDetails[20], bookingDetails[21], bookingDetails[22]);
        }
        else {
            passenger = new Passenger(
                    bookingDetails[17 + ((i - 1) * 6)], bookingDetails[18 + ((i - 1) * 6)], bookingDetails[19 + ((i - 1) * 6)],
                    bookingDetails[20 + ((i - 1) * 6)], bookingDetails[21 + ((i - 1) * 6)], bookingDetails[22 + ((i - 1) * 6)]);
        }
        this.bookingPassengers.add(passenger);
    }
}
    public int getBookingNumber() {
        return bookingNumber;
    }
    public void setAvailableSeats(Flight currentFlight){
        for (Passenger passenger : Passenger.passengers) {
            currentFlight.reserveSeat(passenger.getSeat().getRow(), passenger.getSeat().getCol());
            currentFlight.setAvailableSeats(currentFlight.getAvailableSeats() - 1);
        }
    }
    public void setBoookingPassengers(ArrayList<Passenger> passengers){
        this.bookingPassengers.addAll(passengers);

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
    public String getUserName() {
        return userName;
    }
    public String getFlightID() {
        return flightID;
    }
    public String getAirLineName() {
        return airLineName;
    }
    public String getDepartureAirportName() {
        return departureAirportName;
    }
    public String getArrivalAirportName() {
        return arrivalAirportName;
    }
    public String getDepartureDate() {
        return departureDate;
    }
    public String getArrivalDate() {
        return arrivalDate;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public String getPaymentID() {
        return paymentID;
    }
    public String getPaymentMethood() {
        return paymentMethood;
    }
    public String getPaymentAmount() {
        return paymentAmount;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
    public ArrayList<Passenger> getBookingPassengers() {
        return bookingPassengers;
    }
}