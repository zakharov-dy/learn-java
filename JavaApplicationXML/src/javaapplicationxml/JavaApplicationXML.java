package javaapplicationxml;

import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

public class JavaApplicationXML {
  public static void main(String[] args) throws SAXException, IOException, JAXBException {
//    JavaApplicationXML xml = new JavaApplicationXML("src/files/demo.svg");
    Dom xml = new Dom("src/files/clouds.svg");
    xml.viewDocument();
    xml.saveDemo();
    new Sax().process("src/files/UfaCenterSmall.xml", "src/files/osm.xsd");
//    new Sax().process("src/files/UfaCenter.xml", "src/files/osm.xsd");
    new Jaxb("src/files/UfaCenterSmall.xml");
  }
}
