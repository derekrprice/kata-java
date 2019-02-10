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

        // Output the paper products.
        for (Product product : catalog.getProductsByCategory(".*paper.*")) {
            // TODO: Output Product # and Price.
            System.out.println(product.getDescription());
        }
    }

}