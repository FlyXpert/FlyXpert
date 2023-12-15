package flyxpert.flyxpert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    public void readAllFiles() throws FileNotFoundException {
        readFlightInformationFile();
        readUsers();
        readBookingRecords();
        readBookingNumber();
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
                Time departureTime = new Time(scan.next(), scan.next(), scan.next());
                Time arrivalTime = new Time(scan.next(), scan.next(), scan.next());
                NewDate departureDate = new NewDate(scan.next(), scan.next(), scan.next());
                NewDate arrivalDate = new NewDate(scan.next(), scan.next(), scan.next());
                int economyPrice = Integer.parseInt(scan.next());
                int businessPrice = Integer.parseInt(scan.next());
                int firstClassPrice = Integer.parseInt(scan.next());
                int availableSeats = Integer.parseInt(scan.next());
                int flightNumber = Integer.parseInt(scan.next());

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
            File file = new File("users.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String user = scan.nextLine();
                //System.out.println(user);
                String[] userCredentials = user.split(" ");
                User.userList.add(new User(userCredentials[0], userCredentials[1], userCredentials[2]));
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readBookingRecords() throws FileNotFoundException{

        try {
            File file = new File("bookingRecords.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] bookingDetails = line.split(",");
                BookingConfirmation bookingConfirmation = new BookingConfirmation(
                        bookingDetails[0], bookingDetails[2], bookingDetails[3],
                        bookingDetails[4], bookingDetails[5], bookingDetails[6],
                        bookingDetails[7], bookingDetails[8], bookingDetails[9],
                        bookingDetails[10], bookingDetails[11], bookingDetails[12],
                        bookingDetails[13], Integer.parseInt(bookingDetails[14]), Integer.parseInt(bookingDetails[15]),
                        Integer.parseInt(bookingDetails[16]));
                bookingConfirmation.setBookingNumber(Integer.parseInt(bookingDetails[1]));
                int numberOfPassengers = bookingConfirmation.getEconomySeatsCount() + bookingConfirmation.getBusinessSeatsCount() + bookingConfirmation.getFirstClassSeatsCount();
                bookingConfirmation.setBoookingPassengers(readPassengers(numberOfPassengers));
                BookingConfirmation.bookingRecords.add(bookingConfirmation);
            }


            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Passenger> readPassengers(int numberOfPassengers) throws FileNotFoundException {
        ArrayList<Passenger> passengers = new ArrayList<>();
        try {
            File file = new File("bookingRecords.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] bookingDetails  = line.split(",");
                for (int i = 1; i <= numberOfPassengers; i++){
                    if(i == 1) {
                        passengers.add( new Passenger(

                                bookingDetails[19], bookingDetails[20], bookingDetails[21],
                                bookingDetails[22], bookingDetails[23], bookingDetails[24]));
                    } else {
                        passengers.add(new Passenger(
                                bookingDetails[19 + ((i - 1) * 6)], bookingDetails[20 + ((i - 1) * 6)], bookingDetails[21 + ((i - 1) * 6)],
                                bookingDetails[22 + ((i - 1) * 6)], bookingDetails[23 + ((i - 1) * 6)], bookingDetails[24 + ((i - 1) * 6)]));
                    }
                }
            }


            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passengers;
    }

    public void readBookingNumber() throws FileNotFoundException {
        try {
            File file = new File("bookingNumber.txt");
            Scanner scanner = new Scanner(file);
            if (scanner.hasNext()) {
                BookingConfirmation.lastBookingNumber = scanner.nextInt() + 1;
                System.out.println(BookingConfirmation.lastBookingNumber);
            } else {
                System.out.println("File is empty");
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}