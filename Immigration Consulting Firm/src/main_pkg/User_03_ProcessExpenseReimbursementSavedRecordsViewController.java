/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_03_ProcessExpenseReimbursementSavedRecordsViewController implements Initializable {

    @FXML
    private TableView<ReimbursementRecords> reimbursementTable;
    @FXML
    private TableColumn<ReimbursementRecords, String> nameColumn;
    @FXML
    private TableColumn<ReimbursementRecords, String> designationColumn;
    @FXML
    private TableColumn<ReimbursementRecords, LocalDate> dateColumn;
    @FXML
    private TableColumn<ReimbursementRecords, String> statusColumn;
    @FXML
    private TableColumn<ReimbursementRecords, String> expenseTypeColumn;
    @FXML
    private TableColumn<ReimbursementRecords, Float> amountColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmployeeName()));
        designationColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDesignation()));
        dateColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getReimbursementDate()));
        statusColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPaymentStatus()));
        expenseTypeColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getExpenseDistribution()));
        amountColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getExpenseAmount()));
        ObservableList<ReimbursementRecords> records = Accountant.loadReimbursementRecord("ReimbursementRecords.bin");
            reimbursementTable.setItems(records);
        
    }    
    
}
