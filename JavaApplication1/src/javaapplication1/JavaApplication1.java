package javaapplication1;

import java.util.Arrays;
import java.util.HashSet;


public class JavaApplication1 {

    public static void main(String[] args) {
        Item item1 = new Item( 12, "item", new HashSet<String>(Arrays.asList("Ха", "хА")));
        Item item2 = new Item( 14, "item2", new HashSet<String>(Arrays.asList("Ха", "хА", "ХАхаХа")));
        System.out.println(item1.getInfo());
        Bag bag = new Bag(1, 15, "Bag", new HashSet<String>(Arrays.asList("Ха", "хА")));
        bag.addItem(item1);
        System.out.println(bag.getWeight());
        bag.addItem(item2);
        Item removedItem = bag.removeRandomItem();
        System.out.println(removedItem.getInfo());
        bag.addItem(item2);
        System.out.println(bag.getWeight());
        System.out.println(bag.searchItem("item2").getInfo());
        bag.searchItem("item1");
    }
}