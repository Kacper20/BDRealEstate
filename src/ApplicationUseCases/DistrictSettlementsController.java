package ApplicationUseCases;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aaa on 2016-02-03.
 */
public class DistrictSettlementsController {
    private TableView<Settlement> table = new TableView<>();
    private TableView<Attribute> secondTable = new TableView<>();
    UseCaseWorker dbWorker;
    private String id;

    public void setupStage(Stage stage, UseCaseWorker worker, String id) throws SQLException {

        this.dbWorker = worker;
        this.id = id;
        basicSetup(stage);
    };

    public void basicSetup(Stage stage) throws SQLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Osiedla");
        stage.setWidth(800);
        stage.setHeight(1000);

        final Label label = new Label("Osiedla - (co zrobic?)");
        label.setFont(new Font("Arial", 20));

        TableColumn idCol = new TableColumn("Id osiedla");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Settlement, String>("id"));

        TableColumn titleCol = new TableColumn("Nazwa osiedla");
        titleCol.setMinWidth(100);
        titleCol.setCellValueFactory(
                new PropertyValueFactory<Settlement, String>("settlementName"));

        table.getColumns().addAll(idCol, titleCol);

        // second table

        TableColumn ndIdCol = new TableColumn("Id cechy");
        ndIdCol.setMinWidth(100);
        ndIdCol.setCellValueFactory(
                new PropertyValueFactory<Settlement, String>("id"));

        TableColumn ndTitleCol = new TableColumn("Nazwa cechy");
        ndTitleCol.setMinWidth(100);
        ndTitleCol.setCellValueFactory(
                new PropertyValueFactory<Settlement, String>("attributeName"));

        secondTable.getColumns().addAll(ndIdCol, ndTitleCol);//, provCol);

        Button giveItAttribute = new Button("Nadaj cechę!");
        giveItAttribute.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Settlement settlement = table.getSelectionModel().getSelectedItem();
                Attribute attribute = secondTable.getSelectionModel().getSelectedItem();
                dbWorker.setSettlementAttribute(settlement, attribute);
            }
        });

        table.setItems(getSettlements());
        secondTable.setItems(getAllSettlementAttributes());
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, secondTable, giveItAttribute);

        ((Group) scene.getRoot()).getChildren().addAll( vbox);

        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Attribute> getAllSettlementAttributes() {
        return dbWorker.getAllSettlementAttributes();
    }

    private ObservableList<Settlement> getSettlements() {
        return dbWorker.getSettlements(id);
    }

    public static class Settlement {
        private final SimpleStringProperty id;
        private final SimpleStringProperty settlementName;

        public Settlement(String id, String settlementName) {
            this.id = new SimpleStringProperty(id);
            this.settlementName = new SimpleStringProperty(settlementName);
        }

        public Settlement(ResultSet rs) throws Exception {
            this.id = new SimpleStringProperty(rs.getString("id"));
            this.settlementName = new SimpleStringProperty(rs.getString("onazwa"));
        }

        public String getId() {
            return id.get();
        }
        public void setId(String fName) {
            id.set(fName);
        }

        public String getSettlementName() {
            return settlementName.get();
        }

        public void setSettlementName(String settlementName) {
            this.settlementName.set(settlementName);
        }
    }

    public static class Attribute {
        private final SimpleStringProperty id;
        private final SimpleStringProperty attributeName;

        public Attribute(String id, String settlementName) {
            this.id = new SimpleStringProperty(id);
            this.attributeName = new SimpleStringProperty(settlementName);
        }

        public Attribute(ResultSet rs) throws Exception {
            this.id = new SimpleStringProperty(rs.getString("id"));
            this.attributeName = new SimpleStringProperty(rs.getString("nazwa"));
        }

        public String getId() {
            return id.get();
        }
        public void setId(String fName) {
            id.set(fName);
        }

        public String getAttributeName() {
            return attributeName.get();
        }

        public void setAttributeName(String attributeName) {
            this.attributeName.set(attributeName);
        }
    }
}
