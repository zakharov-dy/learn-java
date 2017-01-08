package javaapplicationxml;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javaapplicationxml.generated.*;
import javax.xml.bind.JAXBIntrospector;

public class Jaxb {
  Osm osm;
  ArrayList<Node> nodes = new ArrayList<Node>();
  ArrayList<Way> ways = new ArrayList<Way>();
  HashSet<String> busStopSet = new HashSet<String>();
  public HashMap<String, StreetData> streets = new HashMap<String, StreetData>();

  public Jaxb(String fn) throws JAXBException {
    unmarshal(fn);
    System.out.println(osm);
    List<Object> list = osm.getBoundOrUserOrPreferences();

    list.forEach((o) -> {
      if (o instanceof Node) {
        nodes.add((Node) o);
      } else if(o instanceof Way) {
        ways.add((Way) o);
      }
    });
    for (Node n : nodes) {
      ArrayList<Tag> tags = (ArrayList<Tag>) n.getTag();
      if (tags.size() < 2) {continue;}
      boolean isBusStop = false;
      for (Tag t : tags) {
        if (t.getV() != null && t.getV().equals("bus_stop")){
          isBusStop = true;
          continue;
        }
        if (isBusStop && t.getK() != null && t.getK().equals("name")){
            busStopSet.add(t.getV());
        }
      }
    }
    System.out.println(busStopSet.size());
    for (Way w : ways) {
      ArrayList<Tag> tags = new ArrayList<Tag>();
      w.getRest().forEach((Object t) -> {
        if (t instanceof Tag) { tags.add((Tag) t); }
      });
      if (tags.size() < 2) { continue; }
      boolean isHighway = false;
      for (Tag t : tags) {
        if (t.getK() != null && t.getK().equals("highway")) {
          isHighway = true;
          continue;
        }
        if (isHighway && t.getK() != null && t.getK().equals("name")) {
          streets.put(t.getV(), new StreetData());
        }
      }
    }
    System.out.println(streets.size());
    for (Way w : ways) {
      ArrayList<Tag> tags = new ArrayList<Tag>();
      w.getRest().forEach((Object t) -> {
        if (t instanceof Tag) {
          tags.add((Tag) t);
        }
      });
      if (tags.size() < 2) {
        continue;
      }
      for (Tag t : tags) {
        if (t.getK() != null && t.getK().equals("addr:street")) {
          if (!streets.containsKey(t.getV())) {
            System.out.println(w.getId());
          } else {
            streets.get(t.getV()).incHC();
          }
        }
      }
    }
  }

  private void unmarshal(String name) throws JAXBException {
    JAXBContext jxc = JAXBContext.newInstance("javaapplicationxml.generated");
    Unmarshaller u = jxc.createUnmarshaller();
//    JAXBElement je = (JAXBElement) u.unmarshal(new File(name));
    osm = (Osm) JAXBIntrospector.getValue(u.unmarshal(new File(name)));
//    osm = (Osm) je.getValue();
//    System.out.println(je.getValue());
  }

}
