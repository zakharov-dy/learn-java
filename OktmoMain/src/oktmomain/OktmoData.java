package oktmomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class OktmoData {
  public ArrayList<Place> data;
  public Map<Long, OktmoGroup> groups;
  public TreeSet<String> allStatuses;
  
  public OktmoData() {
    data = new ArrayList<Place>();
    allStatuses = new TreeSet<String>();
    groups = new HashMap<Long, OktmoGroup>();
  }
  public boolean addPlase(Place d) {
    allStatuses.add(d.status);
    return data.add(d);
  }
  public OktmoGroup addGroup(long key, OktmoGroup d) {
    return groups.put(key, d);
  }
}

