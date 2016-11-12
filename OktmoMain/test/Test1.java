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
  @Before
  public void initialize() {
    oktmo = OktmoReader.readPlaces(path);
  }
  @Test
  public void testReader() {
//      Просмотр 10-го
    Assert.assertEquals(86604101146l, oktmo.data.get(9).getCode());
//      Просмотр последнего
    Assert.assertEquals(11851000001l, oktmo.data.get(oktmo.data.size()-1).getCode());
  }
}
