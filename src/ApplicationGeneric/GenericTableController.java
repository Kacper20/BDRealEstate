package ApplicationGeneric;

import DBKit.ConnectionManager;
import DBKit.SQLQuery;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        genericTableView.setEditable(true);

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

        List<TableColumnName> primaryColumnsList = dbWorker.getPrimaryKeys(tableName);

        this.data = FXCollections.observableArrayList();
        List<TableColumnName> columnsForTableName = dbWorker.getColumnsForTableName(tableName);
        for (int i = 0; i < columnsForTableName.size(); i++) {
            final int j = i;
            TableColumnName tcm = columnsForTableName.get(i);
            TableColumn tableColumn = new TableColumn(tcm.getColumnName());
            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>() {
                @Override
                public ObservableValue call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            tableColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ObservableList, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<ObservableList, String> event) {

                    ObservableList<TableColumn<ObservableList, ?>> columns = event.getTableView().getColumns();
                    ObservableList<String> rowValues = event.getRowValue();
                    Map <String, String> primaryKeysMap = getMappedConditionValues(primaryColumnsList, rowValues, columns);
                    Map<String, String> updatedValuesMap = new HashMap<String, String>();
                    //TODO: Remember about types, add '' where necessary - REALLY IMPORTANT!!
                    TableColumn edited = event.getTableColumn();
                    String newValue = (String)event.getNewValue();
                    updatedValuesMap.put(edited.getText(), newValue);
                    String updateQuery = SQLQuery.updateSQL(tableName, updatedValuesMap,primaryKeysMap );
                    System.out.println("UPDATE QUERY" + updateQuery);
                    try {
                        dbWorker.updateDatabase(updateQuery);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });


            genericTableView.getColumns().addAll(tableColumn);
        }

        //TODO: Factor it out
        TableColumn actionCol = new TableColumn( "Action" );
        actionCol.setCellValueFactory( new PropertyValueFactory<>( "DUMMY" ) );

        Callback<TableColumn<String, String>, TableCell<String, String>> cellFactory = //
                new Callback<TableColumn<String, String>, TableCell<String, String>>() {
                    @Override
                    public TableCell call( final TableColumn<String, String> param ) {
                        final TableCell<String, String> cell = new TableCell<String, String>() {
                            final Button btn = new Button( "Delete" );
                            @Override
                            public void updateItem( String item, boolean empty ) {
                                super.updateItem( item, empty );
                                if (!empty) {
                                    btn.setOnAction( ( ActionEvent event ) -> {
                                        int indexOfRowToDelete = getIndex();

                                        ObservableList<TableColumn<ObservableList, ?>> columns = genericTableView.getColumns();

                                        ObservableList<String> rowValues = (ObservableList<String>) genericTableView.getItems().get(indexOfRowToDelete);
                                        System.out.println(rowValues);
                                        Map <String, String> primaryKeysForRowMap = getMappedConditionValues(primaryColumnsList, rowValues, columns);
                                        String deleteQuery = SQLQuery.deleteSQL(tableName, primaryKeysForRowMap);
                                        try {
                                            dbWorker.deleteFromDatabase(deleteQuery);
                                            refreshDataInRows(tableName);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }


                                    } );
                                    setGraphic( btn );
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory( cellFactory );
        genericTableView.getColumns().add(actionCol);





        refreshDataInRows(tableName);

        List<TextField> textFields = getInsertTextFieldsForTable(columnsForTableName);
        Button addObjectToDatabaseButton = new Button();
        addObjectToDatabaseButton.setText("Add new object");
        addObjectToDatabaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Map<String, String> mapped = getMappedValuesFromTextFields(textFields);
                String query = SQLQuery.insertSQL(tableName, mapped);
                try {
                    dbWorker.insertIntoDatabase(query);

                    //TODO: Do it in more efficient way - only refresh rows - set item section
                    setupHierarchyForTableName(tableName);
                } catch (SQLException e) {

                    e.printStackTrace();

                }

            }
        });

        hBox.getChildren().clear();
        hBox.getChildren().addAll(textFields);
        hBox.getChildren().add(addObjectToDatabaseButton);


    }

    public void refreshDataInRows(String tableName) throws SQLException {
        ResultSet rs = dbWorker.selectGeneralSQL(tableName);
        while (rs.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int indx = 1; indx <= rs.getMetaData().getColumnCount(); indx++) {
                row.add(rs.getString(indx));
            }
            data.add(row);
        }
        genericTableView.setItems(data);
    }

    public Map<String, String> getMappedConditionValues(List<TableColumnName> primaryColumnsList, ObservableList<String> observableList,
                                                        ObservableList<TableColumn<ObservableList, ?>> columns) {

        Map<String, String> primaryKeysMap = new HashMap<String, String>();
        for (TableColumnName primaryColumn: primaryColumnsList) {
            for (int k = 0; k < columns.size(); k++) {
                TableColumn<ObservableList, ?> observableListTableColumn = columns.get(k);
                if (observableListTableColumn.getText().equals(primaryColumn.getColumnName())) {
                    primaryKeysMap.put(primaryColumn.getColumnName(), observableList.get(k));
                    break;
                }
            }
        }
        return primaryKeysMap;
    }
    public Map<String, String> getMappedValuesFromTextFields(List<TextField> textFields) {

        //Mapping should take into account type of the variable.
        Map<String, String> map = new HashMap<>();
        for (TextField txtField: textFields) {
            map.put( txtField.getId(), txtField.getText());
        }
        return map;
    }

    public List<TextField> getInsertTextFieldsForTable(List<TableColumnName> columns) {

        return columns.stream()
                .map(new Function<TableColumnName, TextField>() {
                    public TextField apply(TableColumnName columnName) {
                        TextField textField = new TextField();
                        textField.setId(columnName.getColumnName());
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
