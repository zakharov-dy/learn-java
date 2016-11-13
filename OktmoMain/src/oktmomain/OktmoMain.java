package oktmomain;

import java.util.ArrayList;

public class OktmoMain {
  public static void main(String[] args) {
//    String path = "C:\\Users\\student\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
//    String path = "C:\\Users\\Asus\\Desktop\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
    String path = "C:\\Users\\Acer\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
    OktmoData data = OktmoReader.readPlaces(path);
//    ArrayList<Place> printFilterValues = data.printFilterValues();
//    printFilterValues.forEach((t) -> System.out.println(t.name));
    ArrayList<Place> fV = data.getTheFilteredValuesOfTheEighthCondition();
    fV.forEach((t) -> System.out.println(t.name));
//    data.sortData();
//    data.printFilterValues();
//    data.printCodes();
  }
}
