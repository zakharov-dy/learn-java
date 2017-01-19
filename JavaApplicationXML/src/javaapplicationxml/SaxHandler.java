package javaapplicationxml;
import java.util.HashMap;
import java.util.HashSet;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

class SaxHandler implements ContentHandler {
  private boolean isBusStop;
  private boolean isWay;
  private boolean isNode;
  private boolean isHighway;
  private String curName; 
  public HashSet<String> busStopSet = new HashSet<String>();
  public HashMap<String, StreetData> streets = new HashMap<String, StreetData>();

  public SaxHandler() {
  }

  Locator loc = null;

  public void setDocumentLocator(Locator locator) {
    loc = locator;
  }

  public void startDocument() throws SAXException {
//    System.out.println("[[[Start document");
  }

  public void endDocument() throws SAXException {
    System.out.println("Sax Остановки:");
    busStopSet.forEach((String s) -> {
      System.out.println(s);
    });
    System.out.println();
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
      String k = atts.getValue("k");
      String v = atts.getValue("v");
      if (k.equals("name")) { curName = v; }
      if (isNode && v != null && v.equals("bus_stop")) {isBusStop = true;}
      else if (isWay && k.equals("highway")) { isHighway = true; }
      break;
    }
  }

  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (curName != null) {    
      if (qName.equals("node") && isBusStop) { busStopSet.add(curName); } 
      else if (qName.equals("way") && isHighway) {
        if (!streets.containsKey(curName)) {
          streets.put(curName, new StreetData());
        }
        streets.get(curName).incPC();
      }
    }
    if (qName.equals("node") || qName.equals("way")) {
      isHighway = false;
      isWay = false;
      isNode = false;
      isBusStop = false;
      curName = null;
    }
  }

  public void characters(char[] ch, int start, int length) throws SAXException {
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
