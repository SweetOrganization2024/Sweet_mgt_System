package sweetSys;


public class newSweet {

    String id;
    String name;
    String type;
    String price;
    int sale=0;

    public newSweet(String id, String name, String type, String price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
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
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public String getId_of_sweet() {
        return id;
    }


    public String getName_of_sweet() {
        return name;
    }

    public void setName_of_sweet(String name) {
        this.name = name;
    }

    public String getType_of_sweet() {
        return type;
    }

    public void setType_of_sweet(String type) {
        this.type = type;
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

    public static boolean isAdd(String id){
        for (newSweet s : sweetSys.sweet.getListOfSweet()) {
            if (s.getId_of_sweet().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
