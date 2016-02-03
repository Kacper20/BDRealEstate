package ApplicationUseCases;

import ApplicationGeneric.GenericTableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Aaa on 2016-02-02.
 */
public class UseCasesApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("use_case_controller.fxml"));
        Parent root = (Parent)loader.load();
        UseCaseController tableController = (UseCaseController) loader.getController();

        tableController.setupStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
