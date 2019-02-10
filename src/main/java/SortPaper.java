import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SortPaper {
    private static List<Product> products;

    public static void main (String[] args) {
        if (args.length < 1) {
            System.err.print("File name is a required argument.");
            System.exit(2);
        }

        products = new ArrayList<>();
        for (String fileName : args) {
            SortPaper.readCSV(fileName, row -> products.add(new Product(row)));
        }
    }

    public static void readCSV(String fileName, CSVRowOperator processRow) {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                processRow.op(csvRecord);
            }
        } catch (IOException e ) {

        }
    }
}