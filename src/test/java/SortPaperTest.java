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
    void testCountPaper() {
        Assertions.assertEquals(4, catalog.getProductsByCategory(".*paper.*").size());
    }

}