package ApplicationGeneric;

import java.sql.ResultSet;

/**
 * Created by kacper on 02.02.2016.
 */
public class TableColumn {

    private String columnName;



    public TableColumn(ResultSet rs) {

    }


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
