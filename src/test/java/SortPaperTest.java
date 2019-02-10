import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SortPaperTest {
    @Test
    void testHello() {
        Assertions.assertEquals("a string", SortPaper.getFile("test"));
    }
}