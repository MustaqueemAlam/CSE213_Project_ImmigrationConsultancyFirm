/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_03_PayrollAndProcessingController implements Initializable {

    @FXML
    private TextField nameTExtFieild;
    @FXML
    private TextField BAsicSallaryTextFeild;
    @FXML
    private TextField deductionsTextFeild;

    @FXML
    private ComboBox<Integer> overtimehoursComboBox;
    @FXML
    private ComboBox<String> designationComboBox;
    @FXML
    private Label displayTotalPayableLable;
    @FXML
    private DatePicker paymentDate;
    private Button backtodashboard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        overtimehoursComboBox.getItems().addAll(12, 24 ,48, 72, 96, 112);
        designationComboBox.getItems().addAll("HR Manager", "Accountant", "Receptionist",
                "Consultant", "IT Specialist", "Document Manager", "Lawyer");
        paymentDate.setValue(LocalDate.now());
    }    

    @FXML
    private void calculatePayableAmount(ActionEvent event) {
    if (!validateInputFields()) {
                return;
            }

            int basicSalary = Integer.parseInt(BAsicSallaryTextFeild.getText());
            int overtimeHours = overtimehoursComboBox.getValue();
            int deductions = Integer.parseInt(deductionsTextFeild.getText());

            int totalPayable = Payroll.calculateTotalPayablePayroll(basicSalary, overtimeHours, deductions);
            displayTotalPayableLable.setText("Total Payable: $" + totalPayable);
}
    


    @FXML
    private void saveRecordsAsBinaryFile(ActionEvent event) {
        try {
            if (!validateInputFields() || !validateComboBoxes()) {
                return;
            }
            if (!validateInputFields() || !validateComboBoxes()) {
                return;
            }

        String employeeName = nameTExtFieild.getText();
        int basicSalary = Integer.parseInt(BAsicSallaryTextFeild.getText());
        int overtimeHours = overtimehoursComboBox.getValue();
        int deductions = Integer.parseInt(deductionsTextFeild.getText());
        LocalDate selectedPaymentDate = paymentDate.getValue();
        String designation = designationComboBox.getValue();

            if (showConfirmationAlert("Are you sure you want to add this record?")) {
                Accountant.savePayrollRecord(employeeName, basicSalary, overtimeHours, deductions, selectedPaymentDate, designation);
                showSuccessAlert("Record added successfully.");
                clearAllFields();
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid input. Please enter valid numeric values.");
        } catch (Exception e) {
            showErrorAlert("An error occurred while saving the record.");

        }
    }

    private boolean validateInputFields() {
        try {
            int basicSalary = Integer.parseInt(BAsicSallaryTextFeild.getText());
            int deductions = Integer.parseInt(deductionsTextFeild.getText());

            if (basicSalary <= 0 || basicSalary > 250000) {
                showErrorAlert("Basic salary should be between 1 and 25000.");
                return false;
            }

            if (deductions < 0 || deductions > 25000) {
                showErrorAlert("Deductions should be between 0 and 25000.");
                return false;
            }
            
            return true;
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid input in Basic Salary or Deductions.");
            return false;
        }
    }

    private boolean validateComboBoxes() {
        if (overtimehoursComboBox.getValue() == null || designationComboBox.getValue() == null) {
            showErrorAlert("Please select values for Overtime Hours and Designation.");
            return false;
        }
        return true;
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean showConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);

        return alert.showAndWait().orElse(null).equals(ButtonType.OK);
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearAllFields() {
        nameTExtFieild.clear();
        BAsicSallaryTextFeild.clear();
        deductionsTextFeild.clear();
        overtimehoursComboBox.setValue(null);
        designationComboBox.setValue(null);
        paymentDate.setValue(LocalDate.now());
        displayTotalPayableLable.setText("");
    }

    private void GoBackToDashBoardOnClick(ActionEvent event) {
                                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User_03_MainDashboard.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Get the current stage from any node in the current scene
                Stage currentStage = (Stage) backtodashboard.getScene().getWindow();
                currentStage.setScene(scene);
            }catch (IOException e) {
                e.printStackTrace();
        }
    }
    
}