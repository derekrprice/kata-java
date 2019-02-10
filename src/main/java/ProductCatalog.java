import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProductCatalog {
    /**
     * The catalog of existing products.  Read from the CSV inputs.
     */
    private SortedMap<Double, Product> catalog;

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
        return Collections.unmodifiableSet(catalog.entrySet());
   }

   public int size() {
        return catalog.size();
   }

   private void init() {
        catalog = new TreeMap<>();
   }

   private void addProductFromFileRow(CSVRecord productData) {
       try {
           Product product = new Product(productData);
           catalog.put(product.getPrice(), product); }
       catch (Exception e) {
           System.err.println("Failed to parse" + productData + " because " + e);
       }
   }
}
