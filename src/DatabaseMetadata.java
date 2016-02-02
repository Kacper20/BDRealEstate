import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by kacper on 01.02.2016.
 */
public class DatabaseMetadata {



    Connection c;

    public DatabaseMetadata() throws SQLException {
        this.c = ConnectionManager.getInstance().getConn();

    }

    public List<String> getColumnNamesForTable(String tableName) throws SQLException {

       PreparedStatement s = c.prepareStatement(
                    "SELECT *" + "FROM information_schema.columns" +
                            "WHERE table_name   = '?'");
            s.setString(1, tableName);

        ResultSet rs = s.executeQuery();
        List<String> collection = new ArrayList<String>();
        System.out.println(rs);
//        while (rs.next()) collection.add(new NewsDTO(rs));
        rs.close();
        s.close();
        return collection;

    }
}
