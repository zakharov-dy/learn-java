package javaapplication1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

class Pile extends ItemsContainer {
    private final float maxSize;
    PriorityQueue items = new PriorityQueue();
    public Pile(float w, float ms, String n, HashSet<String> p) {
        super(w, n, p);
        maxSize = Math.abs(ms);
    }
    @Override
    public boolean addItem(Item i) { return ( !isFull() && isFlat(i)) ? false : super.addItem(i); }
    public boolean isFull() { return items.size() == maxSize; }
    public boolean isFlat(Item i) { return i.getProps().contains("flat"); } 
    public Item pollItem() {
        if (items.isEmpty()) {
            System.out.println("empty");
        }
        
    }

}