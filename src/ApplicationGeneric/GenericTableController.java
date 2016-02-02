package ApplicationGeneric;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by kacper on 02.02.2016.
 */
public class GenericTableController {



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
//    public ObservableList<String> getObservableOptions() {
//
//
//
//
//    }

}
