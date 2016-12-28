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

    /////// подключаем схему XSD
    // XML -поток - источник данных схемы
    Source schemaSrc = new StreamSource(new File(schemaName));
    // Фабрика - генератор схем
    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try {
      // читаем схему из файла
      Schema scheme = sf.newSchema(schemaSrc);
      // Schema scheme = sf.newSchema(new File("test.xsd")); // тоже годится

      /////////////////////////////////////
      spf.setSchema(scheme);
      /////////////////////////////////////

      //dbf.setValidating(true);
    } catch (SAXException ex) {
      // ОТКУДА тут SAX ??? Разборка схемы сделана через SAX
      System.err.println("ERROR PARSING XSD:" + ex.getMessage());
      return;
    }
    /////////////////////////////////////////////////////////

    // Источник данных - XML-файл
    InputSource is;
    try {
      is = new InputSource(new FileInputStream(fname));
    } catch (FileNotFoundException ex) {
      Logger.getLogger(XmlSax.class.getName()).log(Level.SEVERE, null, ex);
      return;
    }

    try {
      // Конкретный объект - парсер
      SAXParser prs = spf.newSAXParser();

      XMLReader rd = prs.getXMLReader();
      // настраивает парсер - добавляем объекты-обработчики событий
      MyXmlHandler myXmlHandler = new MyXmlHandler();
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

      System.out.println("result: " + myXmlHandler.names);
    } catch (ParserConfigurationException ex) {
      System.err.println("Config Exception:  " + ex.getMessage());
    } catch (IOException ex) {
      System.err.println("IO Exception:  " + ex.getMessage());
    } catch (SAXException ex) {
      System.err.println("SAX Exception:  " + ex.getMessage());
    }

  }
}
