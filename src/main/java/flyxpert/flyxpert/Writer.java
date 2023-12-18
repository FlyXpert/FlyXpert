package flyxpert.flyxpert;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Writer {
    public void writeToAllFiles() throws FileNotFoundException {
        writeUsers();
        writeToFlightInformationFile();
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

            for(int i=0 ; i < 12 ; i++){
                for(int j=0 ; j < 4 ; j++){

                    writer.print(flight.getSeatsAvailability()[i][j]);
                    if(i == 11 && j == 3)
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
            for(User user : User.userList)
            {
                //System.out.println(user.getUserName() + ' ' + user.getEmail() + ' ' + user.getPassword());

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
}
