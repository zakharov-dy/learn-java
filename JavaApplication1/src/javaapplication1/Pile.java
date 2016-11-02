package javaapplication1;

import java.util.ArrayDeque;

public class Pile extends ItemsContainer {
    private final float maxSize;
    ArrayDeque<Item> items = new ArrayDeque<Item>();
    public Pile(int ms, String n) {
        super(0, n, null);
        maxSize = Math.abs(ms);
    }
    @Override
    public boolean addItem(Item i) throws ItemStoreException
    {   
        if (isFull()) throw new ItemStoreException("isFull");
        return isFlat(i) ? super.addItem(i) : false; 
    }
    public boolean isFull() { return items.size() == maxSize; }
    public boolean isFlat(Item i) { return i.getProps().contains("flat"); } 
    public Item pollItem() {
        Item removedItem = items.poll();
        if (removedItem == null) {
            return null;
        } else {
//            removedItem.pull();
            return removedItem;
        }
    }
}