package javaapplicationxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class XmlSax {
  public void process(String fname, String schemaName) {
    SAXParserFactory spf = SAXParserFactory.newInstance();
    Source schemaSrc = new StreamSource(new File(schemaName));
    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try {
      Schema scheme = sf.newSchema(schemaSrc);
      spf.setSchema(scheme);
    } catch (SAXException ex) {
      System.err.println("ERROR PARSING XSD:" + ex.getMessage());
      return;
    }
    InputSource is;
    try {
      is = new InputSource(new FileInputStream(fname));
    } catch (FileNotFoundException ex) {
      Logger.getLogger(XmlSax.class.getName()).log(Level.SEVERE, null, ex);
      return;
    }
    try {
      SAXParser prs = spf.newSAXParser();
      XMLReader rd = prs.getXMLReader();
      SaxXmlHandler myXmlHandler = new SaxXmlHandler();
      rd.setContentHandler(myXmlHandler);
      rd.setErrorHandler(new ErrorHandler() {
        public void warning(SAXParseException exception) throws SAXException {
          System.err.println("WARNING: " + exception.getMessage());
          throw new SAXException();
        }
        public void error(SAXParseException exception) throws SAXException {
          System.err.println("ERROR: " + exception.getMessage());
          throw new SAXException();
        }
        public void fatalError(SAXParseException exception) throws SAXException {
          throw new UnsupportedOperationException("Not supported yet.");
        }
      });
      rd.parse(is);
      SaxXmlHandler2 myXmlHandler2 = new SaxXmlHandler2(myXmlHandler.streets);
      rd.setContentHandler(myXmlHandler2);
      InputSource is2;
      try {
        is2 = new InputSource(new FileInputStream(fname));
      } catch (FileNotFoundException ex) {
        Logger.getLogger(XmlSax.class.getName()).log(Level.SEVERE, null, ex);
        return;
      }
      rd.parse(is2);
//      System.out.println("result: " + myXmlHandler.names);
      
    } catch (ParserConfigurationException ex) {
      System.err.println("Config Exception:  " + ex.getMessage());
    } catch (IOException ex) {
      System.err.println("IO Exception:  " + ex.getMessage());
    } catch (SAXException ex) {
      System.err.println("SAX Exception:  " + ex.getMessage());
    }
  }
}
