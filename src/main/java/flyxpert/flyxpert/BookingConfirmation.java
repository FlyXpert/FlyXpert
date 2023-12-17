package flyxpert.flyxpert;

import java.util.ArrayList;

public class BookingConfirmation {
    public static int lastBookingNumber;
    private int bookingNumber;
    private String userName, flightID, airLineName, departureAirportName, arrivalAirportName, departureDate, arrivalDate,  departureTime, arrivalTime, paymentID, paymentMethood, paymentAmount, paymentStatus;
    private Payment payment;
    private User user;
    private Flight flight;
    private int subTotalMoney = 0;
    private int economySeatsCount = 0;
    private int businessSeatsCount = 0;
    private int firstClassSeatsCount = 0;
    public ArrayList<Passenger> bookingPassengers = new ArrayList<>();
    public static ArrayList<BookingConfirmation> bookingRecords = new ArrayList<>();


    public BookingConfirmation(String userName, String flightID, String airLineName, String departureAirportName, String arrivalAirportName, String departureDate, String arrivalDate, String departureTime, String arrivalTime, String paymentID, String paymentMethood, String paymentAmount, String paymentStatus, int economySeatsCount, int businessSeatsCount, int firstClassSeatsCount) {
        this.userName = userName;
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
    public BookingConfirmation(String airLineName, String arrivalDate, String arrivalTime){

    }
    public int getBookingNumber() {
        return bookingNumber;
    }


    public void setAvaliableSeats(Flight currentFlight){
        for (Passenger passenger : Passenger.passengers) {
            currentFlight.reserveSeat(passenger.getSeat().getRow(), passenger.getSeat().getCol());
            currentFlight.setAvailableSeats(currentFlight.getAvailableSeats() - 1);
        }
    }
    public void setBoookingPassengers(ArrayList<Passenger> passengers){
        this.bookingPassengers.addAll(passengers);

    }
//    public Price getPrice() {
//        return this.economyClassPrice;
//    }
//    public Payment getPayment(){
//        return payment;
//    }
//    public User getUser() {
//        return user;
//    }
//    public Flight getFlight() {
//        return flight;
//    }
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
    public User getUser() {
        return user;
    }
    public Flight getFlight() {
        return flight;
    }
}