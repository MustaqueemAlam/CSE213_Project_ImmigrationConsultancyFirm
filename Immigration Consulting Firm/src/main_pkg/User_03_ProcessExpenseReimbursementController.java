/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_03_ProcessExpenseReimbursementController implements Initializable {

    @FXML
    private TextField amount;
    @FXML
    private DatePicker expenseDate;
    @FXML
    private TextField name;
    private Button backToDashboard;
    @FXML
    private ComboBox<String> ExpenseDescriptionComboBox;
    @FXML
    private RadioButton PayableRadioButton;
    @FXML
    private RadioButton NotPayableRadioButton;
    @FXML
    private ComboBox<String> designationComboBox;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         designationComboBox.getItems().addAll("Lawyer", "Receptionist", "Document Manager", "Consultant");
         designationComboBox.getSelectionModel().selectFirst();

         designationComboBox.setOnAction(event -> {
             String selectedDesignation = designationComboBox.getValue();
             if (selectedDesignation != null) {
                 switch (selectedDesignation) {
                     case "Lawyer":
                         ExpenseDescriptionComboBox.getItems().setAll(
                             "Client Meetings", "Legal Research", "Transportation");
                         break;
                     case "Receptionist":
                         ExpenseDescriptionComboBox.getItems().setAll(
                             "Office Supplies", "Client Services", "Telephone Expenses");
                         break;
                     case "Document Manager":
                         ExpenseDescriptionComboBox.getItems().setAll(
                             "Printing and Copying", "Document Management Tools", "Shipping Costs");
                         break;
                     case "Consultant":
                         ExpenseDescriptionComboBox.getItems().setAll(
                             "Client Meetings", "Training and Seminars", "Travel Expenses");
                         break;
                     default:
                         ExpenseDescriptionComboBox.getItems().clear();
                         break;
                 }
             }
         });
         ToggleGroup paymentMethodToggleGroup = new ToggleGroup();
         PayableRadioButton.setToggleGroup(paymentMethodToggleGroup);
         NotPayableRadioButton.setToggleGroup(paymentMethodToggleGroup);
    }    
    private static final String REIMBURSEMENT_RECORDS_FILE = "ReimbursementRecords.bin";

    @FXML
    private void saveRecordsAsBinFileOnClick(ActionEvent event) {
               String nameValue = name.getText();
        String designationValue = designationComboBox.getValue();
        String expenseDescriptionValue = ExpenseDescriptionComboBox.getValue();
        LocalDate expenseDateValue = expenseDate.getValue();
        Float amountValue = Float.parseFloat(amount.getText());

        if (nameValue.isEmpty() || designationValue == null || expenseDescriptionValue == null
                || expenseDateValue == null || amountValue == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields.");
            return;
        }

        String paymentStatus = PayableRadioButton.isSelected() ? "Payable" : "Not Payable";

        ReimbursementRecords reimbursementRecord = new ReimbursementRecords(
            nameValue, amountValue, expenseDateValue, designationValue, paymentStatus, expenseDescriptionValue);

       ArrayList<ReimbursementRecords> existingRecords = Accountant.loadReimbursementRecords(REIMBURSEMENT_RECORDS_FILE);

        // If the file doesn't exist, create an empty ArrayList
        if (existingRecords == null) {
            existingRecords = new ArrayList<>();
        }

        existingRecords.add(reimbursementRecord);

        if (Accountant.writeReimbursementRecords(existingRecords, REIMBURSEMENT_RECORDS_FILE)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Record saved successfully.");
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save record.");
        }

    }

    private void clearFields() {
        name.clear();
        designationComboBox.getSelectionModel().selectFirst();
        ExpenseDescriptionComboBox.getItems().clear();
        PayableRadioButton.setSelected(false);
        NotPayableRadioButton.setSelected(false);
        expenseDate.setValue(null);
        amount.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    
        
    }

    @FXML
    private void loadPrevRecordsOnClick(ActionEvent event) {
                try {
            // Load the FXML file for stage1.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_03_ProcessReimbursementSavedRecordsView.fxml"));
            Parent root = loader.load();
            User_03_ProcessExpenseReimbursementSavedRecordsViewController stage1Controller = loader.getController();
            // Create a new stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Stage 1");

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    private void backOnClick(ActionEvent event) {
                                        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User_03_MainDashboard.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Get the current stage from any node in the current scene
                Stage currentStage = (Stage) backToDashboard.getScene().getWindow();
                currentStage.setScene(scene);
            }catch (IOException e) {
                e.printStackTrace();
        }
    
        
    }
    
}
