/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_04_ReviewAndGenerateReportOnOfflineInvoicesController implements Initializable {

    @FXML
    private TextField invoiceNumber;
    @FXML
    private TextField totalAmountTextFeild;
    @FXML
    private TextField clientName;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> paymentStatusComboBOx;
    @FXML
    private Button browseData;
    @FXML
    private TextArea InvoicedataTextarea;
    private Button backtodashboard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paymentStatusComboBOx.getItems().addAll("Due Date over","Due");
    }    

    @FXML
    private void BrowseDataOnClick(ActionEvent event) {
        String initialFolderPath = "I:/.shortcut-targets-by-id/1-5XpgZLlKuPq1oQh87JMzwmlJUVInsPA/FXML Explorers Group Project/IUB Immigration Consulting Firm_2/IUB Immigration Consulting Firm/src/ClientInvoices_GeneratedByAccountant";    
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.setInitialDirectory(new File(initialFolderPath));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            String textContent = DocumentManager.readTextFile(filePath);
            InvoicedataTextarea.setText(textContent);
        }

    }

        @FXML
        private void saveRecordsAsBinFileOnClick(ActionEvent event) {
            String invoiceNum = invoiceNumber.getText();
            String totalAmountText = totalAmountTextFeild.getText();
            String cName = clientName.getText();
            String paymentStatus = paymentStatusComboBOx.getValue();
            LocalDate currentDate = LocalDate.now();

            if (invoiceNum.isEmpty() || totalAmountText.isEmpty() || cName.isEmpty() || paymentStatus == null || InvoicedataTextarea.getText().isEmpty()) {
                showErrorAlert("Error", "Please enter all required data.");
                return;
            }
            if (!isInvoiceNumberUnique(invoiceNum)) {
                showErrorAlert("Error", "Invoice number must be unique.");
                return;
            }

            try {
                Float totalAmount = Float.parseFloat(totalAmountText);

                Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation");
                confirmationAlert.setHeaderText("Confirm Save");
                confirmationAlert.setContentText("Are you sure you want to save the record?");
                Optional<ButtonType> result = confirmationAlert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if (currentDate.equals(date.getValue())) { 
                        InvoiceReport report = new InvoiceReport(invoiceNum, totalAmount, cName, paymentStatus, currentDate);

                        List<InvoiceReport> existingReports = DocumentManager.LoadInvoiceReports("InvoicesReport.bin");
                        existingReports.add(report);

                        try {
                            DocumentManager.saveInvoiceReports(existingReports, "InvoicesReport.bin");
                            showInfoAlert("Success", "Record added successfully.");
                            clearFields();
                        } catch (Exception e) {
                            e.printStackTrace();
                            showErrorAlert("Error", "Failed to save the record.");
                        }
                    } else {
                        showErrorAlert("Error", "Selected date is not the current date.");
                    }
                }
            } catch (NumberFormatException e) {
                showErrorAlert("Error", "Invalid total amount format. Please enter a valid number.");
            }
    }
    private boolean isInvoiceNumberUnique(String invoiceNumber) {
        List<InvoiceReport> existingReports = DocumentManager.LoadInvoiceReports("InvoicesReport.bin");

        for (InvoiceReport report : existingReports) {
            if (report.getInvoiceNumber().equals(invoiceNumber)) {
                return false;
            }
        }
        return true;
    }


    private void backtodashboardOnClick(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_04_MainDashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) backtodashboard.getScene().getWindow();
            currentStage.setScene(scene);
            }catch (IOException e) {
            e.printStackTrace();
        }
    }
        private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        invoiceNumber.clear();
        totalAmountTextFeild.clear();
        clientName.clear();
        paymentStatusComboBOx.getSelectionModel().clearSelection();
        InvoicedataTextarea.clear();
    }

}
