import oktmomain.OktmoReader;
import org.junit.Before;

public class Test {
    @Before
    public void initialize() {
    }
    @Test
    public void testReader() {
        String path = "C:\\Users\\student\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
        OktmoReader.readPlaces(path);
    }
}
