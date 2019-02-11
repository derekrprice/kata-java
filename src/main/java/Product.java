import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;
import java.util.List;

enum UOM {
    BX, DZ, EA, PK
}
public class Product implements Comparable {
    private int productNum;
    private String description;
    private List<String> categories;
    private UOM unitOfMeasure;
    private double price;
    private int discount;

    public Product(CSVRecord row) {
        productNum = Integer.parseInt(row.get("part #"));
        description = row.get("Description");
        categories = Arrays.asList(row.get("Category").split(","));
        unitOfMeasure = UOM.valueOf(row.get("UOM"));
        price = Double.parseDouble(row.get("Price"));
        if (!row.get("Discount").isEmpty()) {
            discount = Integer.parseInt(row.get(5).replaceAll("%$", ""));
        }
    }

    public int getPartNum() {
        return productNum;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getUnitOfMeasure() {
        String unit;
        switch (unitOfMeasure) {
            case BX:
                unit = "Box";
                break;
            case DZ:
                unit = "Dozen";
                break;
            case EA:
                unit = "Each";
                break;
            case PK:
                unit = "Pack";
                break;
            default:
                throw new IllegalArgumentException("Unrecognized Unit of MEasure: " + unitOfMeasure);
        }
        return unit;
    }

    public double getOriginalPrice() {
        return price;
    }

    public double getPrice() {
        return Math.round(price * (100 - discount)) / 100.0;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Product) {
            Product p = (Product) o;
            return Integer.compare(productNum, p.getPartNum());
        } else {
            throw new IllegalArgumentException("Product may only be compared to other products.");
        }
    }

    public String getSummary() {
        return getPartNum() + ", " + getDescription() + ", " + getFriendlyPrice();
    }

    private String getFriendlyPrice() {
        return "$" + getPrice() + "/" + getUnitOfMeasure();
    }
}
