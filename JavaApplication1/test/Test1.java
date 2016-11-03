//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javaapplication1.Item;
//import javaapplication1.Pile;
//import javaapplication1.Bag;
//import javaapplication1.Box;
//import javaapplication1.ItemStoreException;
////import org.junit.Assert;
////import org.junit.Test;
//
//public class Test1 {
//    Item item1 = new Item(6, "item1");
//    Item item2 = new Item(3, "item", new HashSet<String>(Arrays.asList("bla", "bla")));
//    Item item3 = new Item(99, "itm", new HashSet<String>(Arrays.asList("blabla")));
//    Item itemFlat1 = new Item(14, "itemFlat2", new HashSet<String>(Arrays.asList("flat")));
//    Item itemFlat2 = new Item(14, "123412", new HashSet<String>(Arrays.asList("flat")));
//    Bag bag = new Bag(2, 15, "bag", new HashSet<String>(Arrays.asList("Black")));
//    boolean bagItemStoreException = false;
//    @Test
//    public void testItem(){
//        Assert.assertEquals("item1", item1.getName());
//        Assert.assertEquals(6f, item1.getWeight(), 0.001);
//    }
//    @Test
//    public void testBag() {
//        try {
////            Проверка на добавление элементов
//            Assert.assertTrue(bag.addItem(item1));
//            Assert.assertTrue(bag.addItem(item2));
////            Проверка веса (собственный + содержимое)
//            Assert.assertEquals((float) 11, bag.getWeight(), 0.1);
////            Проверка флага "помещено в контейнер"
//            Assert.assertTrue(item1.getContainsCondition());
////            Проверка на добавление того же элемента
//            Assert.assertFalse(bag.addItem(item1));
////            Проверка на вытаскивание элемента
//            Assert.assertEquals(bag.removeRandomItem().getClass().toString(), "class javaapplication1.Item");
//            Assert.assertEquals(bag.removeRandomItem().getClass().toString(), "class javaapplication1.Item");
////            Проверка на то, что bag пуст
//            Assert.assertEquals(bag.itemSize(), 0);
////            Проверка на то, что item1 не в контейнере
//            Assert.assertFalse(item1.getContainsCondition());
//        } catch (ItemStoreException ex) {
//            Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            bag.addItem(item3);
//        } catch (ItemStoreException ex) {
//            bagItemStoreException = true;
//        }
//        Assert.assertTrue(bagItemStoreException);    }
//    @Test
//    public void testPile() {
//        try {
//            Pile pile = new Pile(2, "pile");
////            Проверка на добавление элементов
//            Assert.assertTrue(pile.addItem(itemFlat1));
//            Assert.assertTrue(pile.addItem(itemFlat2));
////            Проверка на то, что больше элементов не помещается
//            Assert.assertTrue(pile.isFull());
////            Проверка на извлечение
//            Assert.assertEquals(pile.pollItem(), itemFlat2);
//            Assert.assertEquals(pile.pollItem(), itemFlat1);
////            Проверка флага "убран из контейнера"
//            Assert.assertFalse(itemFlat2.getContainsCondition());
//        } catch (ItemStoreException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    @Test
//    public void testBox() {
//        try {
//            Box box = new Box(2, 16, "BOX", new HashSet<String>(Arrays.asList("bla")));
////            Проверка на добавление элементов
//            Assert.assertTrue(box.addItem(itemFlat1));
//            Assert.assertTrue(box.addItem(itemFlat2));
////            Проверка на извлечение
//            Assert.assertEquals(box.removeRandomItem(), itemFlat2);
////            Проверка флага "убран из контейнера"
//            Assert.assertFalse(itemFlat2.getContainsCondition());
//        } catch (ItemStoreException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//}
