
package main_pkg;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class User_02_WriteTermsAndPolicyAndSendToDocumentManagerForReviewAndUploadingController implements Initializable {

    @FXML
    private TextArea introductionTextArea;
    @FXML
    private TextArea scopeOfServicesTextArea;
    @FXML
    private TextArea ClientResponsibilitiesTextArea;
    @FXML
    private TextArea FeesAndPaymentTextArea;
    @FXML
    private TextArea privacyAndConfidentialityTextArea;
    @FXML
    private TextArea communicationTextArea;
    @FXML
    private TextArea limitationsOfServicesTextArea;
    @FXML
    private TextArea govRegulationsTextArea;
    @FXML
    private TextArea CancellationAndTerminationTextArea;
    @FXML
    private TextArea disputeResolution;
    @FXML
    private TextArea intellectualPropertyTextArea;
    @FXML
    private TextArea legaliababilityTExtArea;
    @FXML
    private TextArea governiingLawTextArea;
    @FXML
    private TextArea ChangeToTermsTextArea;
    @FXML
    private TextArea acceptanceOfTermsTextArea;
    @FXML
    private TextArea contactInformationTextArea;

    /**
     * Initializes the controller class.
     */
    private ArrayList<String> termsAndPolicyData = new ArrayList<>();
    private final String fileName = "SendTermsAndPolicyToDocManager.bin";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateRecordsOnClick(ActionEvent event) {
        if (allTextAreasFilled()) {
            termsAndPolicyData.clear();
            termsAndPolicyData.add(introductionTextArea.getText());
            termsAndPolicyData.add(scopeOfServicesTextArea.getText());
            termsAndPolicyData.add(ClientResponsibilitiesTextArea.getText());
            termsAndPolicyData.add(FeesAndPaymentTextArea.getText());
            termsAndPolicyData.add(privacyAndConfidentialityTextArea.getText());
            termsAndPolicyData.add(communicationTextArea.getText());
            termsAndPolicyData.add(limitationsOfServicesTextArea.getText());
            termsAndPolicyData.add(govRegulationsTextArea.getText());
            termsAndPolicyData.add(CancellationAndTerminationTextArea.getText());
            termsAndPolicyData.add(disputeResolution.getText());
            termsAndPolicyData.add(intellectualPropertyTextArea.getText());
            termsAndPolicyData.add(legaliababilityTExtArea.getText());
            termsAndPolicyData.add(governiingLawTextArea.getText());
            termsAndPolicyData.add(ChangeToTermsTextArea.getText());
            termsAndPolicyData.add(acceptanceOfTermsTextArea.getText());
            termsAndPolicyData.add(contactInformationTextArea.getText());

            Lawyer.writeToFile(termsAndPolicyData, fileName);
            clearTextAreas();
            showAlert("Data saved successfully!");
        } else {
            showAlert("Please fill in all text areas before saving.");
        }
    }
    private boolean allTextAreasFilled() {
        return !introductionTextArea.getText().isEmpty() &&
           !scopeOfServicesTextArea.getText().isEmpty() &&
           !ClientResponsibilitiesTextArea.getText().isEmpty() &&
           !FeesAndPaymentTextArea.getText().isEmpty() &&
           !privacyAndConfidentialityTextArea.getText().isEmpty() &&
           !communicationTextArea.getText().isEmpty() &&
           !limitationsOfServicesTextArea.getText().isEmpty() &&
           !govRegulationsTextArea.getText().isEmpty() &&
           !CancellationAndTerminationTextArea.getText().isEmpty() &&
           !disputeResolution.getText().isEmpty() &&
           !intellectualPropertyTextArea.getText().isEmpty() &&
           !legaliababilityTExtArea.getText().isEmpty() &&
           !governiingLawTextArea.getText().isEmpty() &&
           !ChangeToTermsTextArea.getText().isEmpty() &&
           !acceptanceOfTermsTextArea.getText().isEmpty() &&
           !contactInformationTextArea.getText().isEmpty();
        }


    @FXML
    private void LoadRecordsOnClick(ActionEvent event) {
    if (allTextAreasEmpty()) {
        try {
            termsAndPolicyData = Lawyer.readFromFile(fileName);
            introductionTextArea.setText(termsAndPolicyData.get(0));
            scopeOfServicesTextArea.setText(termsAndPolicyData.get(1));
            ClientResponsibilitiesTextArea.setText(termsAndPolicyData.get(2));
            FeesAndPaymentTextArea.setText(termsAndPolicyData.get(3));
            privacyAndConfidentialityTextArea.setText(termsAndPolicyData.get(4));
            communicationTextArea.setText(termsAndPolicyData.get(5));
            limitationsOfServicesTextArea.setText(termsAndPolicyData.get(6));
            govRegulationsTextArea.setText(termsAndPolicyData.get(7));
            CancellationAndTerminationTextArea.setText(termsAndPolicyData.get(8));
            disputeResolution.setText(termsAndPolicyData.get(9));
            intellectualPropertyTextArea.setText(termsAndPolicyData.get(10));
            legaliababilityTExtArea.setText(termsAndPolicyData.get(11));
            governiingLawTextArea.setText(termsAndPolicyData.get(12));
            ChangeToTermsTextArea.setText(termsAndPolicyData.get(13));
            acceptanceOfTermsTextArea.setText(termsAndPolicyData.get(14));
            contactInformationTextArea.setText(termsAndPolicyData.get(15));
            showAlert("Data loaded successfully!");
        } catch (Exception e) {
            showAlert("Error loading data from file.");
        }
            } else {
                showErrorAlert("Please fill in all text areas before saving.");
            }
    }   

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void ClearTextAreasOnClick(ActionEvent event) {
        introductionTextArea.clear();
        scopeOfServicesTextArea.clear();
        ClientResponsibilitiesTextArea.clear();
        FeesAndPaymentTextArea.clear();
        privacyAndConfidentialityTextArea.clear();
        communicationTextArea.clear();
        limitationsOfServicesTextArea.clear();
        govRegulationsTextArea.clear();
        CancellationAndTerminationTextArea.clear();
        disputeResolution.clear();
        intellectualPropertyTextArea.clear();
        legaliababilityTExtArea.clear();
        governiingLawTextArea.clear();
        ChangeToTermsTextArea.clear();
        acceptanceOfTermsTextArea.clear();
        contactInformationTextArea.clear();
    }
    private boolean allTextAreasEmpty() {
    return introductionTextArea.getText().isEmpty() &&
           scopeOfServicesTextArea.getText().isEmpty() &&
           ClientResponsibilitiesTextArea.getText().isEmpty() &&
           FeesAndPaymentTextArea.getText().isEmpty() &&
           privacyAndConfidentialityTextArea.getText().isEmpty() &&
           communicationTextArea.getText().isEmpty() &&
           limitationsOfServicesTextArea.getText().isEmpty() &&
           govRegulationsTextArea.getText().isEmpty() &&
           CancellationAndTerminationTextArea.getText().isEmpty() &&
           disputeResolution.getText().isEmpty() &&
           intellectualPropertyTextArea.getText().isEmpty() &&
           legaliababilityTExtArea.getText().isEmpty() &&
           governiingLawTextArea.getText().isEmpty() &&
           ChangeToTermsTextArea.getText().isEmpty() &&
           acceptanceOfTermsTextArea.getText().isEmpty() &&
           contactInformationTextArea.getText().isEmpty();
                
    }

    private void clearTextAreas() {
        introductionTextArea.clear();
      scopeOfServicesTextArea.clear();
      ClientResponsibilitiesTextArea.clear();
      FeesAndPaymentTextArea.clear();
      privacyAndConfidentialityTextArea.clear();
      communicationTextArea.clear();
      limitationsOfServicesTextArea.clear();
      govRegulationsTextArea.clear();
      CancellationAndTerminationTextArea.clear();
      disputeResolution.clear();
      intellectualPropertyTextArea.clear();
      legaliababilityTExtArea.clear();
      governiingLawTextArea.clear();
      ChangeToTermsTextArea.clear();
      acceptanceOfTermsTextArea.clear();
      contactInformationTextArea.clear();
  }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void saveCopyAsPDF(ActionEvent event) {
            if (allTextAreasFilled()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File selectedFile = fileChooser.showSaveDialog(new Stage());

            if (selectedFile != null) {
                try {
                    PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(selectedFile));
                    PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                    Document document = new Document(pdfDocument);

                    document.add(new Paragraph("Introduction:"));
                    document.add(new Paragraph(introductionTextArea.getText()));

                    document.add(new Paragraph("Scope of Services:"));
                    document.add(new Paragraph(scopeOfServicesTextArea.getText()));
                    
                    document.add(new Paragraph("Client Responsibilities:"));
                    document.add(new Paragraph(ClientResponsibilitiesTextArea.getText()));
                    document.add(new Paragraph("Fees And PaymentTextArea:"));
                    document.add(new Paragraph(FeesAndPaymentTextArea.getText()));
                    document.add(new Paragraph("Privacy And Confidentiality:"));
                    document.add(new Paragraph(privacyAndConfidentialityTextArea.getText()));
                    document.add(new Paragraph("Communication:"));
                    document.add(new Paragraph(communicationTextArea.getText()));
                    document.add(new Paragraph("Limitations Of Services:"));
                    document.add(new Paragraph(limitationsOfServicesTextArea.getText()));
                    document.add(new Paragraph("Gov Regulations:"));
                    document.add(new Paragraph(govRegulationsTextArea.getText()));
                    document.add(new Paragraph("Cancellation And Termination:"));
                    document.add(new Paragraph(CancellationAndTerminationTextArea.getText()));
                    document.add(new Paragraph("Dispute Resolution:"));
                    document.add(new Paragraph(disputeResolution.getText()));
                    document.add(new Paragraph("Intellectual Property:"));
                    document.add(new Paragraph(intellectualPropertyTextArea.getText()));
                    document.add(new Paragraph("Legaliabability:"));
                    document.add(new Paragraph(legaliababilityTExtArea.getText()));
                    document.add(new Paragraph("Governiing Law:"));
                    document.add(new Paragraph(governiingLawTextArea.getText()));
                    document.add(new Paragraph("Change To Terms:"));
                    document.add(new Paragraph(ChangeToTermsTextArea.getText()));
                    document.add(new Paragraph("Acceptance Of Terms:"));
                    document.add(new Paragraph(acceptanceOfTermsTextArea.getText()));
                    document.add(new Paragraph("Contact Information:"));
                    document.add(new Paragraph(contactInformationTextArea.getText()));
    
                    document.close();
                    showAlert("PDF file saved successfully!");
                } catch (IOException e) {
                    showErrorAlert("Error saving PDF.");
                    e.printStackTrace();
                }
            }
        } else {
            showErrorAlert("Please fill in all text areas before saving.");
        }
    }

}
