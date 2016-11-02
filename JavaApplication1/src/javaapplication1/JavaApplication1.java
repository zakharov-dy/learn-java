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
        Pile pile = new Pile(2, "pile");
//        pile.addItem(item3);
//        pile.addItem(item4);
//        pile.addItem(item5);
//        System.out.println(pile.isFlat(item5));
        try {
            System.out.println(pile.addItem(item3));
            System.out.println(pile.addItem(item4));
            System.out.println(pile.pollItem().getInfo());
            System.out.println(pile.addItem(item5));
            System.out.println(pile.addItem(item4));   
        } catch (ItemStoreException e) {
            System.out.println("Ой, упало");
        }
//        System.out.println(pile.pollItem().getInfo());
    }
}