import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;
import java.util.List;

enum UOM {
    BX, DZ, EA, PK
}
public class Product {
    private int productNum;
    private String description;
    private List<String> categories;
    private UOM unitOfMeasure;
    private double price;
    private int discount;

    public Product(CSVRecord row) {
        productNum = Integer.parseInt(row.get(0));
        description = row.get(1);
        categories = Arrays.asList(row.get(2).split(","));
        unitOfMeasure = UOM.valueOf(row.get(3));
        price = Double.parseDouble(row.get(4));
        if (!row.get(5).isEmpty()) {
            discount = Integer.parseInt(row.get(5).replaceAll("%$", ""));
        }
    }

    public int getProductNum() {
        return productNum;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public UOM getUnitOfMeasure() {
        return unitOfMeasure;
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
}
