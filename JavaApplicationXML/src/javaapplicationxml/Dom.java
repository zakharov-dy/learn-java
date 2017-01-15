package javaapplicationxml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Dom {
  Document doc;
  public Dom(String file) throws SAXException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setNamespaceAware(false);
    dbf.setIgnoringElementContentWhitespace(true);
    DocumentBuilder db = null;
    try {
      db = dbf.newDocumentBuilder();
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(JavaApplicationXML.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      doc = db.parse(file);
    } catch (IOException ex) {
      Logger.getLogger(JavaApplicationXML.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void viewDocument() {
    Node n = doc.getDocumentElement();
    lookAttrs(n);
    lookChildren(n, 0);
  }

  private void lookChildren(Node n, int level) {
    NodeList nl = n.getChildNodes();
    String space = "                                         ".substring(0, level);
    int num = nl.getLength();
    Node curNode;
    for (int i = 0; i < num; i++) {
      curNode = nl.item(i);
      if (curNode.getNodeType() == Node.ELEMENT_NODE) {
        switch (curNode.getNodeName()) {
          case "circle":
            System.out.printf(space + "ELEMENT: <%s>\n", curNode.getNodeName());
            lookAttrs(curNode);
            Element newElement = doc.createElement("circle");
            curNode.getParentNode().insertBefore(newElement, curNode);
            i++;
            break;
          case "rect":
            ((Element) curNode).setAttribute("style", "fill: black;");
            System.out.printf(space + "ELEMENT: <%s>\n", curNode.getNodeName());
            lookAttrs(curNode);
            break;
          case "path":
            ((Element) curNode).setAttribute("style", "fill: red;");
            break;
        }
        lookChildren(curNode, level + 1);
      }
    }
  }

  private void lookAttrs(Node n) {
    NamedNodeMap attrs = n.getAttributes();
    if (attrs == null) { return; }
    int num = attrs.getLength();
    if (num == 0) { return; }

    Node curNode;
    System.out.print("[");
    for (int i = 0; i < num; i++) {
      curNode = attrs.item(i);
      System.out.printf("%s=%s ", curNode.getNodeName(), curNode.getNodeValue());
    }
    System.out.printf("]\n");
  }

  public void saveDemo() throws IOException {

    Result sr = new StreamResult(new FileWriter("src/files/out.xml"));
    Result sr2 = new StreamResult(System.out);
    DOMSource domSource = new DOMSource(doc);

    Transformer tr;
    try {
      tr = TransformerFactory.newInstance().newTransformer();
      tr.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      tr.setOutputProperty(OutputKeys.INDENT, "yes"); // отступы
      tr.transform(domSource, sr); // в файл
      tr.transform(domSource, sr2); // на экран
    } catch (TransformerException ex) {
      Logger.getLogger(JavaApplicationXML.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
