package sweetSys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class sweet {
    public static final List<person> peopleList = new ArrayList<>();
    public static final List<newSweet> listOfSweet = new ArrayList<>();
    private static sweet instance;
    private static final Logger logger = Logger.getLogger(sweet.class.getName());

    private sweet() {
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
                    peopleList.add(new person(userEmail, userPassword, userType, firstName, lastName));
                }
            }
        } catch (IOException e) {
            logger.severe("Error reading user data: " + e.getMessage());
        }
    }

    public static synchronized sweet getInstance() {
        if (instance == null) {
            instance = new sweet();
        }
        return instance;
    }

    public static List<newSweet> getListOfSweet() {
        return listOfSweet;
    }

    public static void setListOfSweet(List<newSweet> newList) {
        throw new UnsupportedOperationException("listOfSweet is final and cannot be modified.");
    }

    public static boolean is_loggin = false;

    public static void setToList(person p) {
        peopleList.add(p);
    }

    public static void addsweet(newSweet sweet) {
        listOfSweet.add(sweet);
    }

    public static void deletesweet(newSweet sweet) {
        listOfSweet.remove(sweet);
    }

    public static boolean Search_ID(String id) {
        for (newSweet s : listOfSweet) {
            if (s.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static void print_SweetId(String id_of_sweet) {
        for (newSweet s : listOfSweet) {
            if (s.getId().equals(id_of_sweet)) {
                logger.info("Name: " + s.getName() + ", Id: " + s.getId() + ", Type: " + s.getType() + ", Price: " + s.getPrice());
                break;
            }
        }
    }

    public static boolean Search_name(String name) {
        for (newSweet s : listOfSweet) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void deleteperson(person p) {
        peopleList.remove(p);
    }

    public static void print_Sweetname(String name) {
        for (newSweet s : listOfSweet) {
            if (s.getName().equals(name)) {
                logger.info("Name: " + s.getName() + ", Id: " + s.getId() + ", Type: " + s.getType() + ", Price: " + s.getPrice());
            }
        }
    }

    public static boolean Search_name_Type(String name, String type) {
        for (newSweet s : listOfSweet) {
            if (s.getName().equals(name) && s.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    public static void Print_Type_name(String name, String type) {
        for (newSweet s : listOfSweet) {
            if (s.getName().equals(name) && s.getType().equals(type)) {
                logger.info("Name: " + s.getName() + ", Id: " + s.getId() + ", Type: " + s.getType() + ", Price: " + s.getPrice());
                break;
            }
        }
    }

    public static boolean Search_name_id(String name, String id) {
        for (newSweet s : listOfSweet) {
            if (s.getName().equals(name) && s.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean Secrch_all(String name, String id, String type) {
        for (newSweet s : listOfSweet) {
            if (s.getName().equals(name) && s.getId().equals(id) && s.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    public static void Pricemin_max(String min1, String max1) {
        try {
            int min = Integer.parseInt(min1.trim());
            int max = Integer.parseInt(max1.trim());

            for (newSweet s : listOfSweet) {
                String priceString = s.getPrice().trim();
                try {
                    int myPrice = Integer.parseInt(priceString);
                    if (myPrice > min && myPrice < max) {
                        logger.info("Name: " + s.getName() + ", Id: " + s.getId() + ", Type: " + s.getType() + ", Price: " + s.getPrice());
                    }
                } catch (NumberFormatException e) {
                    logger.severe("Invalid price format: " + priceString);
                }
            }
        } catch (NumberFormatException e) {
            logger.severe("Invalid min or max value: " + e.getMessage());
        }
    }

    public static boolean validSweet(String name, String id, String type) {
        for (newSweet s : listOfSweet) {
            if (s.getName().equals(name) && s.getId().equals(id) && s.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validPeople(String email, String pass) {
        for (person f : peopleList) {
            if (f.getEmail().equals(email) && f.getPass().equals(pass) && f.getType().equals("USER")) {
                return true;
            }
        }
        return false;
    }

    public static void deletesweet1(String id) {
        List<newSweet> updatedList = new ArrayList<>();
        for (newSweet s : listOfSweet) {
            if (!s.getId().equals(id)) {
                updatedList.add(s);
            }
        }
    }

    public static void Print_name_id(String id, String name_of_sweet) {
        for (newSweet s : listOfSweet) {
            if (s.getName().equals(name_of_sweet) && s.getId().equals(id)) {
                logger.info("Name: " + s.getName() + ", Id: " + s.getId() + ", Type: " + s.getType() + ", Price: " + s.getPrice());
                break;
            }
        }
    }

    public static void Print_name_id_type(String id, String name_of_sweet, String type) {
        for (newSweet s : listOfSweet) {
            if (s.getName().equals(name_of_sweet) && s.getId().equals(id) && s.getType().equals(type)) {
                logger.info("Name: " + s.getName() + ", Id: " + s.getId() + ", Type: " + s.getType() + ", Price: " + s.getPrice());
                break;
            }
        }
    }

    public static String getThePrice(String name, String type) {
        for (newSweet s : listOfSweet) {
            if (s.getName().equals(name) && s.getType().equals(type)) {
                return s.getPrice();
            }
        }
        return "";
    }

    public static boolean idSupOrOwner(String email, String pass) {
        for (person p : peopleList) {
            if (p.getEmail().equals(email) && p.getPass().equals(pass) && (p.getType().equals("Supplier") || p.getType().equals("Owner"))) {
                return true;
            }
        }
        return false;
    }

    public static person retperson(String email, String pass) {
        for (person p : peopleList) {
            if (p.getEmail().equals(email) && p.getPass().equals(pass)) {
                return p;
            }
        }
        return null;
    }

    public static boolean idSupOrOwnerorAD(String email, String pass) {
        for (person p : peopleList) {
            if (p.getEmail().equals(email) && p.getPass().equals(pass) && (p.getType().equals("Supplier") || p.getType().equals("Owner") || p.getType().equals("ADMIN"))) {
                return true;
            }
        }
        return false;
    }
}
