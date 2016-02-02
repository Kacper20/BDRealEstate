package ApplicationGeneric;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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

    public GenericTableController() throws SQLException {
        dbWorker = new GenericWorker();
    }


    public TableView genericTableView;


    public void setupStage(Stage stage) {

        final Label label = new Label("Generic operations on Real Estate Database");
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, genericTableView);

    }

    public void setupWithTableName() {
        final HBox hb = new HBox();

    }
//
    public ObservableList<String> getObservableOptions() throws SQLException {

        List<String> listOfTableNames = dbWorker.getTableNames().stream().map(TableName::getTableName)
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(listOfTableNames);
    }

}
