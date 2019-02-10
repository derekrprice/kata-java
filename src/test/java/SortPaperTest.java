import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class SortPaperTest {
    private ProductCatalog catalog;

    @BeforeEach
    public void setUp() {
        catalog = new ProductCatalog("Catalog.csv");
    }

    @Test
    void testHeaders() {
        //Assertions.assertEquals("part #", csvRecords.get(0).get(0));
        //Assertions.assertEquals("Description", csvRecords.get(0).get(1));
        //Assertions.assertEquals("Category", csvRecords.get(0).get(2));
        //Assertions.assertEquals("UOM", csvRecords.get(0).get(3));
        //Assertions.assertEquals("Price", csvRecords.get(0).get(4));
        //Assertions.assertEquals("Discount", csvRecords.get(0).get(5));
    }

    @Test
    void testRowCount() {
        Assertions.assertEquals(12, catalog.size());
    }
}