package javaapplication1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

public class JavaApplication1 {

    public static void main(String[] args) {
        Item item1 = new Item( 12, "item", new HashSet<String>(Arrays.asList("Ха", "хА")));
        Item item2 = new Item( 14, "item2", new HashSet<String>(Arrays.asList("Ха", "хА", "ХАхаХа")));
        System.out.println(item1.getInfo());
        Bag bag = new Bag(1, 12, "Bagg", new HashSet<String>(Arrays.asList("Ха", "хА")));
        bag.addItem(item1);
        System.out.println(bag.getWeight());
        bag.addItem(item2);
    }
}

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
        String s = String.join(",", props);
//        props.forEach((t) -> {System.out.println(t);});
//        props.forEach((String t) -> {s  t;});
    	return name + ":" + weight + " кг. Свойства: " + s;
    }
    float getWeight() {return weight;}
}
abstract class ItemsContainer extends Item
{
    HashSet<Item> boxCol = new HashSet<Item>();;
    public ItemsContainer(float w, String n, HashSet<String> p) {
        super(w, n, p);
    }
    void addItem(Item i) {
        boxCol.add(i);
    }
    @Override
    float getWeight() {return super.getWeight();}
}
class Bag extends ItemsContainer {
    private final float maxWeight;
    public Bag(float w, float mw, String n, HashSet<String> p) {
        super(w, n, p);
        maxWeight = mw;
    }
    float getItemsWeight () {
        if (boxCol.isEmpty()) {
            return 0;
        } else {
            Optional<Float> currentWeight = boxCol
                    .stream()
                    .map((t) -> t.getWeight())
                    .reduce((s1, s2) -> s1 + s2);
            return currentWeight.get();
        }
    }
    float getWeight () {return super.getWeight() + getItemsWeight();}
    public void addItem(Item i) {
        float w = getItemsWeight() + i.getWeight();
        if (maxWeight < w) {
            System.out.println("слишком тяжело, мешок может порваться");
        } else {
            boxCol.add(i);
            System.out.println("Добавлен item - " + i.getInfo());
        }
//        throw new UnsupportedOperationException("Not supported yet."); 
    }
    public Item getRandomItem() {
        if (boxCol.isEmpty()) {
            System.out.println("empty");
        } else {
            
        }
    }
}