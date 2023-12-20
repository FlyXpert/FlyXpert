package flyxpert.flyxpert.File;

import flyxpert.flyxpert.Booking.BookingConfirmation;
import flyxpert.flyxpert.Flight.*;
import flyxpert.flyxpert.Miscellaneous.NewDate;
import flyxpert.flyxpert.Payment.Payment;
import flyxpert.flyxpert.Miscellaneous.Time;
import flyxpert.flyxpert.User.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    public void readAllFiles() throws FileNotFoundException {
        readFlightInformationFile();
        readUsers();
        readBookingRecords();
//        readBookingNumber();
    }


    public void readFlightInformationFile() throws FileNotFoundException {

        try {
            File file = new File("FlightInformation.txt");
            Scanner scan = new Scanner(file);
            scan.useDelimiter(",");

            Flight.flightsCount = 0;

            while (scan.hasNext()) {
                Airport departureAirport = new Airport(scan.next(), scan.next(), scan.next());
                Airport arrivalAirport = new Airport(scan.next(), scan.next(), scan.next());
                String airlineName = scan.next();
                Time departureTime = new Time(scan.next(), scan.next(), scan.next());
                Time arrivalTime = new Time(scan.next(), scan.next(), scan.next());
                NewDate departureDate = new NewDate(scan.next(), scan.next(), scan.next());
                NewDate arrivalDate = new NewDate(scan.next(), scan.next(), scan.next());
                int economyPrice = Integer.parseInt(scan.next());
                int businessPrice = Integer.parseInt(scan.next());
                int firstClassPrice = Integer.parseInt(scan.next());
                int availableSeats = Integer.parseInt(scan.next());
                int flightNumber = Integer.parseInt(scan.next());
                // So that flightsCount will have the number of the last flight in the file, which is equal to the flights count
                Flight.flightsCount = flightNumber;

                boolean[][] seatsAvailability = new boolean[24][4];

                for (int i = 0; i < 24; i++) {
                    for (int j = 0; j < 4; j++) {
                        seatsAvailability[i][j] = Boolean.parseBoolean(scan.next().replaceAll("\\s+", ""));
                    }
                }

                Flight flight = new Flight(departureAirport, arrivalAirport, airlineName, departureTime, arrivalTime, departureDate, arrivalDate, economyPrice, businessPrice, firstClassPrice, availableSeats, flightNumber, seatsAvailability);

                Flight.flights.add(flight);

            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // User data is written in which each line contains all the data for one user, seperated by spaces (Username Email Password)
    public void readUsers() {
        try {
            File file = new File("Users.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String user = scan.nextLine();
                //System.out.println(user);
                String[] userCredentials = user.split(" ");
                int code = Integer.parseInt(Encryption.constantDecrypt(userCredentials[3]));
                User.userList.add(new User(userCredentials[0], userCredentials[1], Encryption.decrypt(userCredentials[2], code)));
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readBookingRecords() throws FileNotFoundException{

        try {
            File file = new File("BookingRecords.txt");
            Scanner scan = new Scanner(file);
            scan.useDelimiter(",");
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] bookingDetails = line.split(",");
                BookingConfirmation bookingConfirmation = new BookingConfirmation(bookingDetails);
                BookingConfirmation.bookingRecords.add(bookingConfirmation);
                Payment.allPaymentsMadeCount = Integer.parseInt(bookingDetails[10]);
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

