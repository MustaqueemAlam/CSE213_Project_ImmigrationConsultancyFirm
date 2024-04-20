/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_03_MainDashboardController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Label userName;
    @FXML
    private Label clockLabel;
    @FXML
    private PieChart pieChart;
    @FXML
    private Button logoutt;
    @FXML
    private Button generateInvoiceButton;
    @FXML
    private Button auditButton;
    @FXML
    private Button payRollsButton;
    @FXML
    private Button reimbursementReportButton;
    @FXML
    private Label reimreqcount;
    @FXML
    private Label auditreportCount;
    @FXML
    private LineChart<?, ?> taxrateLineChart;
        public void setUserFullName(String fullName) {
        userName.setText(fullName);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  ClockUtil clockUtil = new ClockUtil(clockLabel);

        int reimCount = countObjectsInBinFile("Reimbursement.bin");
        reimreqcount.setText(Integer.toString(reimCount));

        // Count files in AuditReport_GeneratedByAccountant folder and update label
        int auditReportCount = countBinFilesInFolder("src/AuditReport_GeneratedByAccountant");
        auditreportCount.setText(Integer.toString(auditReportCount));
    }    
private int countObjectsInBinFile(String fileName) {
    int count = 0;
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
        while (true) {
            try {
                ois.readObject();
                count++;
            } catch (EOFException e) {
                break; // End of file reached, exit the loop
            }
        }
    } catch (IOException | ClassNotFoundException e) {
        // File not found or error reading objects
        e.printStackTrace();
    }
    return count;
}

private int countBinFilesInFolder(String folderPath) {
    int count = 0;
    try {
        count = (int) Files.list(Paths.get(folderPath))
                .filter(p -> p.toString().endsWith(".bin")) // Filter files by extension
                .count();
    } catch (IOException e) {
        // Folder not found or error accessing files
        e.printStackTrace();
    }
    return count;
}
    @FXML
    private void navigateToDashboard(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("User_03_mainDashboardWelcomePage.fxml"));
        borderPane.setCenter(parent);
    }
    @FXML
    private void logoutClick(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) logoutt.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void overtimeDataAnalysisFromPayrollMenuBarButtonOnClick(ActionEvent event) throws IOException {
        loadScene("User_03_ViewPayrollProcessingReports.fxml");
    }

    @FXML
    private void termsAndPolicyMenuBarButtonOnClick(ActionEvent event) throws IOException {
        loadScene("Terms and Policies.fxml");
    }

    @FXML
    private void reimbursementReportsenuBarButtonOnClick(ActionEvent event) throws IOException {
        loadScene("User_03_ViewRequestReimursementNotification.fxml");
    }

    @FXML
    private void trackAddWorkHrsMenuBarButtonOnClick(ActionEvent event) throws IOException {
        loadScene("User_03_TrackAdditionalWorkingHourDataOfEmployees.fxml");
    }

    @FXML
    private void generateInvoiceButtonOnClick(ActionEvent event) throws IOException {
        loadScene("User_03_Generate_Client_Invoices.fxml");
    
    }

    @FXML
    private void auditButtonOnClick(ActionEvent event) throws IOException {
        loadScene("User_03_SubmitAuditReport.fxml");
    }

    @FXML
    private void payRollsButtonOnClick(ActionEvent event) throws IOException {
        loadScene("User_03_PayrollAndProcessing.fxml");
    }

    @FXML
    private void reimbursementReportButtonOnClick(ActionEvent event) throws IOException {
         loadScene("User_03_ProcessExpenseReimbursement.fxml");
    }

    private void loadScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        borderPane.setCenter(root);
    }
}
