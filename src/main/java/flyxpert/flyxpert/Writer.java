package flyxpert.flyxpert;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Writer {
    public void writeToAllFiles(){
        writeToAFile();
    }

    // This an example for writing data to a file (Remove this and add your own functions to write Users, Flights, etc.)
    public void writeToAFile(){
        // PrintWriter writer = new PrintWriter("");
        // writer.println();
        // Do writing here!
        // writer.close();
    }

    public void writeToFlightInformationFile() throws FileNotFoundException {

        PrintWriter writer = new PrintWriter("FlightInformation.txt");

        /*for(Flight flight : Flight.flights) {
            writer.println(flight.getDepartureAirport().getCode()+ "," + flight.getDepartureAirport().getName() + "," + flight.getDepartureAirport().getLocation() + ","
                    + flight.getArrivalAirport().getCode()+ "," + flight.getArrivalAirport().getName() + "," + flight.getArrivalAirport().getLocation() + ","
                    + flight.getAirlineName()+ "," + flight.getDepartureTime() + "," +flight.getArrivalTime()+ "," + flight.getArrivalDay()+ ","
                    + flight.getEconomyPrice()+ "," + flight.getBusinessPrice() + "," + flight.getFirstClassPrice() + ","
                    + flight.getAvailableSeats()+ "," + flight.getFlightNumber());
        }*/
        writer.close();
    }
}
