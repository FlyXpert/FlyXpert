package flyxpert.flyxpert.Flight;

import flyxpert.flyxpert.Miscellaneous.NewDate;
import flyxpert.flyxpert.Miscellaneous.Time;

import java.util.ArrayList;


public class Flight {
    private Airport departureAirport;
    private Airport arrivalAirport;
    private String airlineName;
    private Time departureTime;
    private Time arrivalTime;
    private NewDate arrivalDate;
    private NewDate departureDate;
    private int economyPrice;
    private int businessPrice;
    private int firstClassPrice;
    private int availableSeats;
    private int flightNumber;
    private boolean[][] seatsAvailability;
    public static int selectedFlightIndex;
    public static Flight selectedFlight;
    public static int flightsCount;
    public Flight(){};

    public Flight(Flight flight) {
        this.departureAirport = flight.departureAirport;
        this.arrivalAirport = flight.arrivalAirport;
        this.airlineName = flight.airlineName;
        this.departureTime = flight.departureTime;
        this.arrivalTime = flight.arrivalTime;
        this.departureDate = flight.departureDate;
        this.arrivalDate = flight.arrivalDate;
        this.economyPrice = flight.economyPrice;
        this.businessPrice = flight.businessPrice;
        this.firstClassPrice = flight.firstClassPrice;
        this.availableSeats = flight.availableSeats;
        this.flightNumber = flight.flightNumber;
        this.seatsAvailability = flight.seatsAvailability;
    }

    public Flight(Airport departureAirport, Airport arrivalAirport, String airlineName, Time departureTime, Time arrivalTime, NewDate departureDate, NewDate arrivalDate, int economyPrice, int businessPrice, int firstClassPrice, int availableSeats , int flightNumber, boolean[][] seatsAvailability) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airlineName = airlineName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
        this.firstClassPrice = firstClassPrice;
        this.availableSeats = availableSeats;
        this.flightNumber = flightNumber;
        this.seatsAvailability = seatsAvailability;
    }
    public Flight(Airport departureAirport, Airport arrivalAirport, String airlineName, Time departureTime, Time arrivalTime, NewDate departureDate, NewDate arrivalDate, int economyPrice, int businessPrice, int firstClassPrice) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airlineName = airlineName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
        this.firstClassPrice = firstClassPrice;
    }

    public static ArrayList<Flight>flights = new ArrayList<>();
    public int getFlightNumber() {
        return flightNumber;
    }
    public String getAirLineName(){return this.airlineName;};
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getEconomyPrice() {
        return economyPrice;
    }

    public void setEconomyPrice(int economyPrice) {
        this.economyPrice = economyPrice;
    }

    public int getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(int businessPrice) {
        this.businessPrice = businessPrice;
    }

    public int getFirstClassPrice() {
        return firstClassPrice;
    }

    public void setFirstClassPrice(int firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    public NewDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(NewDate departureDate) {
        this.departureDate = departureDate;
    }
    public NewDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(NewDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public boolean[][] getSeatsAvailability() {
        return seatsAvailability;
    }
    public void setSeatsAvailability(boolean[][] seatsAvailability) {
        this.seatsAvailability = seatsAvailability;
    }
    public void reserveSeat(int row, int col) {
        this.seatsAvailability[row][col] = false;
    }
}