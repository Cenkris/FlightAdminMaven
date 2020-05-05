package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String databaseName = "flightdb";
    private static final String url = "jdbc:mysql://localhost:3306/" + databaseName + "?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";
    private static Connection connection;

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return connection;
    }

}
