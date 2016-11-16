package oktmomain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
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
  
  public void findMostPopularPlaceName(String regionName) {
    OktmoGroup region = getGroupByName(regionName).get();
    if ((region != null) && region.level == OktmoGroup.OktmoLevel.REGION) {
      long div_pattern = region.code / 1000_000;
      long mod_pattern = region.code % 1000_000;
      oktmo
          .groups
          .keySet()
          .stream()
          .filter((Long k) -> {
            return (k / 1000_000 == div_pattern) && (k % 1000_000 != mod_pattern);
          })
          .forEach((Long k) -> {
            long pattern = oktmo.groups.get(k).code / 1000;
            long raiting = oktmo
                .data
                .stream()
                .filter((Place p) -> p.code / 1000_000 == pattern)
                .count();
            System.out.println(k + ": " + raiting);
          });
    }
  }

  /**
   *
   * @param regionName
   */
  public void printCountStatusesInRegion(String regionName){
    OktmoGroup region = getGroupByName(regionName).get();
    if ((region != null) && region.level == OktmoGroup.OktmoLevel.REGION) {
      long pattern = region.code / 1000_000;
      oktmo.allStatuses
        .forEach((String s) -> {
          long count = oktmo
            .data
            .stream()
            .filter((Place d) -> d.status.equals(s) && (d.code/1000_000_000 == pattern))
            .count();
          System.out.println(s + ": " + count);
        });
    }
  }
  
  private Optional<OktmoGroup> getGroupByName(String name) {
    return oktmo
        .groups
        .values()
        .stream()
        .filter((OktmoGroup t) -> t.name.equals(name))
        .findFirst();
  };
}


