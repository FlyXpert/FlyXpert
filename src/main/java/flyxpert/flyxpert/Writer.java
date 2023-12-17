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
        WriteBookingNumber();
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

                    // To set all flights' seats to be available
                    //writer.print("true");

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

            PrintWriter bookingRecordsWriter = new PrintWriter(new FileWriter("bookingRecords.txt"));
            for (BookingConfirmation bookingRecord : BookingConfirmation.bookingRecords) {

                bookingRecordsWriter.println(bookingRecord.getUserName()  + "," + bookingRecord.getBookingNumber() + "," +
                        bookingRecord.getFlightID() + "," + bookingRecord.getAirLineName() + "," +
                        bookingRecord.getDepartureAirportName() + "," + bookingRecord.getArrivalAirportName() + "," +
                        bookingRecord.getDepartureDate() + "," + bookingRecord.getArrivalDate() + "," +
                        bookingRecord.getDepartureTime() + "," + bookingRecord.getArrivalTime()
                        + "," + bookingRecord.getPaymentID() + "," + bookingRecord.getPaymentMethood() + "," + bookingRecord.getPaymentAmount() + "," + bookingRecord.getPaymentStatus() + "," + bookingRecord.getEconomySeatsCount() + "," + bookingRecord.getBusinessSeatsCount() + "," + bookingRecord.getFirstClassSeatsCount()
                        + printAllPassengers());

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
            passengersDetails = "," + "passengerNumber" + "," + passengerNum + "," + passengerRecords.getSeat().getPrimaryKey() + "," + passengerRecords.getFirstName() + "," + passengerRecords.getMiddleName() +
                    "," + passengerRecords.getLastName() + "," + passengerRecords.getPhoneNumber() + "," + passengerRecords.getDateOfBirthAsAString();

            finalString = finalString + passengersDetails;
            passengerNum++;
        }
        return finalString;
    }


    public void WriteBookingNumber() throws FileNotFoundException{
        try {
            PrintWriter bookingNumberWriter = new PrintWriter(new FileWriter("bookingNumber.txt"));
            bookingNumberWriter.println(BookingConfirmation.lastBookingNumber);
            bookingNumberWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}