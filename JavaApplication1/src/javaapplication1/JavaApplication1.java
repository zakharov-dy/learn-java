package javaapplication1;

import java.util.Arrays;
import java.util.HashSet;


public class JavaApplication1 {

    public static void main(String[] args) {
        Item item1 = new Item( 12, "item", new HashSet<String>(Arrays.asList("Ха", "хА")));
        Item item2 = new Item( 14, "item2", new HashSet<String>(Arrays.asList("Ха", "хА", "ХАхаХа")));
        Item item3 = new Item( 14, "item3", new HashSet<String>(Arrays.asList("flat")));
        Item item4 = new Item( 14, "item4", new HashSet<String>(Arrays.asList("flat")));
        Item item5 = new Item( 14, "item5", new HashSet<String>(Arrays.asList("flat")));
//        System.out.println(item1.getInfo());
//        Bag bag = new Bag(1, 15, "Bag", new HashSet<String>(Arrays.asList("Ха", "хА")));
//        bag.addItem(item1);
//        System.out.println(bag.getWeight());
//        bag.addItem(item2);
//        Item removedItem = bag.removeRandomItem();
//        System.out.println(removedItem.getInfo());
//        bag.addItem(item2);
//        System.out.println(bag.getWeight());
//        System.out.println(bag.searchItem("item2").getInfo());
//        bag.searchItem("item1");
        Pile pile = new Pile(2, "pile");
//        pile.addItem(item3);
//        pile.addItem(item4);
//        pile.addItem(item5);
//        System.out.println(pile.isFlat(item5));
        System.out.println(pile.addItem(item3));
        System.out.println(pile.addItem(item4));
        System.out.println(pile.addItem(item5));
        System.out.println(pile.items.size());
    }
}