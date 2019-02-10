import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCatalog {
    /**
     * The catalog of existing products.  Read from the CSV inputs.
     */
    private SortedSetMultimap<Double, Product> priceIndex;
    private Map<Integer, Product> catalog;

    public ProductCatalog() {
        init();
    }

    public ProductCatalog (String filename) {
        init();
        addProducts(filename);
    }

    public void addProducts(String fileName) {
       try (
               Reader reader = Files.newBufferedReader(Paths.get(fileName));
               CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
       ) {
           // Could get the list of headers here from `headers = csvParser.getHeaderMap();` and do some explicit
           // file format checking.
           for (CSVRecord csvRecord : csvParser) {
               addProductFromFileRow(csvRecord);
           }
       } catch (IOException e ) {
           // This space intentionally left blank.  Just ignore unparsable rows.
       }
   }

   public Set<Map.Entry<Double, Product>> getProductSet() {
        return Collections.unmodifiableSet(priceIndex.entries());
   }

   public int size() {
        return catalog.size();
   }

    /**
     * Extract the list of production with a category or a description matching a regular expression.
     *
     * @param regex    A regular expression used to identify a category.  This will be matched both against the
     *                  category strings and the product description string.
     * @return A list of products matching the regular expresion.
     */
   public List<Product> getProductsByCategory(String regex) {
       // If we swapped out the storage layer here, this could be SQL or whatever.
       // Here, I am just looping over the products in the catalog and extracting
       // any matching the regular expression in the category or the description.
       List<Product> products =
           priceIndex.entries().stream()
           .map(e -> e.getValue())
           .filter(
                   p -> !p.getCategories().stream()
                           .filter(c -> c.toLowerCase().matches(regex))
                           .collect(Collectors.toSet())
                           .isEmpty()
                           || p.getDescription().toLowerCase().matches(regex)
           )
           .collect(Collectors.toList());

       return Collections.unmodifiableList(products);
   }

   private void init() {
       catalog = new TreeMap<>();
       priceIndex = TreeMultimap.create();
   }

   private void addProductFromFileRow(CSVRecord productData) {
       try {
           Product product = new Product(productData);
           addProduct(product);
       } catch (Exception e) {
           System.err.println("Failed to parse" + productData + " because " + e);
       }
   }

   private void addProduct(Product product) {
       if (catalog.containsKey(product.getProductNum())) {
           removeProduct(product);
       }
       priceIndex.put(product.getPrice(), product);
       catalog.put(product.getProductNum(), product);
   }

   private void removeProduct(Product product) {
       priceIndex.remove(catalog.get(product.getProductNum()).getPrice(), catalog.get(product.getProductNum()));
       catalog.remove(product.getProductNum());
   }
}
