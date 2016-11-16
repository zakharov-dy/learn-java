package oktmomain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OktmoReader {
  public static OktmoData readPlaces(String fileName, OktmoData oktmo) {
    //    public static void readPlaces(String fileName, OktmoData data) {
    BufferedReader br = null;
    int lineCount = 0;
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "cp1251")); // или cp1251
      String s;
      Pattern p = Pattern.compile("^(\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}(?<!000))\\s+\\d\\s+(\\S)*\\s(.*)\\s*$");
      while ((s = br.readLine()) != null) {
        lineCount++;
        Matcher m = p.matcher(s);
    if (m.matches()) {
      long num = Long.parseLong(m.group(1).replaceAll("\\s+", ""));
      oktmo.addPlase(new Place(num, m.group(3), m.group(2)));
    }
    if (lineCount == 100000) {
      break;
    }
      }
    } catch (IOException ex) {
      System.out.println("Reading error in line " + lineCount);
      ex.printStackTrace();
    } finally {
      try {
        br.close();
      } catch (IOException ex) {
        System.out.println("Can not close");
      }
    }
    return oktmo;
  }
  public static OktmoData readPlacesViaSplit(String fileName) {
    //    public static void readPlaces(String fileName, OktmoData data) {
    BufferedReader br = null;
    int lineCount = 0;
    OktmoData data = new OktmoData();
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "cp1251")); // или cp1251
      String s;
      while ((s = br.readLine()) != null) {
        lineCount++;
//        Если строка хранит соответствующие символы вначале
        String[] strItems = s.split("\\s+");
        if (strItems.length > 6 && !"000".equals(strItems[3])) {
          String code = strItems[0] + strItems[1] + strItems[2] + strItems[3];
//          if (code.matches("\\d{11}")) {
          if (isNumeric(strItems[4]) && isNumeric(code)) {
            long num = Long.parseLong(code);
            String status = strItems[5];
            String name = String
                .join(" ", Arrays.copyOfRange(strItems, 6, strItems.length));
            data.addPlase(new Place(num, name, status));
          }
        }
        if (lineCount == 100000) {
          break;
        }
      }
    } catch (IOException ex) {
      System.out.println("Reading error in line " + lineCount);
      ex.printStackTrace();
    } finally {
      try {
        br.close();
      } catch (IOException ex) {
        System.out.println("Can not close");
      }
    }
    return data;
  }
  private static boolean isNumeric(String str) {
    try {
      Long.parseLong(str);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }
  public static OktmoData readGroups(String fileName, OktmoData data) {
    //    public static void readPlaces(String fileName, OktmoData data) {
    BufferedReader br = null;
    int lineCount = 0;
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "cp1251")); // или cp1251
      String s;
      Pattern p = Pattern.compile("^(\\d{2}\\s\\d{3}\\s000)\\s+\\d\\s+(.*)$");
//      Pattern p = Pattern.compile("^(\\d{2})\\s(\\d{3})\\s000\\s+\\d\\s+(.*)$");
      while ((s = br.readLine()) != null) {
        lineCount++;
        Matcher m = p.matcher(s);
        if (m.matches()) {
          long num = Long.parseLong(m.group(1).replaceAll("\\s+", ""));
          data.addGroup(num, new OktmoGroup(num, m.group(2).trim()));
        }
        if (lineCount == 100000) {
          break;
        }
      }
    } catch (IOException ex) {
      System.out.println("Reading error in line " + lineCount);
      ex.printStackTrace();
    } finally {
      try {
        br.close();
      } catch (IOException ex) {
        System.out.println("Can not close");
      }
    }
    return data;
  }
}