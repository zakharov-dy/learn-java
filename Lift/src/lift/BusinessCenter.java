package lift;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BusinessCenter {
  private boolean liftFree;
  private boolean controlFree;
  private int liftFloor;
  private long startTimeout;

  public BusinessCenter() {
    startTimeout = System.currentTimeMillis();
    liftFloor = 1;
    liftFree = true;
    controlFree = true;
  }
  
  public void enterLift(Visitor v) {
    synchronized (this) {
      if (!liftFree) {
        System.out.println(getTime() + v.toString() + " ждёт лифт");
      }
      while (!liftFree) {
        try {
          this.wait();
        } catch (InterruptedException ex) {
          System.err.println("Rfrfrf");
        }
      }
      System.out.println(getTime() + v.toString() + "вызвал лифт");
      liftFree = false;
    }
  }  
  public boolean enterLift(Visitor v, boolean lalala) {
    long beginTime = System.currentTimeMillis();
    synchronized (this) {
      if (!liftFree) { System.out.println(getTime() + v.toString() + " ждёт лифт");}
      while (!liftFree) {
        try {
          if ((System.currentTimeMillis() - beginTime) < 1000) {return false;}
          this.wait();
        } catch (InterruptedException ex) {
          System.err.println("Rfrfrf");
        }
      }
      System.out.println(getTime() + v.toString() + "вызвал лифт");
      liftFree = false;
      return true;
    }
  }
  
  public void enterControl(Visitor v) {
    System.out.println(getTime() + v.toString() + " пришел");
    synchronized (this) {
      while (!controlFree) {
        try {
          this.wait();
        } catch (InterruptedException ex) {
          System.err.println("ЛалалаЛалаЛалалала");
        }
      }
      controlFree = false;
    }
  }
  public void exitFromControl(Visitor v) {
    synchronized (this) {
      controlFree = true;
      this.notify();
    }
  }
  
  public void moveLift(int targetFloor) {
    System.out.println(getTime() + "Лифт едет на этаж " + targetFloor);
    try {
      Thread.sleep(Math.abs(liftFloor - targetFloor) * 100 + 200);
      liftFloor = targetFloor;
    } catch (InterruptedException ex) {
      Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void exitFromLift(Visitor v) {
    synchronized (this) {
      liftFree = true;
    }
  }
  
  public String getTime() { 
    return (System.currentTimeMillis() - startTimeout) + "ms: ";
  }
}
