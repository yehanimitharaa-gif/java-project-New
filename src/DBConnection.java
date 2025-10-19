import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=TransportDB;encrypt=false;";
    private static final String USER = "transport_user"; // SQL Server login
    private static final String PASSWORD = "StrongP@ssw0rd123!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


