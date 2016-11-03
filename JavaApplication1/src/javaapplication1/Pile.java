package javaapplication1;

import java.util.ArrayDeque;
import java.util.Deque;

public class Pile extends ItemsContainer {
    private final int maxSize;
//    private final ArrayDeque<Item> items = new ArrayDeque<Item>();
    public Pile(int ms, String n) {
        super(0, n, null);
        maxSize = Math.abs(ms);
        items = new ArrayDeque<Item>();
    }
    @Override
    public boolean addItem(Item i) throws ItemStoreException
    {   
        if (isFull()) throw new ItemStoreException("isFull");
        return i.containsProp("flat") ? super.addItem(i) : false; 
    }
    public boolean isFull() { return items.size() == maxSize; }
    public Item pollItem() {
        Item removedItem = ((Deque<Item>) items).pollLast();
        if (removedItem == null) {
            return null;
        } else {
            removedItem.pull();
            return removedItem;
        }
    }
}