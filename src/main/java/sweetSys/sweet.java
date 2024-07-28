package sweetSys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class sweet {
    public static List<person> list_of_people = new ArrayList<>();
    public boolean is_loggin = false;

    public sweet() {
        // Load users when object is created
        loadUsers();
    }

    public void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Userfile.FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String firstName = parts[0].trim();
                    String lastName = parts[1].trim();
                    String userEmail = parts[2].trim();
                    String userPassword = parts[3].trim();
                    String userType = parts[4].trim();
                    list_of_people.add(new person(userEmail, userPassword, userType, firstName, lastName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<person> getList_of_people() {
        return list_of_people;
    }

    public void setToList(person p) {
        list_of_people.add(p);
    }
}

