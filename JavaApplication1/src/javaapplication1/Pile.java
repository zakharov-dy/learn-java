package javaapplication1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

class Pile extends ItemsContainer {
    private final float maxSize;
    PriorityQueue items = new PriorityQueue();
    public Pile(int ms, String n) {
        super(0, n, null);
        maxSize = Math.abs(ms);
    }
    @Override
    public boolean addItem(Item i) { return ( !isFull() && isFlat(i)) ? super.addItem(i) : false; }
    public boolean isFull() { return items.size() == maxSize; }
    public boolean isFlat(Item i) { return i.getProps().contains("flat"); } 
    public Item pollItem() { return (Item) items.poll(); }
}