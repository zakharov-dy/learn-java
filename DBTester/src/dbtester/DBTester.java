package dbtester;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DBTester {
  Connection conn;
  private static final Logger logger = Logger.getLogger(DBTester.class.getName());
  public DBTester() throws SQLException {
    conn = connectToDB();
  }
  public static void main(String[] args) throws SQLException {
    DBTester dbt = new DBTester();
//    Connection connect = dbt.connectToDB();
    dbt.doWork();
  }
  void doWork() throws SQLException {
//    createTablesIfNeeded();
//    viewGroups();
//    viewItems();
//    System.out.println(getTitleID("ITEMGROUP", "КОМПЬЮТЕРЫ"));
//    viewItemsInGroup(3);
//    System.out.println(addItemToGroup("Mac", "КОМПЬЮТЕРЫ"));
//    viewItems();
//    createItemsAcrossFile("src/files/items.txt");
    updateGroupsAcrossFile("src/files/groups.txt");
  }
  private Connection connectToDB() throws SQLException {
    String driverName = "org.apache.derby.jdbc.ClientDriver40";
    try {
      Class.forName(driverName).newInstance();
    } catch (Exception e) {
      logger.log(Level.SEVERE, "ERROR: Driver <{0}> not found", driverName);
      System.exit(0);
    }
    
    String dbUrl = "jdbc:derby://localhost:1527/groupdb";
    
    Properties connInfo = new Properties();
    connInfo.put("user", "java");
    connInfo.put("password", "java");
    
    Connection conn = null;
    conn = DriverManager.getConnection(dbUrl, connInfo);
    return conn;
  }
  HashSet<String> viewGroups() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("SELECT ID, TITLE FROM ITEMGROUP");
    ResultSet rst = stmt.executeQuery();
    System.out.println("rst= " + rst.toString());
    HashSet<String> res = new HashSet<String>();
    while (rst.next()) {
      System.out.printf("row %3d:", rst.getRow());
      int id;
      String title;
      id = rst.getInt(1);
      title = rst.getString(2);
      System.out.printf("%10d | %30s\n", id, title);
      res.add(title);
    }
    stmt.close();
    return res;
  }
  void viewItems() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("SELECT ID, TITLE, GROUPID FROM ITEM");
    ResultSet rst = stmt.executeQuery();
    System.out.println("rst= " + rst.toString());
    rst.getRow();
    while (rst.next()) {
      System.out.printf("row %3d:", rst.getRow());
      int id, groupid;
      String title;
      id = rst.getInt(1);
      title = rst.getString(2);
      groupid = rst.getInt(3);
      System.out.printf("%10d | %30s | %10d\n", id, title, groupid);
    }
    stmt.close();
  }
  int getTitleID(String table, String key) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("SELECT ID FROM " + table + " WHERE TITLE LIKE ?");
    stmt.setString(1, key);
    ResultSet rst = stmt.executeQuery();
    System.out.println("rst= " + rst.toString());
    rst.next();
    return rst.getInt(1);
  }
  void viewItemsInGroup(int groupid) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("SELECT TITLE FROM ITEM WHERE GROUPID = ?");
    stmt.setInt(1, groupid);
    ResultSet rst = stmt.executeQuery();
    System.out.println("rst= " + rst.toString());
    while (rst.next()) {
      System.out.printf("row %3d:", rst.getRow());
      String title = rst.getString(1);
      System.out.printf("%30s\n", title);
    }
  }
  void viewItemsInGroup(String groupname) throws SQLException {
    viewItemsInGroup(getTitleID("ITEMGROUP", groupname));
  }
  boolean selectIsException(String query) {
    try {
      PreparedStatement stmt = conn.prepareStatement(query);
    } catch (SQLException ex) {
//      Logger.getLogger(DBTester.class.getName()).log(Level.SEVERE, null, ex);
      return true;
    }
    return false;
  }
  void createTablesIfNeeded() throws SQLException {
    if (selectIsException("SELECT * FROM ITEMGROUP")) {
      String itemGroupCreator = "CREATE TABLE ITEMGROUP "
      +  "(ID INTEGER PRIMARY KEY generated always as identity, "
      +  "TITLE VARCHAR(100)  UNIQUE NOT NULL) ";
      PreparedStatement statement = conn.prepareStatement(itemGroupCreator);
      statement.execute();
      System.out.println("table ITEMGROUP created");
      String itemGroupSetter = "INSERT INTO ITEMGROUP(TITLE) VALUES(?) ";
      statement = conn.prepareStatement(itemGroupSetter);
      String[] itemNames = {"КОМПЬЮТЕРЫ", "ТЕЛЕФОНЫ", "ТЕЛЕВИЗОРЫ", "ПЛЕЕРЫ"};
      for (int i = 0; i < itemNames.length; i++) {
        statement.setString(1, itemNames[i]);
        statement.executeUpdate();
      }
    } else {
      System.out.println("table ITEMGROUP is exist");
    }
    if (selectIsException("SELECT * FROM ITEM")) {
      String itemGroupCreator = "CREATE TABLE ITEM (ID INTEGER PRIMARY KEY generated always as identity,\n"
          + "TITLE VARCHAR(100) UNIQUE NOT NULL, GROUPID INTEGER,\n"
          + "FOREIGN KEY (GROUPID) REFERENCES ITEMGROUP(ID) ) ";
      PreparedStatement statement = conn.prepareStatement(itemGroupCreator);
      statement.execute();
      System.out.println("table ITEM created");
      String itemSetter = "INSERT INTO ITEM(TITLE, GROUPID) VALUES(?,?) ";
      statement = conn.prepareStatement(itemSetter);
      int[] itemGroupIds = {1, 1, 2, 2, 3, 3};
      String[] itemNames = {"Apple", "Dell", "HTC", "Nokia", "LG", "SAMSUNG"};
      for (int i = 0; i < itemGroupIds.length; i++) {
        statement.setString(1, itemNames[i]);
        statement.setInt(2, itemGroupIds[i]);
        statement.executeUpdate();
      }
    } else {
      System.out.println("table ITEM is exist");
    }
  }
  boolean addItemToGroup(String itemName, String groupName) {
    try {
      int id = getTitleID("ITEMGROUP", groupName);
      String itemSetter = "INSERT INTO ITEM(TITLE, GROUPID) VALUES(?,?) ";
      PreparedStatement statement = conn.prepareStatement(itemSetter);
      statement.setString(1, itemName);
      statement.setInt(2, id);
      statement.executeUpdate();
    } catch (SQLException ex) {
//      Logger.getLogger(DBTester.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    return true;
  }
  boolean removeItemFromGroup(String itemName, String groupName) {
    try {
      String itemSetter = "DELETE FROM ITEM WHERE TITLE=?";
      PreparedStatement statement = conn.prepareStatement(itemSetter);
      statement.setString(1, itemName);
//      statement.setInt(2, getTitleID("ITEMGROUP", groupName));
      statement.executeUpdate();
    } catch (SQLException ex) {
//      Logger.getLogger(DBTester.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    return true;
  }
  void createItemsAcrossFile(String path) throws SQLException{
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf8"));
      String s;
      conn.setAutoCommit(false);
      while ((s = br.readLine()) != null) {
        String[] split = s.split("\\s+");
        boolean result;
        if (split[2].equals("+")) {
          result = addItemToGroup(split[0], split[1]);
        } else {
          result = removeItemFromGroup(split[0], split[1]);
        }
        if (!result) {
          System.out.println("Не вышло c " + s);
          conn.rollback();
          return;
        }
      }
      conn.commit();
      conn.setAutoCommit(true);
    } catch (IOException ex) {
      Logger.getLogger(DBTester.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  void updateGroupsAcrossFile(String path) throws SQLException {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf8"));
      String s;
      conn.setAutoCommit(false);
      HashSet<String> addSet = new HashSet<String>();
      HashSet<String> rmSet = new HashSet<String>();
      while ((s = br.readLine()) != null) {
        if (s.substring(0, 1).equals("+")) {
          addSet.add(s.substring(1));
        } else {
          rmSet.add(s.substring(1));
        }
      }
      HashSet<String> groups = viewGroups();
      addSet = addSet.stream()
          .filter((String group) -> !groups.contains(group))
          .collect(Collectors.toCollection(HashSet::new));
      rmSet = rmSet.stream()
          .filter((String group) -> groups.contains(group))
          .collect(Collectors.toCollection(HashSet::new));
      
      String queryExterminator = "DELETE FROM ITEMGROUP WHERE TITLE=?";
      String querySetter = "INSERT INTO ITEMGROUP(TITLE) VALUES(?) ";
      PreparedStatement statementExterminator = conn.prepareStatement(queryExterminator);
      PreparedStatement statementSetter = conn.prepareStatement(querySetter);
      for (String group : rmSet) {
        statementExterminator.setString(1, group);
        statementExterminator.executeUpdate();
      }
      for (String group : addSet) {
        statementSetter.setString(1, group);
        statementSetter.executeUpdate();
      }
      conn.commit();
      conn.setAutoCommit(true);
    } catch (IOException ex) {
      conn.rollback();
      conn.setAutoCommit(true);
      Logger.getLogger(DBTester.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
