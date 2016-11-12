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
    
    public void printData() {
        data.forEach((t) -> System.out.println(t.status + " " + t.code + ": " + t.name));
    }
    public void printStatuses() {
        allStatuses.forEach((t) -> System.out.println(t));
    }
}
