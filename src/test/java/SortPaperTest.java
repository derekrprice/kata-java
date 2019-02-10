import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SortPaperTest {
    private List<CSVRecord> csvRecords;

    @BeforeEach
    public void setUp() {
        csvRecords = new ArrayList<>();
        SortPaper.readCSV("Catalog.csv", row -> csvRecords.add(row));
    }

    @Test
    void testHeaders() {
        Assertions.assertEquals("part #", csvRecords.get(0).get(0));
        Assertions.assertEquals("Description", csvRecords.get(0).get(1));
        Assertions.assertEquals("Category", csvRecords.get(0).get(2));
        Assertions.assertEquals("UOM", csvRecords.get(0).get(3));
        Assertions.assertEquals("Price", csvRecords.get(0).get(4));
        Assertions.assertEquals("Discount", csvRecords.get(0).get(5));
    }

    @Test
    void testRowCount() {
        Assertions.assertEquals(13, csvRecords.size());
    }
}