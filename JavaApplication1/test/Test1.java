
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.Item;
import javaapplication1.Pile;
import javaapplication1.Bag;
import javaapplication1.ItemStoreException;
import org.junit.Assert;
import org.junit.Test;

public class Test1 {
    @Test
    public void testItem(){
        Item item1 = new Item(6, "item1");
        Item item2 = new Item(3, "item", new HashSet<String>(Arrays.asList("bla", "bla")));
        Item item3 = new Item(14, "itm", new HashSet<String>(Arrays.asList("blabla")));
        Item itemFlat1 = new Item(14, "itemFlat2", new HashSet<String>(Arrays.asList("flat")));
        Item itemFlat2 = new Item(14, "123412", new HashSet<String>(Arrays.asList("flat")));
        Assert.assertEquals("item1", item1.getName());
        Assert.assertEquals((float) 6, item1.getWeight(), 0.1);
        
        Bag bag = new Bag(2, 15, "bag", new HashSet<String>(Arrays.asList("Black")));
        try {
//            Проверка на добавление элементов
            Assert.assertTrue(bag.addItem(item1));
            Assert.assertTrue(bag.addItem(item2));
//            Проверка веса (собственный + содержимое)
            Assert.assertEquals((float) 11, bag.getWeight(), 0.1);
//            Проверка флага "помещено в контейнер"
            Assert.assertTrue(item1.getContainsCondition());
//            Проверка на добавление того же элемента
            Assert.assertFalse(bag.addItem(item1));
//            Проверка на вытаскивание элемента
            Assert.assertEquals(bag.removeRandomItem().getClass().toString(), "class javaapplication1.Item");
            Assert.assertEquals(bag.removeRandomItem().getClass().toString(), "class javaapplication1.Item");
//            Assert.assertFalse(!bag.addItem(item2));
        } catch (ItemStoreException ex) {
            Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Pile pile = new Pile(2, "pile");
//            Проверка на добавление элементов
            Assert.assertTrue(pile.addItem(itemFlat1));
            Assert.assertTrue(pile.addItem(itemFlat2));
            Assert.assertEquals(pile.pollItem(), itemFlat2);
        } catch (ItemStoreException ex) {
            System.out.println(ex.getMessage());
        }

//        try {
//            Assert.assertTrue(pile.addItem(itemFlat1));
//            Assert.assertTrue(!pile.addItem(item2));
//        } catch (ItemStoreException ex) {
//            System.out.println(ex.getMessage());
//        }        
//        System.out.println(pile.addItem(item3));
//        Assert.assertEquals(item1.getInfo(), "item1:14.0 кг. Свойства: " );
//        System.out.println(pile.addItem(item4));
//        System.out.println(item1.pollItem().getInfo());
//        System.out.println(pile.addItem(item5));
//        System.out.println(pile.addItem(item4));
//        System.out.println(item1.getInfo());
//        System.out.println(bag.getWeight());
//        bag.addItem(item2);
//        Item removedItem = bag.removeRandomItem();
//        System.out.println(removedItem.getInfo());
//        bag.addItem(item2);
//        System.out.println(bag.getWeight());
//        System.out.println(bag.searchItem("item2").getInfo());
//        bag.searchItem("item1");
//        Pile pile = new Pile(2, "pile");
//        pile.addItem(item3);
//        pile.addItem(item4);
//        pile.addItem(item5);
//        System.out.println(pile.isFlat(item5));
    }
}
