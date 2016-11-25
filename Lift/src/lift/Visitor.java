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
  }
  private void goUp() {
    place.enterLift(this);
    System.out.println("Visitor" + this.toString() + "is enter");
    try {
      Thread.sleep(floor*100 + 200);
    } catch (InterruptedException ex) {
      Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  private void goDown() {
    place.exitFromLift(this);
    System.out.println("Visitor" + this.toString() + "is exit");
    place.notify();
  }
  @Override
  public void run() {
    goUp();
    goDown();
  }
  @Override
  public String toString () {
    return Integer.toString(num);
  }
}
