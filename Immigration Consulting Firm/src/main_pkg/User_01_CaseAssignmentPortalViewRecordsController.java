/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author mdsha
 */
public class User_01_CaseAssignmentPortalViewRecordsController implements Initializable {

    @FXML
    private TableColumn<CaseAssignment, String> clientNameColumn;
    @FXML
    private TableColumn<CaseAssignment, String> contactInfoColumn;
    @FXML
    private TableColumn<CaseAssignment, String> caseTypeColumn;
    @FXML
    private TableColumn<CaseAssignment, String> assignedAttorneyColumn;
    @FXML
    private TableColumn<CaseAssignment, LocalDate> assignmentDateColumn;
    @FXML
    private TableView<CaseAssignment> Tableview_2;
    @FXML
    private TableColumn<CaseAssignment, String> clientNameColumn2;
    @FXML
    private TableColumn<CaseAssignment, String> contactInfoColumn2;
    @FXML
    private TableColumn<CaseAssignment, String> caseTypeColumn2;
    @FXML
    private TableColumn<CaseAssignment, String> assignedAttorneyColumn2;
    @FXML
    private TableColumn<CaseAssignment, LocalDate> assignmentDateColumn2;
    @FXML
    private TableView<CaseAssignment> TableView_1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getClientName()));
        contactInfoColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getContactInfo()));
        caseTypeColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCaseType()));
        assignedAttorneyColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAssignedAttorneyOrParalegal()));
        assignmentDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAssignmentDate()));
        
        clientNameColumn2.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getClientName()));
        contactInfoColumn2.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getContactInfo()));
        caseTypeColumn2.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCaseType()));
        assignedAttorneyColumn2.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAssignedAttorneyOrParalegal()));
        assignmentDateColumn2.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAssignmentDate()));
        
        ObservableList<CaseAssignment> caseAssignments = Receptionist.loadCaseAssignmentsFromFiles("CaseAssignment.bin");
        TableView_1.setItems(caseAssignments);
        ObservableList<CaseAssignment> sortedCaseAssignments = Receptionist.loadCaseAssignmentsFromFiles("ShortedCaseAssignment.bin");
        Tableview_2.setItems(sortedCaseAssignments);
    }    

    @FXML
    private void sortOutButtonOnClick(ActionEvent event) {
        CaseAssignment selectedCase = TableView_1.getSelectionModel().getSelectedItem();
        if (selectedCase != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete this case?");
            alert.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                TableView_1.getItems().remove(selectedCase);
                Receptionist.updateFile(TableView_1.getItems(),"CaseAssignment.bin");
                Receptionist.saveSortedCaseAssignment(selectedCase, "ShortedCaseAssignment.bin");
                Tableview_2.getItems().add(selectedCase);

            }
        }
    }
    
}
