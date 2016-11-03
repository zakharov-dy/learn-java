package javaapplication1;

import java.util.Collection;
import java.util.HashSet;

public abstract class ItemsContainer extends Item {

    Collection<Item> items;
    public ItemsContainer(float w, String n, HashSet<String> p) {
        super(w, n, p);
    }

    boolean addItem(Item i) throws ItemStoreException {
        if (i.getContainsCondition()) {
            return false;
        } else {
            if (items.add(i)) {
                i.place();
                return true;
            } else {
                return false;
            }
        }
    }
    public int itemSize() { return items.size(); }

    @Override
    public float getWeight() {
        return super.getWeight();
    }
}
