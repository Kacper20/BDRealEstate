package ApplicationUseCases;

import DBKit.ConnectionManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
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
 * Created by Aaa on 2016-02-02.
 */
public class TransactionController {

    private TableView<Transaction> table = new TableView<Transaction>();
    UseCaseWorker dbWorker;
    private String id;
    public void setupStage(Stage stage, UseCaseWorker worker, String id) throws SQLException {

        this.dbWorker = worker;
        this.id = id;
        basicSetup(stage);
    };

    public void basicSetup(Stage stage) throws SQLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Transakcje wybranego agenta");
        stage.setWidth(800);
        stage.setHeight(500);

        final Label label = new Label("Transakcje agenta - wybierz transakcje zeby zwiekszyc prowizję dwukrotnie");
        label.setFont(new Font("Arial", 20));

        TableColumn idCol = new TableColumn("Id transakcji");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Transaction, String>("id"));

        TableColumn titleCol = new TableColumn("tytul oferty");
        titleCol.setMinWidth(100);
        titleCol.setCellValueFactory(
                new PropertyValueFactory<Transaction, String>("offerTitle"));

        TableColumn provCol = new TableColumn("Prowizja");
        provCol.setMinWidth(200);
        provCol.setCellValueFactory(
                new PropertyValueFactory<Transaction, String>("provision"));

        TableColumn ofCol = new TableColumn("Wartość oferty");
        ofCol.setMinWidth(200);
        ofCol.setCellValueFactory(
                new PropertyValueFactory<Transaction, String>("amount"));

        table.getColumns().addAll(idCol, titleCol, provCol,  ofCol);
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                dbWorker.updateProvision(table.getSelectionModel().getSelectedItem().getId());
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("transaction_controller.fxml"));
                    Parent root;
                    root = (Parent)loader.load();
                    TransactionController tableController = (TransactionController) loader.getController();
                    tableController.setupStage(stage,dbWorker, id );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        table.setItems(getTransactions());
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll( vbox);

        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Transaction> getTransactions() {
        return dbWorker.getTransactionData(id);
    }

    public static class Transaction {
        private final SimpleStringProperty provision;
        private final SimpleStringProperty amount;
        private final SimpleStringProperty offerTitle;
        private final SimpleStringProperty id;

        public Transaction(String provision, String offerTitle, String id, String amount) {
            this.provision = new SimpleStringProperty(provision);
            this.offerTitle = new SimpleStringProperty(offerTitle);
            this.id = new SimpleStringProperty(id);
            this.amount = new SimpleStringProperty(amount);
        }

        public Transaction(ResultSet rs) throws Exception {
            this.id = new SimpleStringProperty(rs.getString("id"));
            this.offerTitle = new SimpleStringProperty(rs.getString("tytul"));
            this.provision = new SimpleStringProperty(rs.getString("prowizja"));
            this.amount = new SimpleStringProperty(rs.getString("cena"));
        }

        public String getProvision() {
            return provision.get();
        }
        public void setProvision(String provision) { this.provision.set(provision); }


        public String getOfferTitle() {
            return offerTitle.get();
        }
        public void setOfferTitle(String fName) {
            offerTitle.set(fName);
        }

        public String getId() {
            return id.get();
        }
        public void setId(String fName) {
            id.set(fName);
        }

        public String getAmount() {
            return amount.get();
        }
        public void setAmount(String fName) {
            amount.set(fName);
        }

    }
}
