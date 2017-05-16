package database;
import java.sql.*;

public class DBController {

  // JDBC Treiber und URL zur Datenbank
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB = "jdbc:mysql://duemmer.informatik.uni-oldenburg.de:47099/db_test";

  //  Datenbank-Daten
  static final String USERNAME = "safariman";
  static final String PASSWORD = "blablabla";

  // DB-Objekt
  private Connection conn = null;

  /**
   * Erstellt ein neues Datenbankcontroller-Objekt
   * Setzt Datenbank-Treiber
   */
  public DBController() {
    try {
      Class.forName(JDBC_DRIVER);
    } catch (Exception e) {
      // Handle errors for Class.forName
      e.printStackTrace();
    }
  }

  /**
   * Baut Verbindung zur Datenbank auf
   *
   * @return true, wenn Verbindung aufgebaut wurde und besteht
   */
  public boolean connect() {
    try {
      conn = DriverManager.getConnection(DB, USERNAME, PASSWORD);

      if (this.isConnected()) {
        System.out.println("Connected to database");
        return true;
      }
    } catch (SQLException se) {
      // TODO Auto-generated catch block
      se.printStackTrace();
    }

    return false;
  }

  /**
   * Schlie�t die Verbindung
   *
   * @return true, wenn die Verbindung geschlossen wurde und nicht mehr besteht
   */
  public boolean disconnect() {
    try {
      // Wenn Verbindung besteht
      if (this.isConnected()) {
        // Schlie�en der Verbindung
        conn.close();

        // Wenn Schlie�en der Verbindung erfolgreich nicht mehr besteht, true zur�ckgeben
        if (!this.isConnected()) {
          return true;
        }
      } else {
        // Wenn Verbindung schon geschlossen, ebenfalls true zur�ckgeben
        return true;
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return false; // Verbindung konnte nicht geschlossen werden
  }

  /**
   * �berpr�ft, ob die Verbindung zur Datenbank besteht
   *
   * @return true, wenn Verbindung besteht
   */
  public boolean isConnected() {
    try {
      if (!this.conn.isClosed()) {
        return true;
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return false;
  }

  /**
   * Z�hlt die Anzahl der Resultate einer Abfrage
   *
   * @param  result  ResultSet, in dem die Eintr�ge gez�hlt werden soll
   * @return Anzahl der Eintr�ge in result
   */
  public int countResults(ResultSet result) {
    int count = 0;
    try {
      result.last(); // Zeiger auf den letzten Eintrag setzen
      count = result.getRow(); // Nummer des letzten Eintrags
      result.beforeFirst(); // Zeiger vor den ersten Eintrag zur�cksetzen
    } catch (Exception e) {
      return 0;
    }
    return count;
  }

  /**
   * Pr�ft, ob Wert value in Spalte column in Tabelle table existiert
   *
   * @param  table  Zu �berpr�fende Tabelle
   * @param  column  Spalte der Tabelle
   * @param  value  Wert, auf den �berpr�ft werden soll
   * @return true, wenn Eintrag existiert
   */
  public boolean exists(String table, String column, String value) {
    PreparedStatement stmt = null;

    try {
      // Query ausf�hren
      stmt = conn
          .prepareStatement("SELECT id FROM " + table + " WHERE LOWER(" + column + ") = LOWER(?)");
      stmt.setString(1, value);
      ResultSet result = stmt.executeQuery();

      // Ergebnisse z�hlen
      int rowCount = this.countResults(result);

      // Query & Ergebnis freigeben
      result.close();
      stmt.close();

      // Wenn Anzahl gr��er als 0 ist, true zur�ckgeben
      if (rowCount > 0) {
        return true;
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return false;
  }

  /**
   * F�hrt ausschlie�lich SELECT Queries durch und gibt ein ResultSet zur�ck
   * UNGENUTZT
   *
   * @param  query  Datenbank-Query
   * @return Datensatz als ResultSet aus der Datenbank
   */
  public ResultSet executeQuery(String query) {
    try {
      Statement stmt = conn
          .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet result = stmt.executeQuery(query);

      return result;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }

  /**
   * F�hrt INSERT, DELETE und UPDATE Queries durch und gibt ein ResultSet zur�ck
   * UNGENUTZT
   *
   * @param  query  Datenbank-Query
   * @return ResultSet mit den Daten aus der Datenbank
   */
  public int executeUpdate(String query) {
    try {
      Statement stmt = conn.createStatement();
      int result = stmt.executeUpdate(query);
      stmt.close();

      return result;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return 0;
  }

  /**
   * Beendet eine Query und das zugeh�rige ResultSet
   * UNGENUTZT
   *
   * @param  result  Das von executeQuery zuvor zur�ckgegebene ResultSet
   */
  public void closeQuery(ResultSet result) {
    try {
      result.getStatement().close();
      result.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * @return Aktuelle Verbindung
   */
  public Connection getConnection() {
    return this.conn;
  }
}