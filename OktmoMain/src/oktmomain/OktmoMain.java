package oktmomain;

public class OktmoMain {
  public static void main(String[] args) {
//    String path = "C:\\Users\\student\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
//    String path = "C:\\Users\\Asus\\Desktop\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
    String path = "C:\\Users\\Acer\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
    OktmoData data = OktmoReader.readPlaces(path);
    data.printCodes();
  }
}
