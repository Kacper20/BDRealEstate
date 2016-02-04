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
public class MostFamousController {
    private TableView<District> table = new TableView<>();
    UseCaseWorker dbWorker;

    public void setupStage(Stage stage, UseCaseWorker worker) throws SQLException {
        this.dbWorker = worker;
        basicSetup(stage);
    };

    public void basicSetup(Stage stage) throws SQLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Najpopularniejsze dzielnice");
        stage.setWidth(800);
        stage.setHeight(500);

        final Label label = new Label("Wszystkie oferty");
        label.setFont(new Font("Arial", 20));

        TableColumn idCol = new TableColumn("Id dzielnicy");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<District, String>("id"));

        TableColumn nameCol = new TableColumn("Nazwa dzielnicy");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<District, String>("name"));

        TableColumn transCol = new TableColumn("Liczba transakcji");
        transCol.setMinWidth(100);
        transCol.setCellValueFactory(
                new PropertyValueFactory<District, String>("transactionsno"));

        table.getColumns().addAll(idCol, nameCol, transCol);
        table.setItems(getDistricts());

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("districtsettlements_controller.fxml"));
                    Parent root;
                    root = (Parent)loader.load();
                    DistrictSettlementsController tableController = (DistrictSettlementsController) loader.getController();
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

    private ObservableList<District> getDistricts() {
        return dbWorker.getDistrictData("10");
    }

    public static class District{
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
        private final SimpleStringProperty transactionsno;

        public District(String name, String id, String transactionsno) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.transactionsno = new SimpleStringProperty(transactionsno);
        }

        public District(ResultSet rs) throws Exception {
            this.id = new SimpleStringProperty(rs.getString("id"));
            this.name = new SimpleStringProperty(rs.getString("nazwa"));
            this.transactionsno = new SimpleStringProperty(rs.getString("Cnt"));
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

        public String getTransactionsno() {
            return transactionsno.get();
        }

        public void setTransactionsno(String fName) {
            this.transactionsno.set(fName);
        }
    }
}
