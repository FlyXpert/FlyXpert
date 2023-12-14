package flyxpert.flyxpert;


import java.util.ArrayList;
import java.util.Date;

public class Passenger {

        private String firstName;
        private String middleName;
        private String lastName;
        private Date dateOfBirth = new Date();
        private String phoneNumber;
        private Seat seat;
        public static ArrayList<Passenger> passengers = new ArrayList<>();


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

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public Seat getSeat() {
                return seat;
        }

}