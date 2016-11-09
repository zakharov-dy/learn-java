import javaapplication1.Item;
import javaapplication1.Pile;
import javaapplication1.Bag;
import javaapplication1.Box;
import javaapplication1.ItemStoreException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test1 {
    Item item1;
    Item item2;
    Item item3;
    Item item4;
    Item itemFlat1;
    Item itemFlat2;
    Bag bag;
    boolean bagItemStoreException;
    Box box;
    @Before public void initialize() {
        item1 = new Item(6, "item1");
        item2 = new Item(3, "item", new String[]{"bla", "bla"});
        item3 = new Item(99, "itm", new String[]{"blabla"});
        item4 = new Item(1, "itm", new String[]{"blabla"});
        itemFlat1 = new Item(14, "itemFlat2", new String[]{"flat"});
        itemFlat2 = new Item(14, "123412", new String[]{"flat"});
        bag = new Bag(2, 15, "bag", new String[]{"Black"});
        box = new Box(2, 16, "BOX", new String[]{"bla"});
        bagItemStoreException = false;
    } 

    @Test
    public void testItem(){
        Assert.assertEquals("item1", item1.getName());
        Assert.assertEquals(6f, item1.getWeight(), 0.001);
    }
    @Test
    public void testBag() throws ItemStoreException {
//            Проверка на добавление элементов
            Assert.assertTrue(bag.addItem(item1));
            Assert.assertTrue(bag.addItem(item2));
//            Проверка веса (собственный + содержимое)
            Assert.assertEquals(11f, bag.getWeight(), 0.1);
//            Проверка флага "помещено в контейнер"
            Assert.assertTrue(item1.getContainsCondition());
//            Проверка на добавление того же элемента
            Assert.assertFalse(bag.addItem(item1));
//            Проверка на вытаскивание элемента
            Assert.assertEquals(bag.removeRandomItem().getClass().toString(), "class javaapplication1.Item");
            Assert.assertEquals(bag.removeRandomItem().getClass().toString(), "class javaapplication1.Item");
//            Проверка на то, что bag пуст
            Assert.assertEquals(bag.itemSize(), 0);
//            Проверка на то, что item1 не в контейнере
            Assert.assertFalse(item1.getContainsCondition());

        try {
            bag.addItem(item3);
        } catch (ItemStoreException ex) {
            bagItemStoreException = true;
        }
        Assert.assertTrue(bagItemStoreException);    }
    @Test
    public void testPile() throws ItemStoreException {
        Pile pile = new Pile(2, "pile");
//            Проверка на добавление элементов
            Assert.assertTrue(pile.addItem(itemFlat1));
            Assert.assertTrue(pile.addItem(itemFlat2));
//            Проверка на то, что больше элементов не помещается
            Assert.assertTrue(pile.isFull());
//            Проверка на извлечение
            Assert.assertEquals(pile.pollItem(), itemFlat2);
            Assert.assertEquals(pile.pollItem(), itemFlat1);
//            Проверка флага "убран из контейнера"
            Assert.assertFalse(itemFlat2.getContainsCondition());
        //        Проверка на заполнение неправильным элементом
        boolean onlyFlat = false;
        try {
            Assert.assertTrue(pile.addItem(item1));
        } catch (ItemStoreException ex) {
            onlyFlat = true;
        }
        Assert.assertTrue(onlyFlat);
    }
    @Test
    public void testBox() throws ItemStoreException {
//            Проверка на добавление элементов
            Assert.assertTrue(box.addItem(itemFlat1));
//            Assert.assertTrue(box.addItem(itemFlat2));
//            Проверка на извлечение
            Assert.assertEquals(box.removeRandomItem(), itemFlat1);
    }
    @Test
    public void testAddSelf() {
        boolean addSelfException = false;
        try {
//            Добавляем мешок в себя
            bag.addItem(bag);
        } catch (ItemStoreException ex) {
            addSelfException = true;
        }
        Assert.assertTrue(addSelfException);
    }
    @Test
    public void testOverflow() {
//        проверка на переполнение
        boolean overflow = false;
        try {
            bag.addItem(item3);
        } catch (ItemStoreException ex) {
            overflow = true;
        }
        Assert.assertTrue(overflow);
    }
    @Test
    public void testWeight() throws ItemStoreException {
            Assert.assertEquals(bag.getWeight(), 2, 0.01);
            bag.addItem(item1);
            Assert.assertEquals(bag.getWeight(), 8, 0.01);
            Assert.assertEquals(box.getWeight(), 2, 0.01);
            box.addItem(bag);
            Assert.assertEquals(box.getWeight(), 10, 0.01);
            box.addItem(item2);
            Assert.assertEquals(box.getWeight(), 13, 0.01);
////            Добавляем элемент в мешок 
//            bag.addItem(item4);
//            Assert.assertEquals(bag.getWeight(), 9, 0.01);
//            Assert.assertEquals(box.getWeight(), 14, 0.01);
            //        проверка на переполнение
            boolean contain = false;
            try {
                bag.addItem(item4);
            } catch (ItemStoreException ex) {
                contain = true;
            }
            Assert.assertTrue(contain);
    }
}
