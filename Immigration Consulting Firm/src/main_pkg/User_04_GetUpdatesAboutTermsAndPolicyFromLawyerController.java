/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */

public class User_04_GetUpdatesAboutTermsAndPolicyFromLawyerController implements Initializable {

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

    private final String fileName = "SendTermsAndPolicyToDocManager.bin";
    @FXML
    private ScrollPane scrollPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                loadDataFromFile();
    }    

    private void loadDataFromFile() {
        ArrayList<String> termsAndPolicyData = Lawyer.readFromFile(fileName);
        if (termsAndPolicyData != null && termsAndPolicyData.size() >= 16) {
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
                    // Disable horizontal scroll bar
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            // Enable vertical scroll bar as needed
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    
    }
}

