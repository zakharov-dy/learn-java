package lift;

public class Lift {
  public static void main(String[] args) {
    BusinessCenter bc = new BusinessCenter();
    Visitor v1 = new Visitor(bc);
    Visitor v2 = new Visitor(bc);
    Thread t1 = new Thread(v1);
    Thread t2 = new Thread(v2);
    t1.start();
    t2.start();
  }
}
