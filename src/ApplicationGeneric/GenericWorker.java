package ApplicationGeneric;

import DBKit.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kacper on 02.02.2016.
 */
public class GenericWorker {
    Connection c;
    public GenericWorker() throws SQLException {
        c = ConnectionManager.getInstance().getConn();
    }

    public Collection<TableColumn> getColumnsForTableName(String tableName) throws SQLException {

        PreparedStatement ps = c.prepareStatement("SELECT * FROM information_schema.columns WHERE table_name  = ?");
        ResultSet rs = ps.executeQuery();
        Collection <TableColumn> collection = new ArrayList<>();
        while (rs.next()) {
            collection.add(new TableColumn(rs));
        }
        rs.close();
        ps.close();

        return collection;
    }

    public Collection<TableName>  getTableNames() throws SQLException {

        PreparedStatement ps = c.prepareStatement("SELECT table_name" +
                "FROM information_schema.tables" +
                "ORDER BY table_schema,table_name;");
        ResultSet rs = ps.executeQuery();
        Collection <TableName> collection = new ArrayList<>();
        while (rs.next()) {
            collection.add(new TableName(rs));
        }
        rs.close();
        ps.close();
        return collection;
    }


}
