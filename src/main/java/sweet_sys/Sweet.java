package sweet_sys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Sweet {
    public static List<Person> peopleList = new ArrayList<>();
    public static  List<NewSweet> listOfSweet = new ArrayList<>();
     private static Sweet instance;
    private static final Logger logger = Logger.getLogger(Sweet.class.getName());


    public static Sweet getInstance() {
        if (instance == null) {
            instance = new Sweet();
        }
        return instance;
    }
    
    public static List<NewSweet> getListOfSweet() {
        return listOfSweet;
    }
    public static List<Person> getPeopleList() {
        return peopleList;
    }
    public static boolean isLoggin = false;

    public Sweet() {
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
                    peopleList.add(new Person(userEmail, userPassword, userType, firstName, lastName));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
    }

  public static void setToList(Person p) {
        peopleList.add(p);
    }

    public static void addsweet(NewSweet sweet) {
        listOfSweet.add(sweet);
    }

    public static void deletesweet(NewSweet ss) {
        listOfSweet.remove(ss);
    }

    public static boolean searchID(String id) {
        boolean idSearch = false;
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getId().equals(id)) {
                idSearch = true;
                break;
            }
        }

        return idSearch;
    }

    public static void printSweetId(String id_of_sweet) {
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getId().equals(id_of_sweet)) {
                logger.info("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());

                break;
            }
        }
    }

    public static boolean searchName(String name) {
        boolean nameSearch = false;
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getName().equals(name)) {
                nameSearch = true;
                break;
            }
        }
        return nameSearch;
    }

    public static void deleteperson(Person p){
        peopleList.remove(p);
    }
    public static void printSweetname(String name) {
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getName().equals(name)) {
                logger.info("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());
            }
        }

    }

    public static boolean searchNameType(String name, String type) {
        boolean nametype_search1 = false;
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getName().equals(name) && (s.getType().equals(type))) {
                nametype_search1 = true;
                break;
            }
        }
        return (nametype_search1);
    }

    public static void printTypeName(String name, String type) {
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getName().equals(name) && s.getType().equals(type)) {
                logger.info("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());
                break;
            }
        }
    }

    public static boolean searchNameId(String name, String id) {
        boolean nameidSearch = false;
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getName().equals(name) && (s.getId().equals(id))) {
                nameidSearch = true;
                break;
            }
        }
        return (nameidSearch);
    }

    public static boolean secrchAll(String name, String id, String type) {
        boolean all = false;
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getName().equals(name) && (s.getId().equals(id)) && (s.getType().equals(type))) {
                all = true;
                break;
            }
        }
        return all;
    }
    public static void priceminMax(String min1, String max1) {
        try {
            int min = Integer.parseInt(min1.trim());
            int max = Integer.parseInt(max1.trim());
            for (NewSweet s : Sweet.getListOfSweet()) {
                String priceString = s.getPrice().trim();
                try {
                    int myprice = Integer.parseInt(priceString);
                    if (myprice > min && myprice < max) {
                        logger.info("Name : " + s.getName() +
                                " Id : " + s.getId() +
                                " Type : " + s.getType() +
                                " Price : " + s.getPrice());
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid price format: " + priceString);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid min or max value: " + e.getMessage());
        }
    }
    public static boolean validSweet(String name, String id, String type) {
        boolean valid = false;
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getName().equals(name) && s.getId().equals(id) && s.getType().equals(type) ) {
                valid=true;
                break;}
        }
        return valid;
    }
    public static boolean validPeople(String email , String pass) {
        boolean isUserRegistered = false;
        for (Person f : Sweet.getPeopleList()) {
            if (f.getEmail().equals(email) && f.getPass().equals(pass) && f.getType().equals("USER")) {
                isUserRegistered = true;
                break;
            }
        }
        return isUserRegistered;
    }


    public static void printNameId(String id , String nameOfSweet) {
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getName().equals(nameOfSweet) && (s.getId().equals(id))) {
                logger.info("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());
                break;
            }
        }
    }

    public static void printNameIdType(String id , String nameOfSweet , String type) {
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getName().equals(nameOfSweet) && (s.getId().equals(id)) && (s.getType().equals(type))) {
                logger.info("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());

                break;
            }
        }
    }

    public static String getThePrice(String name, String type) {
        String result= "";
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getName().equals(name) && s.getType().equals(type)) {
                result = s.getPrice();
                break;
            }
        }
        return result;
    }

    public static boolean idSupOrOwner(String email,String pass){
        for (Person p : Sweet.getPeopleList()){
            if (p.getEmail().equals(email)  && p.getPass().equals(pass) && (p.getType().equals("Supplier") || p.getType().equals("Owner"))){
                return true;
            }
        }
        return false;
    }

    public static Person retperson(String email, String pass){
        for (Person p : getPeopleList()){
            if (p.getEmail().equals(email) && p.getPass().equals(pass))
                return p;
        }
        return null;
    }
    public static boolean idSupOrOwnerorAD(String email,String pass){
        for (Person p : Sweet.getPeopleList()){
            if (p.getEmail().equals(email)  && p.getPass().equals(pass) && (p.getType().equals("Supplier") || p.getType().equals("Owner") || p.getType().equals("ADMIN"))){
                return true;
            }
        }
        return false;
    }

}
