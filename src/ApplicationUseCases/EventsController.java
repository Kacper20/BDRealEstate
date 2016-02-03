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
public class EventsController {
    private TableView<Event> table = new TableView<Event>();
    UseCaseWorker dbWorker;
    private String id;
    public void setupStage(Stage stage, UseCaseWorker worker, String id) throws SQLException {

        this.dbWorker = worker;
        this.id = id;
        basicSetup(stage);
    };

    public void basicSetup(Stage stage) throws SQLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Zdarzenia");
        stage.setWidth(800);
        stage.setHeight(500);

        final Label label = new Label("Zdarzenia - klinkij na odpowiednim aby usunąć je z bazy");
        label.setFont(new Font("Arial", 20));

        TableColumn idCol = new TableColumn("Id zdarzenia");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Event, String>("id"));

        TableColumn titleCol = new TableColumn("Opinia klienta");
        titleCol.setMinWidth(100);
        titleCol.setCellValueFactory(
                new PropertyValueFactory<Event, String>("opinion"));

        TableColumn provCol = new TableColumn("Imie agenta");
        provCol.setMinWidth(200);
        provCol.setCellValueFactory(
                new PropertyValueFactory<Event, String>("name"));

        TableColumn ofCol = new TableColumn("Nazwisko agenta");
        ofCol.setMinWidth(200);
        ofCol.setCellValueFactory(
                new PropertyValueFactory<Event, String>("lastname"));

        table.getColumns().addAll(idCol, titleCol, provCol,  ofCol);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                dbWorker.deleteEvent(table.getSelectionModel().getSelectedItem().getId());
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("events_controller.fxml"));
                    Parent root;
                    root = (Parent)loader.load();
                    EventsController tableController = (EventsController) loader.getController();
                    tableController.setupStage(stage,dbWorker, id );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        table.setItems(getEvents());
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll( vbox);

        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Event> getEvents() {
        return dbWorker.getEventsData(id);
    }

    public static class Event {
        private final SimpleStringProperty lastname;
        private final SimpleStringProperty name;
        private final SimpleStringProperty opinion;
        private final SimpleStringProperty id;

        public Event(String name, String lastname, String id, String opinion) {
            this.name = new SimpleStringProperty(name);
            this.lastname = new SimpleStringProperty(lastname);
            this.id = new SimpleStringProperty(id);
            this.opinion = new SimpleStringProperty(opinion);
        }

        public Event(ResultSet rs) throws Exception {
            this.id = new SimpleStringProperty(rs.getString("id"));
            this.lastname = new SimpleStringProperty(rs.getString("nazwisko"));
            this.name = new SimpleStringProperty(rs.getString("imie"));
            this.opinion = new SimpleStringProperty(rs.getString("opinia_klienta"));
        }

        public String getName() {
            return name.get();
        }
        public void setName(String provision) { this.name.set(provision); }


        public String getLastname() {
            return lastname.get();
        }
        public void setLastname(String fName) {
            lastname.set(fName);
        }

        public String getId() {
            return id.get();
        }
        public void setId(String fName) {
            id.set(fName);
        }

        public String getOpinion() {
            return opinion.get();
        }
        public void setOpinion(String fName) {
            opinion.set(fName);
        }

    }
}
