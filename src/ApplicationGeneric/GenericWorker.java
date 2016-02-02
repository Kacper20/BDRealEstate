package ApplicationGeneric;

import DBKit.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by kacper on 02.02.2016.
 */
public class GenericWorker {
    Connection c;
    public GenericWorker() throws SQLException {
        c = ConnectionManager.getInstance().getConn();
    }
}
