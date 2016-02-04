package ApplicationUseCases;

import ApplicationGeneric.GenericWorker;
import DBKit.ConnectionManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Aaa on 2016-02-02.
 */
public class UseCaseController {

    UseCaseWorker dbWorker;
    Stage stage;

    public void setupStage(Stage stage) throws Exception {

        ConnectionManager.getInstance().connect();
        this.stage = stage;
        this.dbWorker = new UseCaseWorker();
        basicSetup(stage);
    }

    ;

    public void basicSetup(Stage stage) throws Exception {
        Scene scene = new Scene(new Group());
        stage.setTitle("Przypadki uzycia");
        stage.setWidth(450);
        stage.setHeight(500);

        final Label label = new Label("Akcje");
        label.setFont(new Font("Arial", 20));
        Button buttonTr = new Button("Agenci-transakcje");
        buttonTr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("agents_controller.fxml"));
                    Parent root;
                    root = (Parent) loader.load();
                    AgentsController tableController = (AgentsController) loader.getController();
                    tableController.setupStage(stage, dbWorker);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        Button buttonOf = new Button("Oferty - zdarzenia");
        buttonOf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("offers_controller.fxml"));
                    Parent root;
                    root = (Parent) loader.load();
                    OffersController tableController = (OffersController) loader.getController();
                    tableController.setupStage(stage, dbWorker);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        Button buttonDs = new Button("Dzielnice - najpopularniejsze");
        buttonDs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("mostfamous_controller.fxml"));
                    Parent root;
                    root = (Parent) loader.load();
                    MostFamousController tableController = (MostFamousController) loader.getController();
                    tableController.setupStage(stage, dbWorker);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, buttonTr, buttonOf, buttonDs);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
