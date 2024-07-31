package sweetSys;

import java.util.ArrayList;

public class newSweet {
    static ArrayList<newSweet> listOfSweet = new ArrayList<>();
    String id_of_sweet;
    String name_of_sweet;
    String type_of_sweet;
    String price_of_sweeet;

    public newSweet(String id, String name, String type, String price) {
        this.id_of_sweet = id;
        this.name_of_sweet = name;
        this.type_of_sweet = type;
        this.price_of_sweeet=price;
    }
    public static String printsweet(newSweet sweet) {
        return "ID: " + sweet.getId_of_sweet() + ", Name: " + sweet.getName_of_sweet() + ", Type: " + sweet.getType_of_sweet();
    }

    public String getPrice() {
        return price_of_sweeet;
    }

    public void setPrice(String price) {
        this.price_of_sweeet = price;
    }

    public static void deletesweet(newSweet ss) {
        listOfSweet.remove(ss);
    }

    public String getId_of_sweet() {
        return id_of_sweet;
    }

    public void setId_of_sweet(String id_of_sweet) {
        this.id_of_sweet = id_of_sweet;
    }

    public String getName_of_sweet() {
        return name_of_sweet;
    }

    public void setName_of_sweet(String name_of_sweet) {
        this.name_of_sweet = name_of_sweet;
    }

    public String getType_of_sweet() {
        return type_of_sweet;
    }

    public void setType_of_sweet(String type_of_sweet) {
        this.type_of_sweet = type_of_sweet;
    }

    public static ArrayList<newSweet> getListOfSweet() {
        return listOfSweet;
    }

    public static void addsweet(newSweet ss) {
        listOfSweet.add(ss);
    }
}