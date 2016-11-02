package javaapplication1;

import java.util.HashSet;

public abstract class ItemsContainer extends Item {

    HashSet<Item> items = new HashSet<Item>();
    public ItemsContainer(float w, String n, HashSet<String> p) {
        super(w, n, p);
    }

    boolean addItem(Item i) throws ItemStoreException {
        if (i.inContains) {
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

    @Override
    public float getWeight() {
        return super.getWeight();
    }
}
