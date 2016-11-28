package lift;

public class BusinessCenter {
  private boolean liftFree;
  private boolean contrilFree;
  private int liftFloor;

  public BusinessCenter() {
    liftFloor = 1;
    liftFree = true;
    contrilFree = true;
  }
  public void enterLift(Visitor v) {
    synchronized (this) {
      while (!liftFree) {
        try {
          System.out.println(v.toString() + " ждёт лифт");
          this.wait();
//          this.notify();
        } catch (InterruptedException ex) {
          System.err.println("Rfrfrf");
        }
      }
      System.out.println("" + v.);
      liftFree = false;
    }
  }
  public void exitFromLift(Visitor v) {
    synchronized (this) {
//      System.out.println("Visitor" + v.toString() + "is exit");
      liftFree = true;
//      this.notify();
    }
  }
  
}
