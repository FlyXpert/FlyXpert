package flyxpert.flyxpert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Reader {

    public void readAllFiles() throws FileNotFoundException {
        readFlightInformationFile();
        readUsers();
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
                NewDate departureDate = new NewDate(scan.next(), scan.next(), scan.next());
                NewDate arrivalDate = new NewDate(scan.next(), scan.next(), scan.next());
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

                Flight flight = new Flight(departureAirport, arrivalAirport, airlineName, departureTime, arrivalTime, departureDate, arrivalDate, economyPrice, businessPrice, firstClassPrice, availableSeats, flightNumber,seatsAvailability);

                Flight.flights.add(flight);

            }

            scan.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // User data is written in which each line contains all the data for one user, seperated by spaces (Username Email Password)
    public void readUsers() {
        try {
            File file = new File("users.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String user = scan.nextLine();
                //System.out.println(user);
                String[] userCredentials = user.split(" ");
                int code = Integer.parseInt(Encryption.constantDecryption(userCredentials[3]));
                User.userList.add(new User(userCredentials[0], userCredentials[1], Encryption.decryption(userCredentials[2], code)));
            }
            scan.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
