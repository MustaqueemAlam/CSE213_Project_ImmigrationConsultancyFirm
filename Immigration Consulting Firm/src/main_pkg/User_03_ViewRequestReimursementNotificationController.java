/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.sun.javafx.font.FontFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_03_ViewRequestReimursementNotificationController implements Initializable {

    private Button BackTODashBoard;
    @FXML
    private TableView<Reimbursement> RequestReimbursementDataTableView;
    @FXML
    private TableColumn<Reimbursement, String> nameColumn;
    @FXML
    private TableColumn<Reimbursement, String> designationColumn;
    @FXML
    private TableColumn<Reimbursement, String> expenseTypeColumn;
    @FXML
    private TableColumn<Reimbursement, LocalDate> reqDateColumn;
    @FXML
    private TableColumn<Reimbursement, Float> amountColumn;
    @FXML
    private TableColumn<Reimbursement, String> paymentMethodColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmployeeName()));
        designationColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDesignation()));
        expenseTypeColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getExpenseDistribution()));
        reqDateColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getReimbursementDate()));
        amountColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getExpenseAmount()));
        paymentMethodColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPaymentMethod()));
        loadDataOnCLick(null);
    }    

    private void loadDataOnCLick(ActionEvent event) {
            ArrayList<Reimbursement> reimbursements = DocumentManager.loadReimbursements("Reimbursement.bin");
           RequestReimbursementDataTableView.getItems().clear();
           RequestReimbursementDataTableView.getItems().addAll(reimbursements);

       }


    @FXML
    private void saveTAbleviewContentAsPDFOnCLick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            saveTableViewContentAsPDF(RequestReimbursementDataTableView.getItems(), file.getAbsolutePath());
        }
    }

    private void saveTableViewContentAsPDF(ObservableList<Reimbursement> items, String filePath) {
        try {
            PdfWriter pdfWriter = new PdfWriter(filePath);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            for (Reimbursement reimbursement : items) {
                document.add(new Paragraph("Employee Name: " + reimbursement.getEmployeeName()));
                document.add(new Paragraph("Designation: " + reimbursement.getDesignation()));
                document.add(new Paragraph("Expense Type: " + reimbursement.getExpenseDistribution()));
                document.add(new Paragraph("Reimbursement Date: " + reimbursement.getReimbursementDate()));
                document.add(new Paragraph("Amount: " + reimbursement.getExpenseAmount()));
                document.add(new Paragraph("\n")); // Add space between entries
            }

            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
