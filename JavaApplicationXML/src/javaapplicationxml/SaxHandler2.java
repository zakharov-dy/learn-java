package javaapplicationxml;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

class SaxHandler2 implements ContentHandler {
  public HashMap<String, StreetData> streets;
  String curWayId;
  boolean isWay = false;

  public SaxHandler2(HashMap<String, StreetData> hm) {
    streets = hm;
  }

  Locator loc = null;

  public void setDocumentLocator(Locator locator) {
    loc = locator;
  }

  public void startDocument() throws SAXException {
    System.out.println("SAX Дома без улиц");
  }

  public void endDocument() throws SAXException {
    System.out.println();
    System.out.println("Sax Map<String, StreetData>");
    streets.forEach((String s, StreetData data) -> {
      System.out.println(s + " " + data.getPC() + " " + data.getHC());
    });
  }

  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    if( qName.equals("way")){ 
      curWayId = atts.getValue("id");
      isWay = true;
    }
    String key = atts.getValue("k");
    if (isWay && key != null && key.equals("addr:street")) {
      String value = atts.getValue("v");
      if (!streets.containsKey(value)) {
        System.out.println(curWayId + " " + value);
      } else {
        streets.get(value).incHC();
      }
    }
  }

  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equals("way")) {
      curWayId = "-1";
      isWay = false;
    }
  }

  public void characters(char[] ch, int start, int length) throws SAXException {
  }

  public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
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