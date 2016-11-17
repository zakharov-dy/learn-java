package oktmomain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;
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
    final Pattern re = Pattern.compile(s);//, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
   
    return (ArrayList<Place>) oktmo.data
        .stream()
        .filter((Place t) -> re.matcher(t.name).matches())
        .collect(Collectors.toList());
        
  }
  
  public ArrayList<Place> getTheFilteredValuesOfTheEighthCondition() {
    return getTheFilteredData("^\\D{0,3}ово$");
  }

  public ArrayList<Place> getTheFilteredValuesOfTheEleventhCondition() {
    return getTheFilteredData("^(?ui)([цкнгшщзхвпрлджчсмтб]).*(\\1)$");
  }
  
  /**
   * Вывод районов региона с количеством НП у каждого района
   * @param regionName
   */
  public void countPlacesByEveryRayonsForRegion(String regionName) {
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
//          TODO: Убрать тут большой цикл
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
   * Вывод статусов у региона с количеством НП по каждому статусу
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
  
  public void findMostPopularPlaceName(String regionName) {
    OktmoGroup region = getGroupByName(regionName).get();
    long pattern = region.code / 1000_000;
    ArrayList<Place> regionPlaces = (ArrayList<Place>) oktmo
        .data
        .stream()
        .filter((Place t) -> t.code / 1000_000_000 == pattern)
        .collect(Collectors.toList());
    HashMap<String, Long> countSamePlaces = new HashMap <String, Long>();
    regionPlaces.forEach((Place t) -> {
      if (countSamePlaces.containsKey(t.name)) {
        countSamePlaces.put(t.name, countSamePlaces.get(t.name) + 1);
      }
      else {
        countSamePlaces.put(t.name, 1l);
      }
    });
    countSamePlaces.forEach((String s, Long l) -> System.out.println(s + " " + l));
    
//    Comparator<String> byName = (String s1, String s2) -> countSamePlaces.get(s1) > countSamePlaces.get(s2) ? 1 : -1;
//    ArrayList<String> popular = (ArrayList<String>) countSamePlaces.keySet().stream().sorted(byName).collect(Collectors.toList());
//    popular.forEach(System.out::println);
  }
  
  /**
   *
   * @param regionName
   * @return Optional<OktmoGroup> - регион по имени
   */
  private Optional<OktmoGroup> getGroupByName(String name) {
    return oktmo
        .groups
        .values()
        .stream()
        .filter((OktmoGroup t) -> t.name.equals(name))
        .findFirst();
  };
}


