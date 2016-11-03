package javaapplication1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

public class Bag extends ItemsContainer {
    private final float maxWeight;
    public Bag(float w, float mw, String n, HashSet<String> p) {
        super(w, n, p);
        maxWeight = mw;
        items = new HashSet<Item>();
    }
    float getItemsWeight () {
        if (items.isEmpty()) {
            return 0;
        } else {
            Optional<Float> currentWeight = items
                    .stream()
                    .map((t) -> t.getWeight())
                    .reduce((s1, s2) -> s1 + s2);
            return currentWeight.get();
        }
    }
    @Override
    public float getWeight () {return super.getWeight() + getItemsWeight();}
    @Override
    public boolean addItem(Item i) throws ItemStoreException {
        if(maxWeight < (getItemsWeight() + i.getWeight())) {
            throw new ItemStoreException("isFull");
        }
        return super.addItem(i);
    }
    public Item removeRandomItem() {
        if (items.isEmpty()) {
            System.out.println("empty");
            return null;
        } else {
            Iterator iter = items.iterator();
            Item removedItem = (Item) iter.next();
            removedItem.pull();
            iter.remove();
            return removedItem;
        }
    }
    public Item searchItem (String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        System.out.println("not found");
        return null;
    }
}