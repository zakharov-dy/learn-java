package lift;

public class Lift {
  public static void main(String[] args) {
    BusinessCenter lift = new BusinessCenter();
    BusinessCenter control = new BusinessCenter();
    Visitor v1 = new Visitor(lift, control);
    Visitor v2 = new Visitor(lift, control);
    Thread t1 = new Thread(v1);
    Thread t2 = new Thread(v2);
    t1.start();
    t2.start();
  }
}
