package ApplicationGeneric;

import DBKit.SQLQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kacper on 02.02.2016.
 */
public class GenericApplication extends Application {


    @Override

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("generic_table_controller.fxml"));
        Parent root = (Parent)loader.load();

        GenericTableController tableController = (GenericTableController) loader.getController();

        primaryStage.setTitle("Generic Database");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();

        tableController.setupStage(primaryStage);





    }

    public static void main(String[] args) {

        Map<String, String> columnValueMappingForSet = new HashMap<String, String>();
        columnValueMappingForSet.put("FIRST_NAME", "'DEBOPAM'");
        columnValueMappingForSet.put("LAST_NAME", "'PAL'");
        columnValueMappingForSet.put("DESIGNATION", "'Software Developer'");
        columnValueMappingForSet.put("ORGANIZATION", "'NIC'");

        Map<String, String> columnValueMappingForCondition = new HashMap<String, String>();
        columnValueMappingForCondition.put("EMPLOYEE_NO", "201400002014");

// Getting UPDATE SQL Query...
        String updateSQL = SQLQuery.updateSQL("EMPLOYEE", columnValueMappingForSet, columnValueMappingForCondition);

        launch(args);
    }
}
