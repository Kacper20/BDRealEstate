package ApplicationUseCases;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class CityChooserController {
    private TableView<City> table = new TableView<City>();
    UseCaseWorker dbWorker;

    public void setupStage(Stage stage, UseCaseWorker worker) throws SQLException {

        this.dbWorker = worker;
        basicSetup(stage);
    };

    public void basicSetup(Stage stage) throws SQLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Oferty");
        stage.setWidth(800);
        stage.setHeight(500);

        final Label label = new Label("Wszystkie miasta");
        label.setFont(new Font("Arial", 20));

        TableColumn idCol = new TableColumn("Id miasta");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<City, String>("id"));

        TableColumn titleCol = new TableColumn("Nazwa");
        titleCol.setMinWidth(100);
        titleCol.setCellValueFactory(
                new PropertyValueFactory<City, String>("name"));

        TableColumn descrCol = new TableColumn("ile ofert");
        descrCol.setMinWidth(100);
        descrCol.setCellValueFactory(
                new PropertyValueFactory<City, String>("howMany"));


        table.getColumns().addAll(idCol, titleCol, descrCol);
        table.setItems(getCities());

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("districtstats_controller.fxml"));
                    Parent root;
                    root = (Parent)loader.load();
                    DistrictStatsController tableController = (DistrictStatsController) loader.getController();
                    tableController.setupStage(stage,dbWorker, table.getSelectionModel().getSelectedItem().getId() );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll( vbox);

        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<City> getCities() {
        return dbWorker.getCitiesData();
    }

    public static class City {
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
        private final SimpleStringProperty howMany;

        public City(String name, String id, String descr, String howMany) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.howMany = new SimpleStringProperty(howMany);
        }

        public City(ResultSet rs) throws Exception {
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
