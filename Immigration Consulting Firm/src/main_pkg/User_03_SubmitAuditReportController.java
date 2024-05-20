/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_03_SubmitAuditReportController implements Initializable {

    @FXML
    private TextField reportTitleField;
    @FXML
    private TextArea reportDescriptionArea;
    private Button backtodashboard;
    @FXML
    private Button browseButton;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void browseButtononClick(ActionEvent event) {
         File selectedFile = Accountant.browseFile();
        if (selectedFile != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
                AuditReport auditReport = (AuditReport) ois.readObject();
                reportDescriptionArea.setText(auditReport.getReportDescription());
            } catch (IOException | ClassNotFoundException e) {
                showErrorAlert("Error reading the audit report.");
                e.printStackTrace();
            }
        }
    
    }

    @FXML
    private void submitButtonOnClick(ActionEvent event) {
        String reportTitle = reportTitleField.getText().trim();
        String reportDescription = reportDescriptionArea.getText().trim();

        if (isValidInput(reportTitle, reportDescription)) {
            Accountant.saveAuditReport(reportTitle, reportDescription);
            generatePDF(reportTitle, reportDescription);
            showConfirmationAlert("Report submitted successfully.");
            clearFields();
        } else {
            showErrorAlert("Invalid input. Please check title and description.");
            clearFields();
        }
    }

    private void generatePDF(String reportTitle, String reportDescription) {
        try {
            File pdfFile = new File("audit_report.pdf");
            PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(pdfFile));
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            // Add report title and description to the PDF
            Paragraph title = new Paragraph("Report Title: " + reportTitle);
            Paragraph description = new Paragraph("Report Description: " + reportDescription);

            document.add(title);
            document.add(description);

            document.close();
        } catch (IOException e) {
            showErrorAlert("Error generating the PDF.");
            e.printStackTrace();
        }
    }

    private boolean isValidInput(String reportTitle, String reportDescription) {
        return reportTitle.length() > 0 && reportDescription.length() >= 200;
    }

    private void showConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        reportTitleField.clear();
        reportDescriptionArea.clear();
    }

    @FXML
    private void clearTextAreaOnClick(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Text area data is about to be cleared. Click OK to proceed, or Cancel to keep the data.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            reportDescriptionArea.clear();
        }
    }
}
