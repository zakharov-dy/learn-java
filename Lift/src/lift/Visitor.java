package lift;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Visitor implements Runnable {
  private BusinessCenter place;
  private static int totalCount = 0;
  private int num;
  private int floor;

  public Visitor(BusinessCenter p) {
    place = p;
    totalCount++;
    floor = (int) Math.random() * 10;
    num = totalCount;
  }
  private void goUp() {
    place.enterLift(this);
//    System.out.println(this.toString() + " едет на этаж " +);
    try {
      Thread.sleep(floor*100 + 200);
    } catch (InterruptedException ex) {
      Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
    }
    place.exitFromLift(this);
    System.out.println("Visitor" + this.toString() + "is exit");
    synchronized (place) {
      place.notify();
    }
  }
  private void goDown() {
    place.enterLift(this);
    System.out.println("" + this.toString() + "is enter");
    try {
      Thread.sleep(floor * 100 + 200);
    } catch (InterruptedException ex) {
      Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
    }
    place.exitFromLift(this);
    System.out.println("Visitor" + this.toString() + "is exit");
    synchronized (place) {
      place.notify();
    }
  }
  private void doSomeWork() {
    System.out.println("Visitor is work");
    try {
      Thread.sleep((int) Math.random() * 100);
    } catch (InterruptedException ex) {
      Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println("Visitor ");
  }
  @Override
  public void run() {
    goUp();
    doSomeWork();
    goDown();
  }
  @Override
  public String toString () {
    return Integer.toString(num);
  }
}
