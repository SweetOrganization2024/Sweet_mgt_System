package sweetSys;
import java.util.ArrayList;
import java.util.List;
public class DiscountManager {
    private String id;
    private final String description;
    private final double percentage;
    private static final List<DiscountManager> discounts = new ArrayList<>();

    public DiscountManager(String id, String description, double percentage) {
        this.id = id;
        this.description = description;
        this.percentage = percentage;
    }

    public void setStart(String start) {
        // Implementation for setting the start date
    }

    public void setEnd(String end) {
        // Implementation for setting the end date
    }

    public static List<DiscountManager> getDiscounts() { // Returning List type
        return discounts;
    }

    public static void addDiscount(DiscountManager discount) {
        discounts.add(discount);
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

    public static double applyDiscountToProduct(String id, DiscountManager d) {
        double dis = 0.0;
        double originalPrice;
        double per = d.getPercentage();
        for (newSweet n : sweet.getListOfSweet()) {
            if (n.getId().equals(id)) {
                originalPrice = Double.parseDouble(n.getPrice());
                dis = originalPrice * (per / 100);
                n.setPrice(String.format("%.2f", dis));
                break;
            }
        }
        return dis;
    }
}
