package oktmomain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OktmoAnalyzer {
  OktmoData oktmo;
  long most;

  public OktmoAnalyzer(OktmoData o) {
    oktmo = o;
    most = 0;
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
    OktmoGroup region = oktmo.strGroups.get(regionName);
    if ((region != null) && region.level == OktmoGroup.OktmoLevel.REGION) {
      long div_pattern = region.code / 1000_000;
      long mod_pattern = region.code % 1000_000;
      HashMap<Long, Long> groupsRaiting = new HashMap<Long, Long>();
      
      oktmo.groups.forEach((Long t, OktmoGroup u) -> {
        if ((t / 1000_000 == div_pattern) && (t % 1000_000 != mod_pattern)) {
          groupsRaiting.put(t, 1l);
        } 
      });
      oktmo.data.forEach((Place t) -> {
        long key = (t.code / 1000_000) * 1000;
        if (groupsRaiting.containsKey(key)) {
          groupsRaiting.put(key, groupsRaiting.get(key) + 1);
        }
      });
      groupsRaiting.forEach((Long t, Long u) -> {
        System.out.println(t + ": " + u);
      });
    }
  }
  
  /**
   * Вывод статусов у региона с количеством НП по каждому статусу
   *
   * @param regionName
   */
  public void printCountStatusesInRegion(String regionName){
    OktmoGroup region = oktmo.strGroups.get(regionName);
    if ((region != null) && region.level == OktmoGroup.OktmoLevel.REGION) {
      ArrayList<Place> regionPlaces = getPlacesByRegion(region);
      HashMap<String, Long> statusRaiting = new HashMap<String, Long>();
      regionPlaces.stream().forEach((Place t) -> {
        if (statusRaiting.containsKey(t.status)) {
          statusRaiting.put(t.status, statusRaiting.get(t.status) + 1);
        } else {
          statusRaiting.put(t.status, 1l);
        }
      });
      statusRaiting.forEach((String t, Long u) -> {
        System.out.println(t + ": " + u);
      });
    }
  }
  
  public void findMostPopularPlaceName(String regionName) {
    OktmoGroup region = oktmo.strGroups.get(regionName);
    if (region == null) {
      System.err.println("Ошибка");
    } else {
      ArrayList<Place> regionPlaces = getPlacesByRegion(region);
      HashMap<String, Long> countSamePlaces = new HashMap <String, Long>();
      regionPlaces.forEach((Place t) -> {
        if (countSamePlaces.containsKey(t.name)) {
          final long num = countSamePlaces.get(t.name)+1;
          countSamePlaces.put(t.name, num);
          if (num > most) {
              most = num;
          }
        }
        else {
          countSamePlaces.put(t.name, 1l);
        }
      });
      countSamePlaces.keySet().stream()
          .filter((String t) -> countSamePlaces.get(t) == most)
          .forEach((String t) -> {System.err.println(t);});
    }
  }
  
  
  public ArrayList<Place> getPlacesByRegion(OktmoGroup region) {
    long min = region.code * 1000;
    long max = min + 1000_000_000l;
    return (ArrayList<Place>) oktmo.dataAndKeys.subMap(min, max)
        .values()
        .stream()
        .collect(Collectors.toList());
  }
  
}
