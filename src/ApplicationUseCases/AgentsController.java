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
public class AgentsController {

    private TableView<Agent> table = new TableView<Agent>();
    UseCaseWorker dbWorker;
    private TextField rate;
    private TextField money;
    private TextField transactions;

    public void setupStage(Stage stage, UseCaseWorker worker) throws SQLException {

        this.dbWorker = worker;
        basicSetup(stage);
    };

    public void basicSetup(Stage stage) throws SQLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Wybrani agenci");
        stage.setWidth(450);
        stage.setHeight(700);

        final Label label = new Label("Agenci");
        label.setFont(new Font("Arial", 20));

        rate = new TextField();
        rate.setPromptText("Wprowadź ocenę");
        rate.setPrefColumnCount(10);
        rate.getText();
        GridPane.setConstraints(rate, 0, 0);

        money = new TextField();
        money.setPromptText("Wprowadź minimalną wartosc transakcji");
        GridPane.setConstraints(money, 0, 1);

        transactions = new TextField();
        transactions.setPrefColumnCount(15);
        transactions.setPromptText("Wprowadź minimalna ilość transakcji");
        GridPane.setConstraints(transactions, 0, 2);

        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 0);

        Button clear = new Button("Clear");
        GridPane.setConstraints(clear, 1, 1);



        submit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((rate.getText() != null && !money.getText().isEmpty() && !transactions.getText().isEmpty())) {
                    table.setItems(loadAgents(rate.getText(),money.getText(), transactions.getText()));
                } else {
                    label.setText("Jedno z pól jest puste");
                }
            }
        });

//Setting an action for the Clear button
        clear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                rate.clear();
                transactions.clear();
                money.clear();
                label.setText("Agenci");
            }
        });


        TableColumn firstNameCol = new TableColumn("Imie");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Agent, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Nazwisko");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Agent, String>("lastName"));

        TableColumn idCol = new TableColumn("Id");
        idCol.setMinWidth(200);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Agent, String>("id"));

        table.getColumns().addAll( idCol, firstNameCol, lastNameCol);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("transaction_controller.fxml"));
                    Parent root;
                    root = (Parent)loader.load();
                    TransactionController tableController = (TransactionController) loader.getController();
                    tableController.setupStage(stage,dbWorker, table.getSelectionModel().getSelectedItem().getId() );
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println(table.getSelectionModel().getSelectedItem().getId());
            }
        });

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label,rate, money, transactions, submit, clear, table);

        ((Group) scene.getRoot()).getChildren().addAll( vbox);

        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Agent> loadAgents(String rate, String money, String transactions) {
        return dbWorker.getAgentsData(rate, money, transactions);
    }

    public static class Agent {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty id;

        public Agent(String fName, String lName, String id) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.id = new SimpleStringProperty(id);
        }

        public Agent(ResultSet rs) throws Exception {
            this.id = new SimpleStringProperty(rs.getString("id"));
            this.firstName = new SimpleStringProperty(rs.getString("imie"));
            this.lastName = new SimpleStringProperty(rs.getString("nazwisko"));
        }

        public String getFirstName() {
            return firstName.get();
        }
        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }
        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getId() {
            return id.get();
        }
        public void setId(String fName) {
            id.set(fName);
        }
    }
}
