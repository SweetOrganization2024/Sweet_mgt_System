package sweetSys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;



public class Userfile {
    protected static List<person> users = new ArrayList<>();

    public static final String FILE_NAME = "userfile.txt";
    private static final Logger LOGGER = Logger.getLogger(Userfile.class.getName());


    private public Userfile() {
        //constr
    }
    public static boolean emailIsRegisted (String email){
        for (person p : Userfile.getUsers()) {
            if (p.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    public static void readUsers(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String firstName = parts[0].trim();
                    String lastName = parts[1].trim();
                    String userEmail = parts[2].trim();
                    String userPassword = parts[3].trim();
                    String userType = parts[4].trim();
                    users.add(new person(userEmail, userPassword, userType, firstName, lastName));
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred", e);
        }
    }

    public static List<person> getUsers() {
        return users;
    }
}
