package flyxpert.flyxpert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Reader {
    public void readAllFiles() throws FileNotFoundException {
        readFlightInformationFile();
    }


    public void readFlightInformationFile() throws FileNotFoundException {

        try {
            File file = new File("FlightInformation.txt");
            Scanner scan = new Scanner(file);
            scan.useDelimiter(",");

            while (scan.hasNext()) {

                Airport departureAirport = new Airport(scan.next(), scan.next(), scan.next());
                Airport arrivalAirport = new Airport(scan.next(), scan.next(), scan.next());
                String airlineName = scan.next();
                Time departureTime = new Time(scan.next(), scan.next(),scan.next());
                Time arrivalTime = new Time(scan.next(), scan.next(),scan.next());
                NewDate arrivalDay = new NewDate(scan.next(), scan.next(), scan.next());
                int economyPrice = Integer.parseInt(scan.next());
                int businessPrice = Integer.parseInt(scan.next());
                int firstClassPrice = Integer.parseInt(scan.next());
                int availableSeats = Integer.parseInt(scan.next());
                int flightNumber = Integer.parseInt(scan.next());

                boolean [][] seatsAvailability = new boolean[12][4];

                for(int i=0 ; i < 12 ; i++){
                    for(int j=0 ; j < 4 ; j++){
                        seatsAvailability[i][j] = Boolean.parseBoolean(scan.next().replaceAll("\\s+", ""));
                    }
                }

                Flight flight = new Flight(departureAirport, arrivalAirport, airlineName, departureTime, arrivalTime, arrivalDay, economyPrice, businessPrice, firstClassPrice, availableSeats, flightNumber,seatsAvailability);

                Flight.flights.add(flight);

            }

            scan.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
