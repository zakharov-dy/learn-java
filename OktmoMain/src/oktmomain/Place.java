
package oktmomain;

import java.util.Objects;

public class Place {
  long code;
  String name;
  String status;
  public long getCode() { return code; }
  
  public Place(long c, String n, String s) {
    code = c;
    name = n;
    status = s;
  }
  @Override
  public boolean equals(Object o) { return o.hashCode() == this.hashCode(); }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 67 * hash + (int) (this.code ^ (this.code >>> 32));
//    hash = 67 * hash + Objects.hashCode(this.name);
//    hash = 67 * hash + Objects.hashCode(this.status);
    return hash;
  }
}
