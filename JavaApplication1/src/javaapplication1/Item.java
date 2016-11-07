package javaapplication1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Item {
    private final float weight;
    private final String name;
    private final HashSet<String> props;
    private boolean inContains;

    public Item(float w, String n, String[] p) {
        weight = w;
        name = n;
        props = (p == null) ? new HashSet<String>() : new HashSet<String>(Arrays.asList(p));
//        Collections.addAll(props, p);
//        props = new HashSet<String>(Arrays.asList(p));
        inContains = false;
    }
    public Item(float w, String n) {
//        TODO: Придумать что-нибудь другое
        this(w, n, null);
    }
    public Item(String n) {
        this(0, n);
    }
    public String getInfo() {
//        String s = String.join(",", props);
        final StringBuilder sb = new StringBuilder();
        props.forEach((String t) -> {sb.append(t);});
    	return name + ":" + weight + " кг. Свойства: " + sb.toString();
    }
    public boolean containsProp(String prop) { return getProps().contains(prop); }
    public boolean getContainsCondition() { return inContains; }
    void place() { inContains = true; }
    void pull() { inContains = false; }
    public boolean addProps (String prop) { return props.add(prop); };
    public float getWeight() {return weight;}
    public String getName() { return name; }
    HashSet<String> getProps() { return props; }
}
