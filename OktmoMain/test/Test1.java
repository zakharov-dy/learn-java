import java.util.Collection;
import oktmomain.OktmoData;
import oktmomain.OktmoReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test1 {
//    String path = "C:\\Users\\student\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
//    String path = "C:\\Users\\Asus\\Desktop\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
  String path = "C:\\Users\\Acer\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
  OktmoData oktmo;
  OktmoData soktmo;
  @Before
  public void initialize() {
  }
  @Test
  public void testReaderReadPlaces() {
    long timeout = System.currentTimeMillis();
    oktmo = OktmoReader.readPlaces(path);
    timeout = System.currentTimeMillis() - timeout;
    System.out.println("first method: " + timeout);
    System.out.println(oktmo.data.size());
//      Просмотр 10-го
    Assert.assertEquals(86604101146l, oktmo.data.get(9).getCode());
//      Просмотр последнего
    Assert.assertEquals(11851000001l, oktmo.data.get(oktmo.data.size()-1).getCode());
  }
  @Test
  public void testReaderReadPlacesViaSplit() {
    long timeout = System.currentTimeMillis();
    soktmo = OktmoReader.readPlacesViaSplit(path);
    timeout = System.currentTimeMillis() - timeout;
    System.out.println("second method: " + timeout);
    System.out.println(soktmo.data.size());
//      Просмотр 10-го
    Assert.assertEquals(86604101146l, soktmo.data.get(9).getCode());
//      Просмотр последнего
    Assert.assertEquals(11851000001l, soktmo.data.get(soktmo.data.size() - 1).getCode());
  }
  @Test
  public void testStatuses() {
    oktmo = OktmoReader.readPlaces(path);
    Assert.assertTrue(oktmo.allStatuses.contains("д"));
  }
  @Test
  public void testEqualsPlases() {
    oktmo = OktmoReader.readPlaces(path);
    soktmo = OktmoReader.readPlacesViaSplit(path);
    Assert.assertTrue(oktmo.data.equals(soktmo.data));
  }
}
