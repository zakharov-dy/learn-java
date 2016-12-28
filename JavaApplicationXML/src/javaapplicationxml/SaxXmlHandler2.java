package javaapplicationxml;
import java.util.HashMap;
import java.util.HashSet;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

class SaxXmlHandler2 implements ContentHandler {
  public HashMap<String, StreetData> streets;

  public SaxXmlHandler2(HashMap<String, StreetData> hm) {
    streets = hm;
  }

  Locator loc = null;

  public void setDocumentLocator(Locator locator) {
    System.out.println("Set document locator=" + locator);
    loc = locator;
  }

  public void startDocument() throws SAXException {
  }

  public void endDocument() throws SAXException {
  }

  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    String key = atts.getValue("k");
    if (key.equals("addr:street")) {
      String value = atts.getValue("v");
      if (!streets.containsKey(value)) {
        System.out.println(value);
      } else {
        streets.get(value).incHC();
      }
    }
  }

  public void endElement(String uri, String localName, String qName) throws SAXException {
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