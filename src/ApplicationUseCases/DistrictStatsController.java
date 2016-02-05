package ApplicationUseCases;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aaa on 2016-02-03.
 */
public class DistrictStatsController {
    private TableView<DistrictStats> table = new TableView<DistrictStats>();
    UseCaseWorker dbWorker;
    private String cityId;

    public void setupStage(Stage stage, UseCaseWorker worker, String cityId) throws SQLException {

        this.dbWorker = worker;
        this.cityId = cityId;
        basicSetup(stage);
    };

    public void basicSetup(Stage stage) throws SQLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Oferty");
        stage.setWidth(800);
        stage.setHeight(500);

        final Label label = new Label("Dzielnice");
        label.setFont(new Font("Arial", 20));

        TableColumn idCol = new TableColumn("Id dzielnicy");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<DistrictStats, String>("id"));

        TableColumn titleCol = new TableColumn("Nazwa");
        titleCol.setMinWidth(100);
        titleCol.setCellValueFactory(
                new PropertyValueFactory<DistrictStats, String>("name"));

        TableColumn descrCol = new TableColumn("ile ofert");
        descrCol.setMinWidth(100);
        descrCol.setCellValueFactory(
                new PropertyValueFactory<DistrictStats, String>("howMany"));


        table.getColumns().addAll(idCol, titleCol, descrCol);
        table.setItems(getDistrictStats());

//        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("events_controller.fxml"));
//                    Parent root;
//                    root = (Parent)loader.load();
//                    EventsController tableController = (EventsController) loader.getController();
//                    tableController.setupStage(stage,dbWorker, table.getSelectionModel().getSelectedItem().getId() );
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        Button updateStats = new Button("Wrzuć statystyki!");
        updateStats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbWorker.insertDistrictStats(table.getItems());
            }
        });

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, updateStats, table);

        ((Group) scene.getRoot()).getChildren().addAll( vbox);

        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<DistrictStats> getDistrictStats() {
        return dbWorker.getDistrictStatsData(cityId);
    }

    public static class DistrictStats {
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
        private final SimpleStringProperty howMany;

        public DistrictStats(String name, String id, String descr, String howMany) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.howMany = new SimpleStringProperty(howMany);
        }

        public DistrictStats(ResultSet rs) throws Exception {
            this.id = new SimpleStringProperty(rs.getString("id"));
            this.name = new SimpleStringProperty(rs.getString("nazwa"));
            this.howMany = new SimpleStringProperty(rs.getString("Cnt"));
        }

        public String getId() {
            return id.get();
        }
        public void setId(String fName) {
            id.set(fName);
        }

        public String getName() {
            return name.get();
        }
        public void setName(String fName) {
            name.set(fName);
        }

        public String getHowMany() {
            return howMany.get();
        }
        public void setHowMany(String fName) {
            howMany.set(fName);
        }

    }
}
