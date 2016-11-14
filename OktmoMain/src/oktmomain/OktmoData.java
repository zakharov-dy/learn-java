package oktmomain;

import java.util.ArrayList;
import java.util.TreeSet;

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
}

