package ApplicationGeneric;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kacper on 02.02.2016.
 */

enum ColumnType {
    CHARACTER, INTEGER




}

public class TableColumnName {

    private String columnName;

    private ColumnType columnType;



    public TableColumnName(ResultSet rs) throws SQLException {
        this.columnName = rs.getString("column_name");
        this.columnType = columnTypeForType(rs.getString("data_type"));
    }

    public static ColumnType columnTypeForType(String type) {
        if (type.equals("integer")) {
            return ColumnType.INTEGER;
        }
        else if (type.equals("character varying") || type.equals("character")) {
            return ColumnType.CHARACTER;
        }
        assert(false);
        return ColumnType.CHARACTER;

    }

    public static String formattedValueBasedOnType(String value, ColumnType type) {
        String base = value;
        if (type == ColumnType.CHARACTER) {
            base = "'" + base + "'";
        }
        return base;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
