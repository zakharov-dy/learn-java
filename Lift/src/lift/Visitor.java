package lift;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Visitor implements Runnable {
  private BusinessCenter lift;
  private BusinessCenter control;
  private static int totalCount = 0;
  private int num;
  private int floor;

  public Visitor(BusinessCenter l, BusinessCenter c) {
    lift = l;
    control = c;
    totalCount++;
    floor = 2 + (int) (Math.random() * 9);
    num = totalCount;
  }
  private void goUp() { liftLife(1, floor); }
  private void goDown() { liftLife(floor, 1); }
  
  private void liftLife(int from, int to) {
    if (lift.enterLift(this)){
      lift.moveLift(from);
      System.out.println(lift.getTime() + toString() + "зашел в лифт");
      lift.moveLift(to);
      lift.exitFromLift(this);
      System.out.println(lift.getTime() + toString() + "вышел из лифта");
    } else {
      System.out.println(lift.getTime() + toString() + "идёт пешком");
      try {
        Thread.sleep((int) Math.random() * 10000 + 2000);
      } catch (InterruptedException ex) {
        Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    synchronized (lift) {
      lift.notify();
    }
  }
  private void doSomeWork() {
    System.out.println(lift.getTime() + toString() + " работает");
    try {
      Thread.sleep((int) Math.random() * 10000 + 2000);
    } catch (InterruptedException ex) {
      Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println(lift.getTime() + toString() + " сделал своё дело " + toString() + " может уходить");
  }
  public void passControl() {
    control.enterControl(this);
    System.out.println(lift.getTime() + toString() + "показывает документы");
    try {
      Thread.sleep((int) Math.random() * 100);
    } catch (InterruptedException ex) {
      Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println(control.getTime() + toString() + "показал документы");
    control.exitFromControl(this);
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
