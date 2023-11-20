import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

  private static Connection connect() {
    String url = "jdbc:sqlite:messages.db";
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public void createUser(String nick) {
    String sql = "INSERT INTO User(nickname) VALUES(?)";

    try {
      Connection conn = connect();
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, nick);
      pstmt.executeUpdate();

      System.out.println("User created sucessfully!");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void allUser() {
    String sql = "SELECT rowid, nickname FROM User;";

    try (
      Connection conn = connect();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql)
    ) {
      while (rs.next()) {
        int id = rs.getInt("rowid");
        String nickname = rs.getString("nickname");
        System.out.println("Id: " + id + " | " + nickname );
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
