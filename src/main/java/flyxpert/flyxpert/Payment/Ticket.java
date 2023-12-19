package flyxpert.flyxpert.Payment;

import flyxpert.flyxpert.Flight.Flight;
import flyxpert.flyxpert.Passenger.Passenger;

public class Ticket {
    public int ticketNumber;
    public int fare;
    public String ticketStatus;
    public Passenger passenger;
    public Flight flight;

    Ticket(int ticketNumber, int fare, String ticketStatus, Passenger passenger, Flight flight)
    {
        this.ticketNumber = ticketNumber;
        this.fare = fare;
        this.ticketStatus = ticketStatus;
        this.passenger = passenger;
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void readTicket(Ticket ticket)
    {
        System.out.println("Mister/Miss " + ticket.getPassenger().getFirstName() + " Please Board Flight Number " + ticket.getFlight().getFlightNumber() + " before " + ticket.getFlight().getDepartureTime().getHour()+":"+ticket.getFlight().getDepartureTime().getMinutes()+ " " + ticket.getFlight().getDepartureTime().getPeriod() + "\n Ticket Number " + ticket.ticketNumber + "\n\n");
    }
}
