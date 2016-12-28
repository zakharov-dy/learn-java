package javaapplicationxml;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

class SaxXmlHandler implements ContentHandler {
  private boolean nameTag;
  private boolean isBusStop;
  private boolean isWay;
  private boolean isNode;
  private boolean isHighway;
  public HashSet<String> busStopSet = new HashSet<String>();
  public HashMap<String, StreetData> streets = new HashMap<String, StreetData>();

  public SaxXmlHandler() {
  }

  Locator loc = null;

  public void setDocumentLocator(Locator locator) {
    System.out.println("Set document locator=" + locator);
    loc = locator;
  }

  public void startDocument() throws SAXException {
//    System.out.println("[[[Start document");
  }

  public void endDocument() throws SAXException {
    busStopSet.forEach((String s) -> {
      System.out.println(s);
    });
    System.out.println("+++++++++++++++++Улицы короче++++++++++++++++++");
    streets.forEach((String s, StreetData data) -> {
      System.out.println(s + " " + data.getPC());
    });
  }

  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    switch (qName) {
    case "node":
      isNode = true;
      break;
    case "way":
      isWay = true;
      break;
    case "tag":
      String key = atts.getValue("k");
      if (key.equals("addr:street")) {
        String value = atts.getValue("v");
        if (!streets.containsKey(value)) {
          System.out.println(value);
        } else {streets.get(value).incHC();}
      }
      if (isNode) {
        if (isBusStop) {
          if (key.equals("name")) {
            busStopSet.add(atts.getValue("v"));
          }
        } else {
          String value = atts.getValue("v");
          if (value != null && value.equals("bus_stop")) {
            isBusStop = true;
          }
        }
        return;
      }
      if (isWay) {
        if (isHighway && key.equals("name")) {
          String value = atts.getValue("v");
          if (!streets.containsKey(value)) {
            streets.put(value, new StreetData());
          }
          streets.get(value).incPC();
        } else if (key.equals("highway")) {
          isHighway = true;
        }
      }
      break;
    }
  }

  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equals("node") || qName.equals("way")) {
      isHighway = false;
      isBusStop = false;
      isWay = false;
      isNode = false;
    }
  }

  public void characters(char[] ch, int start, int length) throws SAXException {
//    String chars = new String(ch, start, length).trim();
//
//    if (nameTag) {
//      names.add(chars); // запомним
//    }
//
//    if (chars.length() > 0) {
//      System.out.printf("[L%d],Characters: ***%s***\n", loc.getLineNumber(), chars);
//    } else {
//      System.out.println("Characters: empty");
//    }
  }

  public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
//    System.out.println("ignorable whitespaces");
  }

  public void processingInstruction(String target, String data) throws SAXException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void skippedEntity(String name) throws SAXException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void startPrefixMapping(String prefix, String uri) throws SAXException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void endPrefixMapping(String prefix) throws SAXException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
