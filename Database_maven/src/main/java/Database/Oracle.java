package Database;
import java.sql.*;

public class Oracle extends DButil{
    @Override
    Statement connect() throws SQLException {
        System.out.println("Oracle is Connected.");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_1622022",
                    "root", "");
            stmt = con.createStatement();
            return stmt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    void disconnect() throws SQLException {
        con.close();
        stmt.close();
    }

}
