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

    private ComboBox columnBox;
    private TextField selectValueTextField;
    HBox hBox;
    String currentQueryKey;
    String currentQueryValue;

    String currentTableName;

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



        HBox horizontalBox = new HBox();
        horizontalBox.setSpacing(10);

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


        columnBox = new ComboBox();



        selectValueTextField = new TextField();

        Button setValueButton = new Button();
        setValueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               String columnName = (String) columnBox.getSelectionModel().getSelectedItem().toString();
                TableColumnName tableColumn = columnsForTableName.stream().filter(obj -> obj.getColumnName().equals(columnName)).collect(Collectors.toList()).get(0);
                String text = selectValueTextField.getText();
                if (text.length() == 0) { return; }
                String formatted = TableColumnName.formattedValueBasedOnType(text, tableColumn.getColumnType());


                currentQueryKey = tableColumn.getColumnName();
                currentQueryValue = formatted;
                try {
                    refreshDataInRows(currentTableName, currentQueryKey, currentQueryValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
        setValueButton.setText("Ustaw");


        Button resetButton = new Button();
        resetButton.setText("RESET");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentQueryKey = null;
                currentQueryValue = null;
                try {
                    refreshDataInRows(currentTableName, currentQueryKey, currentQueryValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        Label equalLabel = new Label();
        equalLabel.setText(" = ");
        horizontalBox.getChildren().addAll(comboBox, columnBox, equalLabel, selectValueTextField, setValueButton, resetButton);

        vbox.getChildren().addAll(label, horizontalBox, genericTableView, hBox);
        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
    }


    List<TableColumnName> columnsForTableName;

    private void setupHierarchyForTableName(String tableName) throws SQLException {



        currentTableName = tableName;
        genericTableView.getColumns().clear();

        List<TableColumnName> primaryColumnsList = dbWorker.getPrimaryKeys(tableName);

        this.data = FXCollections.observableArrayList();
        columnsForTableName = dbWorker.getColumnsForTableName(tableName);

        columnBox.getItems().clear();
        columnBox.getItems().addAll(columnsForTableName.stream().map(col -> col.getColumnName()).collect(Collectors.toList()));


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

                    TableColumn edited = event.getTableColumn();
                    String newValue = (String)event.getNewValue();

                    TableColumnName tableColumn = columnsForTableName.stream().filter(obj -> obj.getColumnName().equals(edited.getText())).collect(Collectors.toList()).get(0);

                    String valueFormatted = TableColumnName.formattedValueBasedOnType(newValue, tableColumn.getColumnType());
                    updatedValuesMap.put(edited.getText(), valueFormatted);
                    String updateQuery = SQLQuery.updateSQL(tableName, updatedValuesMap,primaryKeysMap );
                    System.out.println("UPDATE QUERY" + updateQuery);
                    try {
                        dbWorker.updateDatabase(updateQuery);
                        refreshDataInRows(tableName, currentQueryKey, currentQueryValue);
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
                                            refreshDataInRows(tableName, currentQueryKey, currentQueryValue);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    } );
                                    setGraphic( btn );
                                }
                                else {
                                    setGraphic( null );
                                    setText( null );

                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory( cellFactory );
        genericTableView.getColumns().add(actionCol);





        refreshDataInRows(tableName, currentQueryKey, currentQueryValue);

        List<TextField> textFields = getInsertTextFieldsForTable(columnsForTableName);
        Button addObjectToDatabaseButton = new Button();
        addObjectToDatabaseButton.setText("Add new object");
        addObjectToDatabaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Map<String, String> mapped = getMappedValuesFromTextFields(textFields, columnsForTableName);
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

    private void refreshDataInRows(String tableName, String key, String value) throws SQLException {
        genericTableView.getItems().clear();

        ResultSet rs;
        if (key != null && value != null) {
            rs = dbWorker.selectConditionSQL(tableName, key, value);
        }
        else {
            rs = dbWorker.selectGeneralSQL(tableName);
        }
        while (rs.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int indx = 1; indx <= rs.getMetaData().getColumnCount(); indx++) {
                row.add(rs.getString(indx));
            }
            data.add(row);
        }
        genericTableView.setItems(data);
    }


    private Map<String, String> getMappedConditionValues(List<TableColumnName> primaryColumnsList, ObservableList<String> observableList,
                                                        ObservableList<TableColumn<ObservableList, ?>> columns) {

        Map<String, String> primaryKeysMap = new HashMap<String, String>();
        for (TableColumnName primaryColumn: primaryColumnsList) {
            for (int k = 0; k < columns.size(); k++) {
                TableColumn<ObservableList, ?> observableListTableColumn = columns.get(k);
                if (observableListTableColumn.getText().equals(primaryColumn.getColumnName())) {
                    String valueFormatted = TableColumnName.formattedValueBasedOnType(observableList.get(k), primaryColumn.getColumnType());
                    primaryKeysMap.put(primaryColumn.getColumnName(), valueFormatted);
                    break;
                }
            }
        }
        return primaryKeysMap;
    }
    private Map<String, String> getMappedValuesFromTextFields(List<TextField> textFields, List<TableColumnName> columnNames) {

        //Mapping should take into account type of the variable.
        Map<String, String> map = new HashMap<>();
        for (TextField txtField: textFields) {

            String id = txtField.getId();
            TableColumnName tableColumn = columnNames.stream().filter(obj -> obj.getColumnName().equals(txtField.getId())).collect(Collectors.toList()).get(0);
            String value = TableColumnName.formattedValueBasedOnType(txtField.getText(), tableColumn.getColumnType());

            map.put( txtField.getId(), value);
        }
        return map;
    }

    private List<TextField> getInsertTextFieldsForTable(List<TableColumnName> columns) {

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


    private ObservableList<String> getTableNames() throws SQLException {
        List<String> listOfTableNames = dbWorker.getTableNames().stream().map(TableName::getTableName)
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(listOfTableNames);
    }

}
