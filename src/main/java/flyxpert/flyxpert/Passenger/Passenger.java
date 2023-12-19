package flyxpert.flyxpert.Passenger;

import flyxpert.flyxpert.Seat.Seat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Passenger {

        private String firstName;
        private String middleName;
        private String lastName;
        private Date dateOfBirth = new Date();
        private String phoneNumber;
        private String dateOfBirthasAString;
        private Seat seat;
        public static ArrayList<Passenger> passengers = new ArrayList<>();

        public Passenger (){

        }
        public Passenger(String primarySeat, String firstName, String middleName, String lastName, String phoneNumber, String dateOfBirth) {
                this.seat = new Seat();
                this.seat.setPrimaryKey(primarySeat);
                this.firstName = firstName;
                this.middleName = middleName;
                this.lastName = lastName;
                this.dateOfBirthasAString = dateOfBirth;
                this.phoneNumber = phoneNumber;
        }

        public void setSeat(Seat seat) {
                this.seat = seat;
        }

        public void setFirstName(String name) {
                this.firstName = name;
        }

        public void setMiddleName(String name) {
                this.middleName = name;
        }

        public void setLastName(String name) { this.lastName = name; }

        public void setDateOfBirth(Date DOB) {
                this.dateOfBirth = DOB;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }


        public String getFirstName() { return firstName; }

        public String getMiddleName() {
                return middleName;
        }

        public String getLastName() {
                return lastName;
        }

        public Date getDateOfBirth() {
                return dateOfBirth;
        }
        public String getDateOfBirthAsAString(){
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                return formatter.format(dateOfBirth.toString());
        }
        public String getPhoneNumber() {
                return phoneNumber;
        }

        public Seat getSeat() {
                return seat;
        }

}