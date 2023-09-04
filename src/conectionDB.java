import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectionDB {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/javaBankManagement";
            String user = "root";
            String password = "camacho123";

            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
