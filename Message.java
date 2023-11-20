import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Message {

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

  public void sendMessage(String sender, String recipient, String text) {
    String sql =
      "INSERT INTO Messages(idSender, idRecipient, text) VALUES " +
      "((SELECT rowid FROM User WHERE nickname = ?), (SELECT rowid FROM User WHERE nickname = ?), ?)";

    try {
      Connection conn = connect();
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, sender);
      pstmt.setString(2, recipient);
      pstmt.setString(3, text);
      pstmt.executeUpdate();

      System.out.println("Message was sucessfully sent!");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void readAllMessageToUser(String nickname) {
    String sql;

    sql =
      "SELECT User.nickname, Messages.text FROM Messages"
      +  " INNER JOIN User ON Messages.idRecipient = User.rowid"
      +  " WHERE Messages.idRecipient = ( SELECT rowid FROM User WHERE nickname = ?);";

    try {
      Connection conn = connect();
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, nickname);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        String nick = rs.getString("nickname");
        String text = rs.getString("text");
        System.out.println("User: " + nick + " | " + text);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
