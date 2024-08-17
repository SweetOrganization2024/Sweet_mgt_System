package sweetSys;


public class newSweet {

    String id_of_sweet;
    String name_of_sweet;
    String type_of_sweet;
    String price_of_sweeet;
    int sale=0;

    public newSweet(String id, String name, String type, String price) {
        this.id_of_sweet = id;
        this.name_of_sweet = name;
        this.type_of_sweet = type;
        this.price_of_sweeet=price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale1) {
        this.sale = sale+sale1;
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



    public String getId_of_sweet() {
        return id_of_sweet;
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

    public static boolean if_the_type_sp_or_owner (String email, String password){
        for (person f : sweet.getList_of_people()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("Owner") || f.getType().equals("Supplier"))) {
                return true;
            }
        }
        return false;
    }
    public static boolean if_the_type_AD_or_USR (String email, String password){
        for (person f : sweet.getList_of_people()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("ADMIN") || f.getType().equals("USER"))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAdd(String id_of_sweet){
        for (newSweet s : sweetSys.sweet.getListOfSweet()) {
            if (s.getId_of_sweet().equals(id_of_sweet)) {
                return true;
            }
        }
        return false;
    }
}