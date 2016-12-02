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
    floor = 2 + (int) (Math.random() * 9);
    num = totalCount;
  }
  private void goUp() { liftLife(1, floor); }
  private void goDown() { liftLife(floor, 1); }
  
  private void liftLife(int from, int to) {
    place.enterLift(this);
    place.moveLift(from);
    System.out.println(place.getTime() + toString() + "зашел в лифт");
    place.moveLift(to);
    place.exitFromLift(this);
    System.out.println(place.getTime() + toString() + "вышел из лифта");
    synchronized (place) {
      place.notify();
    }
  }
  private void doSomeWork() {
    System.out.println(place.getTime() + toString() + " работает");
    try {
      Thread.sleep((int) Math.random() * 10000 + 2000);
    } catch (InterruptedException ex) {
      Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println(place.getTime() + toString() + " сделал своё дело " + toString() + " может уходить");
  }
  public void passControl() {
    place.enterControl(this);
    System.out.println(place.getTime() + toString() + "показывает документы");
    try {
      Thread.sleep((int) Math.random() * 100);
    } catch (InterruptedException ex) {
      Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println(place.getTime() + toString() + "показал документы");
    place.exitFromControl(this);
  }
  @Override
  public void run() {
    passControl();
    goUp();
    doSomeWork();
    goDown();
  }
  
  @Override
  public String toString () {
    return "Посетитель " + Integer.toString(num) + " ";
  }
}
