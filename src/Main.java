import DBKit.ConnectionManager;
import Generator.DataGenerator;

import java.sql.SQLException;

/**
 * Created by kacper on 01.02.2016.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        ConnectionManager.getInstance().connect();
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateDatabase();
    }
}
