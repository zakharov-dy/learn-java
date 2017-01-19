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
    System.out.println();
    List<Object> list = osm.getBoundOrUserOrPreferences();

    list.forEach((o) -> {
      if (o instanceof Node) {
        nodes.add((Node) o);
      } else if(o instanceof Way) {
        ways.add((Way) o);
      }
    });
    for (Node n : nodes) {
      List<Tag> tags = n.getTag();
      if (tags.size() < 2) {continue;}
      boolean isBusStop = false;
      String name = null;
      for (Tag t : tags) {
        if (t.getV() != null && t.getV().equals("bus_stop")){ isBusStop = true; }
        else if (t.getK() != null && t.getK().equals("name")) { name = t.getV(); }
      }
      if (isBusStop && name != null ){ busStopSet.add(name); }
    }
    for (Way w : ways) {
      ArrayList<Tag> tags = getTagsFromW(w);
      if (tags.size() < 2) { continue; }
      boolean isHighway = false;
      String name = null;
      String k = null;
      for (Tag t : tags) {
        if (t.getK() != null) { k = t.getK(); } else { continue; }
        if (k.equals("highway")) { isHighway = true;}
        else if (k.equals("name")) { name = t.getV(); }
      }
      if (isHighway && name != null) {
        if (!streets.containsKey(name)) { streets.put(name, new StreetData());}
        streets.get(name).incPC();
      }

    }
    System.out.println("JAXB Остановки:");
    busStopSet.forEach((String s) -> {
      System.out.println(s);
    });
    System.out.println();
    System.out.println("JAXB Дома без улиц");
    for (Way w : ways) {
      ArrayList<Tag> tags = getTagsFromW(w);
      if (tags.size() < 2) { continue; }
      for (Tag t : tags) {
        if (t.getK() != null && t.getK().equals("addr:street")) {
          if (!streets.containsKey(t.getV())) { 
            System.out.println(w.getId() + " " + t.getV()); 
          }
          else { streets.get(t.getV()).incHC(); }
        }
      }
    }
    System.out.println();
    System.out.println("JAXB Map<String, StreetData>");
    streets.forEach((String s, StreetData data) -> {
      System.out.println(s + " " + data.getPC() + " " + data.getHC());
    });
  }

  private ArrayList<Tag> getTagsFromW(Way w) {
    ArrayList<Tag> tags = new ArrayList<Tag>();
    w.getRest().forEach((Object t) -> {
      if (t instanceof Tag) { tags.add((Tag) t); }
    });
    return tags;
  }

  private void unmarshal(String name) throws JAXBException {
    JAXBContext jxc = JAXBContext.newInstance("javaapplicationxml.generated");
    Unmarshaller u = jxc.createUnmarshaller();
    osm = (Osm) JAXBIntrospector.getValue(u.unmarshal(new File(name)));
  }
}
