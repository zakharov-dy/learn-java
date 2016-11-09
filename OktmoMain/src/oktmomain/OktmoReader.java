
package oktmomain;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OktmoReader {
    public static void readPlaces(String fileName) {
//    public static void readPlaces(String fileName, OktmoData data) {
        BufferedReader br = null;
        int lineCount = 0;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8")); // или cp1251
            String s;
            Pattern p = Pattern.compile("^\\d{2}\\s\\d{3}\\s\\d{3}.*$");
            while ((s = br.readLine()) != null) {
                lineCount++;
                Matcher m = p.matcher(s);
//                Если строка хранит соответствующие символы вначале
                if (m.matches()) {
//                    Сплитим строку, которая нам подходит
                    String[] splitValue = s.split("\\s+");
                    System.out.println(splitValue[0]);
                    long num = Long.parseLong(splitValue[0] + splitValue[1] + splitValue[2]);
                    
                }
//                System.out.println((splitValue.length != 0) && (splitValue[0].matches("[-+]?\\d*\\.?\\d+")));
                if (lineCount == 1000) {
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
    }
}

