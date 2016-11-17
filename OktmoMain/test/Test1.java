import oktmomain.OktmoData;
import oktmomain.OktmoReader;
import org.junit.Assert;
import org.junit.Test;

public class Test1 {
    String path = "C:\\Users\\student\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
//    String path = "C:\\Users\\Asus\\Desktop\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
//  String path = "C:\\Users\\Acer\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
  OktmoData oktmo;
  OktmoData soktmo;

  @Test
  public void testReader_readPlaces() {
    long timeout = System.currentTimeMillis();
    oktmo = new OktmoData();
    OktmoReader.readPlaces(path, oktmo);
    timeout = System.currentTimeMillis() - timeout;
    System.out.println("first method: " + timeout);
//      Просмотр 10-го
    Assert.assertEquals(86604101146l, oktmo.data.get(9).getCode());
//      Просмотр последнего
    Assert.assertEquals(11851000001l, oktmo.data.get(oktmo.data.size()-1).getCode());
  }
  @Test
  public void testOktmoReader_readPlacesViaSplit() {
    long timeout = System.currentTimeMillis();
    soktmo = new OktmoData();
    OktmoReader.readPlacesViaSplit(path, soktmo);
    timeout = System.currentTimeMillis() - timeout;
    System.out.println("second method: " + timeout);
//      Просмотр 10-го
    Assert.assertEquals(86604101146l, soktmo.data.get(9).getCode());
//      Просмотр последнего
    Assert.assertEquals(11851000001l, soktmo.data.get(soktmo.data.size() - 1).getCode());
  }
  @Test
  public void testStatuses() {
    oktmo = new OktmoData();
    OktmoReader.readPlaces(path, oktmo);
    Assert.assertTrue(oktmo.allStatuses.contains("д"));
  }
  @Test
//  Насколько данные, ч/з регулярку соотвутствуют данным ч/з обычные проверки
  public void testPlace_equals() {
    oktmo = new OktmoData();
    OktmoReader.readPlaces(path, oktmo);
    soktmo = new OktmoData();
  OktmoReader.readPlacesViaSplit(path, soktmo);
    Assert.assertArrayEquals(oktmo.data.toArray(), soktmo.data.toArray());
  }
}