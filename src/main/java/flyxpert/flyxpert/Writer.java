package flyxpert.flyxpert;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Writer {
    public void writeToAllFiles() throws FileNotFoundException {
        writeToFlightInformationFile();
    }

    public void writeToFlightInformationFile() throws FileNotFoundException {

        PrintWriter writer = new PrintWriter("FlightInformation.txt");
        boolean firstElement = false;
        for(Flight flight : Flight.flights) {

            if(firstElement)
            writer.print(",");

            writer.println(flight.getDepartureAirport().getCode()+ "," + flight.getDepartureAirport().getName() + "," + flight.getDepartureAirport().getLocation() + ","
                    + flight.getArrivalAirport().getCode()+ "," + flight.getArrivalAirport().getName() + "," + flight.getArrivalAirport().getLocation() + ","
                    + flight.getAirlineName()+ "," + flight.getDepartureTime().getHour() + "," +flight.getDepartureTime().getMinutes() + "," + flight.getDepartureTime().getPeriod() + ","
                    + flight.getArrivalTime().getHour() + "," + flight.getArrivalTime().getMinutes() + "," + flight.getArrivalTime().getPeriod() + ","
                    + flight.getArrivalDay().getDay() +  "," + flight.getArrivalDay().getMonth() +  "," + flight.getArrivalDay().getYear() +  ","
                    + flight.getEconomyPrice()+ "," + flight.getBusinessPrice() + "," + flight.getFirstClassPrice() + ","
                    + flight.getAvailableSeats()+ "," + flight.getFlightNumber());

            firstElement = true;
        }
        writer.close();
    }
}
