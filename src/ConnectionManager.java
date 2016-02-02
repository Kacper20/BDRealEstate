import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kacper on 31.01.2016.
 */
public class ConnectionManager {

    private static ConnectionManager instance;
    private Connection conn;

    private String username = "kacper";
    private String password = "kacper";
    private String url = "jdbc:postgresql://127.0.0.1:5432/bdbase";

    public static ConnectionManager getInstance() throws SQLException {
        if (instance == null) instance = new ConnectionManager();
        return instance;
    }

    private ConnectionManager() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    public Connection getConn() {
        return conn;
    }

    public void connect() throws SQLException {
        System.out.println("Otwieram połączenie...");
        conn = DriverManager.getConnection(url, username, password);
    }

    public void close() throws SQLException {
        System.out.println("Zamykam połączenie...");
        conn.close();
        conn = null;
    }
}
