package javaapplication1;

import java.util.Collection;

public abstract class ItemsContainer extends Item {

    Collection<Item> items;
    public ItemsContainer(float w, String n, String[] p) {
        super(w, n, p);
    }

    boolean addItem(Item i) throws ItemStoreException {
        if (i==this) {
            throw new ItemStoreException("add self");
        }
        if (i.getContainsCondition()) {
            return false;
        } else {
            if (items.add(i)) {
                i.place();
                return true;
            } else {
                throw new ItemStoreException("item didn`t add");
            }
        }
    }
    public int itemSize() { return items.size(); }

    @Override
    public float getWeight() {
        return super.getWeight();
    }
}
