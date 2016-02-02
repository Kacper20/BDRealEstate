package ApplicationGeneric;

import DBKit.ConnectionManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kacper on 02.02.2016.
 */
public class GenericTableController {


    GenericWorker dbWorker;


    HBox hBox;


    public TableView genericTableView;


    public void setupStage(Stage stage) throws SQLException {

        ConnectionManager.getInstance().connect();

        this.dbWorker = new GenericWorker();
        basicSetup(stage);
    }

    public void basicSetup(Stage stage) throws SQLException {

        hBox = new HBox();
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        final Label label = new Label("Generic operations on Real Estate Database");



        final ComboBox comboBox = new ComboBox();

        comboBox.getItems().addAll(getTableNames());

        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println("New Value: " + newValue);
            }
        });
        vbox.getChildren().addAll(label, comboBox);
        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
    }



    public void setupHierarchyForTableName(String tableName) {



    }
    public ObservableList<String> getTableNames() throws SQLException {
        List<String> listOfTableNames = dbWorker.getTableNames().stream().map(TableName::getTableName)
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(listOfTableNames);
    }

}
