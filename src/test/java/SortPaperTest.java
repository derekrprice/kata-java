import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortPaperTest {
    @Test
    void testHeaders() {
        List<CSVRecord> csvRecords = SortPaper.getCSV("Catalog.csv");
        Assertions.assertEquals("part #", csvRecords.get(0).get(0));
        Assertions.assertEquals("Description", csvRecords.get(0).get(1));
        Assertions.assertEquals("Category", csvRecords.get(0).get(2));
        Assertions.assertEquals("UOM", csvRecords.get(0).get(3));
        Assertions.assertEquals("Price", csvRecords.get(0).get(4));
        Assertions.assertEquals("Discount", csvRecords.get(0).get(5));
    }
}