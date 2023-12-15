package flyxpert.flyxpert;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
    public void writeToAllFiles() throws FileNotFoundException {
        writeUsers();
        writeToFlightInformationFile();
        writeBookingDataToFile();
    }


    public void writeToFlightInformationFile() throws FileNotFoundException {

        PrintWriter writer = new PrintWriter("FlightInformation.txt");
        boolean firstElement = false;
        for(Flight flight : Flight.flights) {

            if(firstElement)
                writer.print(",");

            writer.print(flight.getDepartureAirport().getCode()+ "," + flight.getDepartureAirport().getName() + "," + flight.getDepartureAirport().getLocation() + ","
                    + flight.getArrivalAirport().getCode()+ "," + flight.getArrivalAirport().getName() + "," + flight.getArrivalAirport().getLocation() + ","
                    + flight.getAirlineName()+ "," + flight.getDepartureTime().getHour() + "," +flight.getDepartureTime().getMinutes() + "," + flight.getDepartureTime().getPeriod() + ","
                    + flight.getArrivalTime().getHour() + "," + flight.getArrivalTime().getMinutes() + "," + flight.getArrivalTime().getPeriod() + ","
                    + flight.getDepartureDate().getDay() +  "," + flight.getDepartureDate().getMonth() +  "," + flight.getDepartureDate().getYear() +  ","
                    + flight.getArrivalDate().getDay() +  "," + flight.getArrivalDate().getMonth() +  "," + flight.getArrivalDate().getYear() +  ","
                    + flight.getEconomyPrice()+ "," + flight.getBusinessPrice() + "," + flight.getFirstClassPrice() + ","
                    + flight.getAvailableSeats()+ "," + flight.getFlightNumber() + ",");

            for(int i=0 ; i < 24 ; i++){
                for(int j=0 ; j < 4 ; j++){

                    writer.print(flight.getSeatsAvailability()[i][j]);
                    if(i == 23 && j == 3)
                        writer.println();
                    else
                        writer.print(",");
                }
            }

            firstElement = true;
        }
        writer.close();
    }

    public void writeUsers() throws FileNotFoundException {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("users.txt"));
            for(User user : User.userList) {
                //System.out.println(user.getUserName() + ' ' + user.getEmail() + ' ' + user.getPassword());
                writer.println(user.getUserName() + ' ' + user.getEmail() + ' ' + user.getPassword());
            }
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeBookingDataToFile() throws FileNotFoundException{
        try{
            PrintWriter bookingRecordsWriter = new PrintWriter(new FileWriter("bookingRecords.txt", true));
            for (BookingConfirmation bookingRecord : BookingConfirmation.bookingRecords) {
                bookingRecordsWriter.println(bookingRecord.getUser().getUserName() + " " + " " + bookingRecord.getBookingNumber() + " " +
                bookingRecord.getFlight().getAirlineName() + " " +
                bookingRecord.getFlight().getDepartureAirport() + " " + bookingRecord.getFlight().getArrivalAirport() + " " + bookingRecord.getFlight().getFlightNumber()
                + " " + bookingRecord.getFlight().getDepartureDate().toString() + " " + bookingRecord.getFlight().getArrivalDate().toString() + " " +
                bookingRecord.getFlight().getDepartureTime().toString() + " " + bookingRecord.getFlight().getArrivalTime().toString()
                + " " + bookingRecord.getPayment().getPaymentID() + " " + bookingRecord.getPayment().toString() + " " + bookingRecord.getPayment().getPaymentAmount() + " " + bookingRecord.getPayment().getPaymentStatus()
                + printAllPassengers() + "%n");


            }
            bookingRecordsWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public String printAllPassengers(){
        int passengerNum = 1;
        String passengersDetails = null;
        String finalString = "";
        for (Passenger passengerRecords: Passenger.passengers) {
            passengersDetails = " " + "passengerNumber" + " " + passengerNum + " " + passengerRecords.getSeat() + " " + passengerRecords.getFirstName() + " " + passengerRecords.getMiddleName() +
            " " + passengerRecords.getLastName() + " " + passengerRecords.getPhoneNumber() + " " + passengerRecords.getDateOfBirth();
            finalString = finalString + passengersDetails;
            passengerNum++;
        }
        return finalString;
    }

}
