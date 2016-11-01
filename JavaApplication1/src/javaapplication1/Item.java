package javaapplication1;

import java.util.HashSet;

class Item {
    private final float weight;
    private final String name;
    private final HashSet<String> props;
    boolean inContains;

    Item(float w, String n, HashSet<String> p) {
        weight = w;
        name = n;
        props = p;
        inContains = false;
    }
    Item(float w, String n) {
        this(w, n, null);
    }
    Item(String n) {
        this(0, n);
    }
    String getInfo() {
//        String s = String.join(",", props);
        final StringBuilder sb = new StringBuilder();
        props.forEach((String t) -> {sb.append(t);});
    	return name + ":" + weight + " кг. Свойства: " + sb.toString();
    }
    void place() { inContains = true; }
    void pull() { inContains = false; }
    boolean addProps (String prop) { return props.add(prop); };
    float getWeight() {return weight;}
    String getName() { return name; }
    HashSet<String> getProps() { return props; }
}
abstract class ItemsContainer extends Item
{
    HashSet<Item> items = new HashSet<Item>();;
    public ItemsContainer(float w, String n, HashSet<String> p) {
        super(w, n, p);
    }
    boolean addItem(Item i) {
        if (i.inContains) {
            return false;
        } else {
            if (items.add(i)) {
                i.place();
                return true;
            } else {
                return false;
            }
        }
    }
    @Override
    float getWeight() {return super.getWeight();}
}