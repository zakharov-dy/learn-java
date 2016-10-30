package javaapplication1;

import java.util.HashSet;

class Item {
    private final float weight;
    private final String name;
    private final HashSet<String> props;

    Item(float w, String n, HashSet<String> p) {
        weight = w;
        name = n;
        props = p;
    }
    Item(float w, String n) {
        this(w, n, new HashSet<String>());
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
        return items.add(i);
    }
    @Override
    float getWeight() {return super.getWeight();}
}