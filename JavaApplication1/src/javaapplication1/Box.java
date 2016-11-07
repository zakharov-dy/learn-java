package javaapplication1;
public class Box extends Bag {
    public Box(float w, float mw, String n, String[] p) {
        super(w, mw, n, p);
        this.addProps("flat");
    }
}
