package flyxpert.flyxpert;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
    public void writeToAllFiles() throws IOException {
        writeUsers();
    }

    // This an example for writing data to a file (Remove this and add your own functions to write Users, Flights, etc.)
    public void writeToAFile() {
        // PrintWriter writer = new PrintWriter("");
        // writer.println();
        // Do writing here!
        // writer.close();
    }

    public void writeUsers() throws IOException {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("users.txt"));
            for(User user : User.userList) {
                //System.out.println(user.getUserName() + ' ' + user.getEmail() + ' ' + user.getPassword());
                writer.println(user.getUserName() + ' ' + user.getEmail() + ' ' + user.getPassword());
            }
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
