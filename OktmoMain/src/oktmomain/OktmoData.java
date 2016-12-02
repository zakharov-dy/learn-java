package oktmomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class OktmoData {
  public ArrayList<Place> data;
  public TreeMap<Long, Place> dataAndKeys;
  public HashMap<Long, OktmoGroup> groups;
  public HashMap<String, OktmoGroup> strGroups;
  public TreeSet<String> allStatuses;
  
  public OktmoData() {
    data = new ArrayList<Place>();
    allStatuses = new TreeSet<String>();
    dataAndKeys = new TreeMap<Long, Place>();
    groups = new HashMap<Long, OktmoGroup>();
    strGroups = new HashMap<String, OktmoGroup>();
  }
  public boolean addPlace(Place d) {
    allStatuses.add(d.status);
    dataAndKeys.put(d.code, d);
    return data.add(d);
  }
  public void addGroup(long key, String name) {
    OktmoGroup group = new OktmoGroup(key, name);
    groups.put(key, group);
    strGroups.put(name, group);
  }
}

