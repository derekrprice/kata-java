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
    public static void main (String[] args) {
        if (args.length < 1) {
            System.err.print("File name is a required argument.");
            System.exit(2);
        }

        for (String fileName : args) {
            List<CSVRecord> products = SortPaper.getCSV(fileName);
        }
    }

    public static List<CSVRecord> getCSV (String fileName) {
        List<CSVRecord> out = new ArrayList<CSVRecord>();
        try (
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                out.add(csvRecord);
            }
        } catch (IOException e ) {

        }
        return out;
    }
}