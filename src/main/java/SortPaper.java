import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SortPaper {
    private static SortedMap<Double, Product> products;

    public static void main (String[] args) {
        if (args.length < 1) {
            System.err.print("File name is a required argument.");
            System.exit(2);
        }

        products = new TreeMap<>();
        for (String fileName : args) {
            SortPaper.readCSV(fileName, row -> { Product p = new Product(row); products.put(p.getPrice(), p); });
        }

        // Extract the list of production with a category containing "paper".
        List<Product> paperProducts = products.entrySet().stream()
                .map(e -> e.getValue())
                .filter(
                        p -> !p.getCategories().stream()
                                .filter(c -> c.matches("paper"))
                                .collect(Collectors.toSet())
                                .isEmpty()
                )
                .collect(Collectors.toList());

        for (Product product : paperProducts) {
            System.out.println(product.getDescription());
        }
    }

    public static void readCSV(String fileName, CSVRowOperator processRow) {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            int row = 0;
            for (CSVRecord csvRecord : csvParser) {
                if (row++ == 0) continue;
                processRow.op(csvRecord);
            }
        } catch (IOException e ) {

        }
    }
}