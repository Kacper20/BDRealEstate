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
public class OffersController {
    private TableView<Offer> table = new TableView<Offer>();
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

        final Label label = new Label("Dzielnice");
        label.setFont(new Font("Arial", 20));

        TableColumn idCol = new TableColumn("Id oferty");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Offer, String>("id"));

        TableColumn titleCol = new TableColumn("Tytul oferty");
        titleCol.setMinWidth(100);
        titleCol.setCellValueFactory(
                new PropertyValueFactory<Offer, String>("name"));

        TableColumn descrCol = new TableColumn("Opis oferty");
        descrCol.setMinWidth(100);
        descrCol.setCellValueFactory(
                new PropertyValueFactory<Offer, String>("descr"));

        TableColumn eventsCol = new TableColumn("Ilosc zdarzen");
        eventsCol.setMinWidth(100);
        eventsCol.setCellValueFactory(
                new PropertyValueFactory<Offer, String>("events"));

        table.getColumns().addAll(idCol, titleCol, descrCol, eventsCol);
        table.setItems(getOffers());

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("events_controller.fxml"));
                    Parent root;
                    root = (Parent)loader.load();
                    EventsController tableController = (EventsController) loader.getController();
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

    private ObservableList<Offer> getOffers() {
        return dbWorker.getOffersData();
    }

    public static class Offer{
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
        private final SimpleStringProperty descr;
        private final SimpleStringProperty events;

        public Offer(String name, String id, String descr, String events) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.descr = new SimpleStringProperty(descr);
            this.events = new SimpleStringProperty(events);
        }

        public Offer(ResultSet rs) throws Exception {
            this.id = new SimpleStringProperty(rs.getString("id"));
            this.name = new SimpleStringProperty(rs.getString("tytul"));
            this.descr = new SimpleStringProperty(rs.getString("opis"));
            this.events = new SimpleStringProperty(rs.getString("Zdarzenia"));
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

        public String getDescr() {
            return descr.get();
        }
        public void setDescr(String fName) {
            descr.set(fName);
        }

        public String getEvents() {
            return events.get();
        }
        public void setEvents(String fName) {
            events.set(fName);
        }

    }
}
