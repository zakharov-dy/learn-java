package oktmomain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OktmoReader {
  public static OktmoData readPlaces(String fileName) {
    //    public static void readPlaces(String fileName, OktmoData data) {
    BufferedReader br = null;
    int lineCount = 0;
    OktmoData data = new OktmoData();
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "cp1251")); // или cp1251
      String s;
      Pattern p = Pattern.compile("^(\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}(?<!000))\\s+\\d\\s+(\\S)*\\s(.*)$");
      while ((s = br.readLine()) != null) {
        lineCount++;
        Matcher m = p.matcher(s);
    if (m.matches()) {
      long num = Long.parseLong(m.group(1).replaceAll("\\s+", ""));
      data.addPlase(new Place(num, m.group(3), m.group(2)));
    }
    if (lineCount == 100000) {
      break;
    }
      }
      data.printStatuses();
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
        if (strItems.length > 6
          && (strItems[0]+strItems[1]+strItems[2]+strItems[3]).matches("//d{11}")
          && !"000".equals(strItems[3])) {
//          just do it
        }
//                        System.out.println(splitValue[0]);
//                        long num = Long.parseLong(splitValue[0] + splitValue[1] + splitValue[2]);
//                    System.out.println((splitValue.length != 0) && (splitValue[0].matches("[-+]?\\d*\\.?\\d+")));
        if (lineCount == 100000) {
          break;
        }
      }
      data.printStatuses();
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