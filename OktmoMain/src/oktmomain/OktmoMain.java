package oktmomain;

import java.util.ArrayList;

public class OktmoMain {
  public static void main(String[] args) {
//    String path = "C:\\Users\\student\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
    String path = "C:\\Users\\Asus\\Desktop\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";
//    String path = "C:\\Users\\Acer\\Downloads\\JavaLab2\\JavaLab2\\Tom1-CFO.txt";

    OktmoData oktmo = OktmoReader.readPlaces(path);
    OktmoAnalyzer analyser = new OktmoAnalyzer(oktmo);
    
//    НП, название которых содержит меньше 6 букв и заканчиваются на -ово
    System.out.println();
    ArrayList<Place> f11 = analyser.getTheFilteredValuesOfTheEleventhCondition();
    f11.forEach((t) -> System.out.println(t.name));

//    НП, которые начинаются и заканчиваются на одну и ту же согласную букву
    System.out.println();
    ArrayList<Place> f8 = analyser.getTheFilteredValuesOfTheEighthCondition();
    f8.forEach((t) -> System.out.println(t.name));
    
//    Сортированные данные
    System.out.println();
    ArrayList<Place> sortedData = analyser.sort();
    sortedData.forEach((t) -> System.out.println(t.name));
  }
}
