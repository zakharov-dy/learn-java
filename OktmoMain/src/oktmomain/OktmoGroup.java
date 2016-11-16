package oktmomain;

public class OktmoGroup {
  String name;
  long code;
  OktmoLevel level;
  enum OktmoLevel { REGION, RAYON }

  public OktmoGroup(long c, String n) {
    level = ((c % 1000000) == 0) ? OktmoLevel.REGION : OktmoLevel.RAYON;
    code = c;
    name = n;
//    System.out.println(code + ": " + level);
//    System.out.println(code + ": " + name + "!");
  }
}
