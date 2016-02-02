package ApplicationGeneric;

import DBKit.ConnectionManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by kacper on 02.02.2016.
 */
public class GenericTableController {



    private ObservableList<ObservableList> data;
    GenericWorker dbWorker;


    HBox hBox;


    public TableView genericTableView = new TableView();


    public void setupStage(Stage stage) throws SQLException {

        ConnectionManager.getInstance().connect();

        this.dbWorker = new GenericWorker();
        basicSetup(stage);
    }

    public void basicSetup(Stage stage) throws SQLException {

        hBox = new HBox();
        hBox.setSpacing(5);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        final Label label = new Label("Generic operations on Real Estate Database");



        final ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(getTableNames());
        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println("New Value: " + newValue);
                String tableName = (String) newValue;
                try {
                    setupHierarchyForTableName(tableName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        vbox.getChildren().addAll(label, comboBox, genericTableView, hBox);
        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
    }



    public void setupHierarchyForTableName(String tableName) throws SQLException {

        genericTableView.getColumns().clear();

        this.data = FXCollections.observableArrayList();
        List<TableColumnName> columnsForTableName = dbWorker.getColumnsForTableName(tableName);
        for (int i = 0; i < columnsForTableName.size(); i++) {
            final int j = i;
            TableColumnName tcm = columnsForTableName.get(i);
            TableColumn tableColumn = new TableColumn(tcm.getColumnName());
            tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>() {
                @Override
                public ObservableValue call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            genericTableView.getColumns().addAll(tableColumn);
        }
        ResultSet rs = dbWorker.selectGeneralSQL(tableName);
        while (rs.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int indx = 1; indx <= rs.getMetaData().getColumnCount(); indx++) {
                row.add(rs.getString(indx));
            }
            data.add(row);
        }
        genericTableView.setItems(data);


        List<TextField> textFields = getInsertTextFieldsForTable(columnsForTableName);
        Button addObjectToDatabaseButton = new Button();
        addObjectToDatabaseButton.setText("Add new object");
        addObjectToDatabaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        hBox.getChildren().clear();

        hBox.getChildren().addAll(textFields);
        hBox.getChildren().add(addObjectToDatabaseButton);

    }

    public List<TextField> getInsertTextFieldsForTable(List<TableColumnName> columns) {

        return columns.stream()
                .map(new Function<TableColumnName, TextField>() {
                    public TextField apply(TableColumnName columnName) {
                        TextField textField = new TextField();
                        textField.setPromptText(columnName.getColumnName());
                        return textField;
                    }
                }).collect(Collectors.toList());

    }


    public ObservableList<String> getTableNames() throws SQLException {
        List<String> listOfTableNames = dbWorker.getTableNames().stream().map(TableName::getTableName)
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(listOfTableNames);
    }

}
