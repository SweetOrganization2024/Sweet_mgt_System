package sweetSys;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Userfile {
    public static List<person> users = new ArrayList<>();

    public static final String FILE_NAME = "userfile.txt";

    public Userfile() {
    }

    public void readUsers(String fileName) {
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
            e.printStackTrace();
        }
    }

    public static List<person> getUsers() {
        return users;
    }
}

