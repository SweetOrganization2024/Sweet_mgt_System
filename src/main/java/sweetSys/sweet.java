package sweetSys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class sweet {
    public static List<person> peopleList = new ArrayList<>();
    public static final List<newSweet> listOfSweet = new ArrayList<>();
     private static sweet instance;
    private static final Logger logger = Logger.getLogger(sweet.class.getName());

    public static sweet getInstance() {
        if (instance == null) {
            instance = new sweet();
        }
        return instance;
    }
    

    public static ArrayList<newSweet> getListOfSweet() {
        return listOfSweet;
    }
    public static List<person> getPeopleList() {
        return peopleList;
    }

    public static void setListOfSweet(ArrayList<newSweet> listOfSweet) {
        sweet.listOfSweet = listOfSweet;
    }

    public static boolean is_loggin = false;

    public sweet() {
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
            e.printStackTrace();
        }
    }

    public static List<person> getPeopleList() {
        return peopleList;
    }

    public void setToList(person p) {
        peopleList.add(p);
    }

    public static void addsweet(newSweet sweet) {
        listOfSweet.add(sweet);
    }

    public static void deletesweet(newSweet ss) {
        listOfSweet.remove(ss);
    }

    public static boolean Search_ID(String id) {
        boolean id_search = false;
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getId().equals(id)) {
                id_search = true;
                break;
            }
        }

        return id_search;
    }

    public static void print_SweetId(String id_of_sweet) {
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getId().equals(id_of_sweet)) {
                logger.info("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());

                break;
            }
        }
    }

    public static boolean Search_name(String name) {
        boolean nameSearch = false;
        for (newSweet s : sweet.getListOfSweet()) { // Assuming 'Sweet' is the correct class name
            if (s.getName().equals(name)) {
                nameSearch = true;
                break;
            }
        }
        return nameSearch;
    }


    public static void deleteperson(person p){
        peopleList.remove(p);
    }
    public static void print_Sweetname(String name) {
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name)) {
                logger.info("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());
            }
        }

    }

    public static boolean Search_name_Type(String name, String type) {
        boolean nametype_search1 = false;
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name) && (s.getType().equals(type))) {
                nametype_search1 = true;
                break;
            }
        }
        return (nametype_search1);
    }

    public static void Print_Type_name(String name, String Type) {
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name) && s.getType().equals(Type)) {
                logger.info("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());
                break;
            }
        }
    }

    public static boolean Search_name_id(String name, String id) {
        boolean nameid_Search = false;
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name) && (s.getId().equals(id))) {
                nameid_Search = true;
                break;
            }
        }
        return (nameid_Search);
    }

    public static boolean Secrch_all(String name, String id, String type) {
        boolean all = false;
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name) && (s.getId().equals(id)) && (s.getType().equals(type))) {
                all = true;
                break;
            }
        }
        return all;
    }

    public static void Pricemin_max(String min1, String max1) {
        try {
            int min = Integer.parseInt(min1.trim());
            int max = Integer.parseInt(max1.trim());

            for (newSweet s : sweet.getListOfSweet()) {
                String priceString = s.getPrice().trim();
                try {
                    int Myprice = Integer.parseInt(priceString);
                    if (Myprice > min && Myprice < max) {
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
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name) && s.getId().equals(id) && s.getType().equals(type) ) {
                valid=true;
                break;}

        }
        return valid;
    }
    public static boolean validPeople(String email , String pass) {
        boolean isUserRegistered = false;
        for (person f : sweet.getPeopleList()) {
            if (f.getEmail().equals(email) && f.getPass().equals(pass) && f.getType().equals("USER")) {
                isUserRegistered = true;
                break;
            }
        }
        return isUserRegistered;
    }

    public static void deletesweet1(String ss) {
        List<newSweet> updatedList = new ArrayList<>();
        for (newSweet s : sweetSys.sweet.getListOfSweet()) {
            if (!s.getId().equals(ss)) {
                updatedList.add(s);
            }
        }
        sweetSys.sweet.setListOfSweet((ArrayList<newSweet>) updatedList);
    }
    public static void Print_name_id(String id , String name_of_sweet) {
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name_of_sweet) && (s.getId().equals(id))) {
                logger.info("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());
                break;
            }
        }
    }

    public static void Print_name_id_type(String id , String name_of_sweet , String type) {
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name_of_sweet) && (s.getId().equals(id)) && (s.getType().equals(type))) {
                logger.info("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());

                break;
            }
        }
    }

    public static String getThePrice(String name, String type) {
        String result= "";
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name) && s.getType().equals(type)) {
                result = s.getPrice();
                break;
            }
        }
        return result;
    }

    public static boolean idSupOrOwner(String email,String pass){
        for (person p : sweet.getPeopleList()){
            if (p.getEmail().equals(email)  && p.getPass().equals(pass) && (p.getType().equals("Supplier") || p.getType().equals("Owner"))){
                return true;
            }
        }
        return false;
    }

    public static person retperson(String email,String pass){
        for (person p : getPeopleList()){
            if (p.getEmail().equals(email) && p.getPass().equals(pass))
                return p;
        }
        return null;
    }
    public static boolean idSupOrOwnerorAD(String email,String pass){
        for (person p : sweet.getPeopleList()){
            if (p.getEmail().equals(email)  && p.getPass().equals(pass) && (p.getType().equals("Supplier") || p.getType().equals("Owner") || p.getType().equals("ADMIN"))){
                return true;
            }
        }
        return false;
    }

}
