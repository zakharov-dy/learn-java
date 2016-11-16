package oktmomain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class OktmoAnalyzer {
  OktmoData oktmo;

  public OktmoAnalyzer(OktmoData o) {
    oktmo = o;
  }
  
  public ArrayList<Place> sort() {
    ArrayList<Place> sortedPlaces = (ArrayList<Place>) oktmo.data.clone();
    Comparator<Place> byName = (Place o1, Place o2) -> o1.name.compareTo(o2.name);
    Collections.sort(sortedPlaces, byName);
    return sortedPlaces;
  }

  private ArrayList<Place> getTheFilteredData(String s) {
    return (ArrayList<Place>) oktmo.data
        .stream()
        .filter((Place t) -> t.name.matches(s))
        .collect(Collectors.toList());
  }
  
  public ArrayList<Place> getTheFilteredValuesOfTheEighthCondition() {
    return getTheFilteredData("^\\D{0,3}ово$");
  }

  public ArrayList<Place> getTheFilteredValuesOfTheEleventhCondition() {
    return getTheFilteredData("^((?ui)[цкнгшщзхвпрлджчсмтб]).*(\\1)$");
  }
  
//  public findMostPopularPlaceName(String regionName)
  public void printCountStatusesInRegion(){
    oktmo
        .allStatuses
        .forEach((t) -> oktmo
            .data
            .stream()
            .filter((Place d) -> d.status.equals(t))
            .map((Place d) -> System.out.println(d.name);)
        );
  }
}


