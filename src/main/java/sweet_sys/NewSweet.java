package sweet_sys;
public class NewSweet {

    String id;
    String name;
    String type;
    String price;
    int sale=0;
    
    public NewSweet(String id, String name, String type, String price) {
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

    public static String printsweet(NewSweet sweet) {
        return "ID: " + sweet.getId() + ", Name: " + sweet.getName() + ", Type: " + sweet.getType();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static boolean isOwnerOrSupplier (String email, String password){
        for (Person f : Sweet.getPeopleList()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("Owner") || f.getType().equals("Supplier"))) {
                return true;
            }
        }
        return false;
    }
    public static boolean isAdminOrUser (String email, String password){
        for (Person f : Sweet.getPeopleList()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("ADMIN") || f.getType().equals("USER"))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAdd(String id){
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
