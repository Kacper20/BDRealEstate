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
        launch(args);
    }
}
