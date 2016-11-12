
package oktmomain;

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
}
