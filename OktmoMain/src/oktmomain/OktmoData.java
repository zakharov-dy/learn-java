package oktmomain;

import java.util.ArrayList;
import java.util.TreeSet;

public class OktmoData {
  public ArrayList<Place> data;
  public ArrayList<OktmoGroup> groups;
  public TreeSet<String> allStatuses;
  
  public OktmoData() {
    data = new ArrayList<Place>();
    allStatuses = new TreeSet<String>();
    groups = new ArrayList<OktmoGroup>
  }
  public boolean addPlase(Place d) {
    allStatuses.add(d.status);
    return data.add(d);
  }
  public boolean addGroup(OktmoGroup d) {
    return groups.add(d);
  }
}

