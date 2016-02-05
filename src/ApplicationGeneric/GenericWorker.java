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
    public ResultSet selectConditionSQL(String tableName, String key, String value) throws SQLException {
        PreparedStatement ps;
        String stringQuery = String.format("SELECT * FROM %s WHERE %s = %s;", tableName, key, value);

        ps = c.prepareStatement(stringQuery);

        System.out.println(ps);

        ResultSet rs =  ps.executeQuery();

        return rs;
    }


    public List<TableColumnName> getPrimaryKeys(String tableName) throws SQLException {


        PreparedStatement ps = c.prepareStatement("SELECT " +
                "c.column_name, c.data_type " +
                "FROM " +
                "information_schema.table_constraints tc " +
                "JOIN information_schema.constraint_column_usage AS ccu USING (constraint_schema, constraint_name) " +
                "JOIN information_schema.columns AS c ON c.table_schema = tc.constraint_schema AND tc.table_name = c.table_name AND ccu.column_name = c.column_name " +
                "where constraint_type = 'PRIMARY KEY' and tc.table_name = ?;");
        ps.setString(1, tableName);
        ResultSet rs = ps.executeQuery();
        List<TableColumnName> tableColumnNameList = new ArrayList<>();
        while (rs.next()) {
            tableColumnNameList.add(new TableColumnName(rs));
        }

        return tableColumnNameList;

    }
    public void insertIntoDatabase(String insertQuery) throws SQLException {
        PreparedStatement ps = c.prepareStatement(insertQuery);
        ps.execute();
        ps.close();

    }
    public void updateDatabase(String updateQuery) throws SQLException {
        PreparedStatement ps = c.prepareStatement(updateQuery);
        ps.execute();
        ps.close();
    }
    public void deleteFromDatabase(String deleteQuery) throws SQLException {
        PreparedStatement ps = c.prepareStatement(deleteQuery);
        ps.execute();
        ps.close();
    }


}
