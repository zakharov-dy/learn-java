package oktmomain;

import java.util.ArrayList;
import java.util.TreeSet;
import static java.lang.System.out;

public class OktmoData {
  public ArrayList<Place> data;
  public TreeSet<String> allStatuses;
  
  public OktmoData() {
    data = new ArrayList<Place>();
    allStatuses = new TreeSet<String>();
  }
  
  public boolean addPlase(Place d) {
    allStatuses.add(d.status);
    return data.add(d);
  }
  
  public void printCodes() { data.forEach((t) -> out.println(t)); }
  public void printStatuses() { allStatuses.forEach((t) -> out.println(t)); }
}
