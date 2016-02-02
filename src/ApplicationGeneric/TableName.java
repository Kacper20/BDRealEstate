package ApplicationGeneric;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kacper on 02.02.2016.
 */
public class TableName {

    private String tableName;


    public TableName(ResultSet rs) throws SQLException {
        this.tableName = rs.getString("table_name");

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
