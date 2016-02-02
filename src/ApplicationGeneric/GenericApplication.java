package ApplicationGeneric;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by kacper on 02.02.2016.
 */
public class GenericApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = (Parent)loader.load();




        GenericTableController tableController = (GenericTableController) loader.getController();




        primaryStage.setTitle("Generic Database");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();





    }

    public static void main(String[] args) {

    }
}