package oktmomain;

import java.util.ArrayList;
import java.util.TreeSet;
import static java.lang.System.out;
import java.util.Collections;
import java.util.Comparator;

public class OktmoData {
  public ArrayList<Place> data;
  public ArrayList<Place> sortedPlaces;
  public TreeSet<String> allStatuses;
  
  public OktmoData() {
    data = new ArrayList<Place>();
    allStatuses = new TreeSet<String>();
  }
  public boolean addPlase(Place d) {
    allStatuses.add(d.status);
    return data.add(d);
  }
  public void sortData() {
    sortedPlaces = (ArrayList<Place>) data.clone();
    Comparator<Place> byName = (Place o1, Place o2) -> o1.name.compareTo(o2.name);
    Collections.sort(sortedPlaces, byName);
    sortedPlaces.forEach((t) -> out.println(t.name));
  }
  public ArrayList<Place> getTheFilteredValuesOfTheEighthCondition() { 
    return (ArrayList<Place>) data.stream().filter((t) -> t.name.matches("^\\D{0,3}ово$"));
  }
//  public void getCodes() { data.forEach((t) -> out.println(t)); }
//  public TreeSet<String> getAllStatuses() { return allStatuses; }

  public ArrayList<Place> printFilterValues() {
    return (ArrayList<Place>) data.stream().filter((t) -> t.name.matches("^(?ui:[цкнгшщзхвпрлджчсмтб]).*(\\1)$"));
  }
}

