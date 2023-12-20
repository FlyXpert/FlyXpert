package flyxpert.flyxpert.File;

import flyxpert.flyxpert.Booking.BookingConfirmation;
import flyxpert.flyxpert.Flight.Flight;
import flyxpert.flyxpert.Passenger.Passenger;
import flyxpert.flyxpert.User.User;

import java.io.FileNotFoundException;
import java.io.FileWriter;
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

                    // To set all flights' seats to be available
                    writer.print("true");

                    //writer.print(flight.getSeatsAvailability()[i][j]);
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
            PrintWriter writer = new PrintWriter(new FileWriter("Users.txt"));
            for(User user : User.userList) {
                int code = Encryption.generateCode();
                String password = user.getPassword();
                writer.println(user.getUserName() + ' ' + user.getEmail() + ' ' +
                        Encryption.encrypt(password, code) + ' ' + Encryption.constantEncrypt(Integer.toString(code)));
            }
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeBookingDataToFile() throws FileNotFoundException{
        try{

            PrintWriter bookingRecordsWriter = new PrintWriter(new FileWriter("BookingRecords.txt"));
            for (BookingConfirmation bookingRecord : BookingConfirmation.bookingRecords) {

                bookingRecordsWriter.println(bookingRecord.getUserName()  + "," + bookingRecord.getBookingNumber() + "," +
                        bookingRecord.getFlightID() + "," + bookingRecord.getAirLineName() + "," +
                        bookingRecord.getDepartureAirportName() + "," + bookingRecord.getArrivalAirportName() + "," +
                        bookingRecord.getDepartureDate() + "," + bookingRecord.getArrivalDate() + "," +
                        bookingRecord.getDepartureTime() + "," + bookingRecord.getArrivalTime()
                        + "," + bookingRecord.payment.getPaymentID() + "," + bookingRecord.payment.getPaymentMethod().getPaymentMethodName() + "," + bookingRecord.payment.getPaymentAmount() + "," + bookingRecord.payment.getPaymentStatus() + ","
                        + bookingRecord.getEconomySeatsCount() + "," + bookingRecord.getBusinessSeatsCount() + ","
                        + bookingRecord.getFirstClassSeatsCount()
                        + printAllPassengers(bookingRecord));
            }
            bookingRecordsWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public String printAllPassengers(BookingConfirmation bookingConfirmation){
        String passengersDetails = null;
        String finalString = "";
        for (Passenger passengerRecords: bookingConfirmation.getBookingPassengers()) {
            passengersDetails = "," + passengerRecords.getSeat().getPrimaryKey() + "," + passengerRecords.getFirstName() + "," + passengerRecords.getMiddleName() +
                    "," + passengerRecords.getLastName() + "," + passengerRecords.getPhoneNumber() + "," + passengerRecords.getDateOfBirth().toString();
            finalString = finalString + passengersDetails;
        }
        return finalString;
    }

}