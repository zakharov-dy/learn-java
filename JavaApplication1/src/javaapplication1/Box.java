package javaapplication1;
import java.util.HashSet;
class Box extends Bag {
    public Box(float w, float mw, String n, HashSet<String> p) {
        super(w, mw, n, p);
        this.addProps("flat");
    }
}
