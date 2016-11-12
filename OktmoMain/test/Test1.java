import oktmomain.OktmoData;
import oktmomain.OktmoReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test1 {
//    String path = "C:\\Users\\student\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
    String path = "C:\\Users\\Asus\\Desktop\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
    OktmoData data;
    @Before
    public void initialize() {
        data = OktmoReader.readPlaces(path);
    }
    @Test
    public void testReader() {
        Assert.assertEquals(86604101146l, data.data.get(9).getCode());
    }
}
