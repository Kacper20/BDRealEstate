package ApplicationGeneric;

import DBKit.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by kacper on 02.02.2016.
 */
public class GenericWorker {
    Connection c;
    public GenericWorker() throws SQLException {
        Connection c = ConnectionManager.getInstance().getConn();
        this.c = c;
    }

    public List<TableColumnName> getColumnsForTableName(String tableName) throws SQLException {

        PreparedStatement ps = c.prepareStatement("SELECT * FROM information_schema.columns WHERE table_name  = ?");
        ps.setString(1, tableName);
        ResultSet rs = ps.executeQuery();
        List <TableColumnName> collection = new ArrayList<>();
        while (rs.next()) {
            collection.add(new TableColumnName(rs));
        }
        rs.close();
        ps.close();

        return collection;
    }

    public Collection<TableName>  getTableNames() throws SQLException {

        PreparedStatement ps;
        ps = c.prepareStatement("SELECT table_name FROM information_schema.tables " +
                "WHERE table_schema = 'public'\n" +
                "AND table_type='BASE TABLE'");
        ResultSet rs = ps.executeQuery();
        Collection <TableName> collection = new ArrayList<>();
        while (rs.next()) {
            collection.add(new TableName(rs));
        }
        rs.close();
        ps.close();
        return collection;
    }
    public ResultSet selectGeneralSQL(String tableName) throws SQLException {
        PreparedStatement ps;
        System.out.println(tableName);
        String stringQuery = String.format("SELECT * FROM %s;", tableName);

        ps = c.prepareStatement(stringQuery);
        ResultSet rs =  ps.executeQuery();

        int i = 0;
        return rs;
    }
    public void insertIntoDatabase(String insertQuery) throws SQLException {
        PreparedStatement ps = c.prepareStatement(insertQuery);
        ps.execute();
        ps.close();

    }


}
