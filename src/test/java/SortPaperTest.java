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
    void testRowCount() {
        Assertions.assertEquals(10, catalog.size());
    }

    @Test
    void testCountMatched() {
        Assertions.assertEquals(4, catalog.getProductsByCategory(".*paper.*").size());
    }

    @Test
    void testCountUnmatched() {
        Assertions.assertEquals(0, catalog.getProductsByCategory(".*space\\s*ship.*").size());
    }
}