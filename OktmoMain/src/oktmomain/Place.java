
package oktmomain;

import java.util.Objects;

public class Place {
  public long code;
  public String name;
  public String status;
  public long getCode() { return code; }
  
  public Place(long c, String n, String s) {
    code = c;
    name = n;
    status = s;
  }

  @Override
  public boolean equals(Object o) {
    Place obj = (Place) o;
      return (code == obj.code) && (name.equals(obj.name)) && (status.equals(obj.status));
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 67 * hash + (int) (this.code ^ (this.code >>> 32));
    hash = 67 * hash + Objects.hashCode(this.name);
    hash = 67 * hash + Objects.hashCode(this.status);
    return hash;
  }
}
