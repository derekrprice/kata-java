import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the main application class for the SortPaper application.  This application loads a product catalog
 * from a list of CSV files, then sorts the list by price, including any discounts, and prints out just the
 * paper products from the catalog.
 */
public class SortPaper {
    public static void main (String[] args) {
        if (args.length < 1) {
            System.err.print("File name is a required argument.");
            System.exit(2);
        }

        // Parse the file.
        ProductCatalog catalog = new ProductCatalog();
        for (String fileName : args) {
            catalog.addProducts(fileName);
        }

        // Extract the list of production with a category containing "paper".
        // If we swapped out the storage layer here, this could be SQL or whatever.
        // Here, I am just looping over the products in the catalog and extracting
        // any with the word "paper" in the category or the description.
        List<Product> paperProducts = catalog.getProductSet().stream()
                .map(e -> e.getValue())
                .filter(
                        p -> !p.getCategories().stream()
                                .filter(c -> c.toLowerCase().matches(".*paper.*"))
                                .collect(Collectors.toSet())
                                .isEmpty()
                             || p.getDescription().toLowerCase().matches(".*paper.*")
                )
                .collect(Collectors.toList());

        // Output the paper products.
        for (Product product : paperProducts) {
            // TODO: Output Product # and Price.
            System.out.println(product.getDescription());
        }
    }

}