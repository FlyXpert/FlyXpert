package flyxpert.flyxpert;

import java.io.File;
import java.util.Scanner;

public class Reader {
    public void readAllFiles() {
        readAFile();
        readUsers();
    }

    // This an example for reading data from a file (Remove this and add your own functions to read Users, Flights, etc.)
    public void readAFile() {
        // File file = new File("");
        // Scanner scan = new Scanner(file);
        // Do Reading Here!
        // scan.close();
    }

    public void readUsers() {
        try {
            File file = new File("users.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String user = scan.nextLine();
                System.out.println(user);
                String[] splits = user.split(" ");
                 User.userList.add(new User(splits[0], splits[1], splits[2]));
            }
            scan.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
