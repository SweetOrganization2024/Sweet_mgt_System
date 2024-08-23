package sweetSys;
import java.util.ArrayList;

public class DiscountManager {
    private String id;
    private String description;
    private double percentage;
    private String start;
    private String end;
    private static ArrayList<DiscountManager> discounts = new ArrayList<>();

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public static ArrayList<DiscountManager> getDiscounts() {
        return discounts;
    }

    public DiscountManager(String id, String description, double percentage) {
        this.id = id;
        this.description = description;
        this.percentage = percentage;
    }
    public static void addDiscount(DiscountManager D) {
        discounts.add(D);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public double getPercentage() {
        return percentage;
    }

    public static double applyDiscountToProduct(String id ,DiscountManager d) {
        double dis=0.0;
        double originalPrice;
        double per =d.getPercentage();
        for(newSweet n : sweet.getListOfSweet()){
            if(n.getId_of_sweet().equals(id)){
                originalPrice=Double.parseDouble(n.getPrice());
                dis =originalPrice * (per / 100);
                n.setPrice(String.format("%.2f", dis));
                break;
            }
        }
        return dis;
    }
}
