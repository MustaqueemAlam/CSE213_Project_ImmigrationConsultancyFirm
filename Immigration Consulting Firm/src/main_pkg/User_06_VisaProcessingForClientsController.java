
package main_pkg;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_06_VisaProcessingForClientsController implements Initializable {

    @FXML
    private ComboBox<String> visaTypeComboBox;
    @FXML
    private TextField clientNameTExtFeild;
    @FXML
    private TextField clientContactInfo;
    @FXML
    private DatePicker dateOfApplyingDatePicker;
    @FXML
    private ComboBox<String> PreferredCountryComboBox;
    @FXML
    private RadioButton hasInsuranceRadioButton;
    @FXML
    private RadioButton DontHaveInsuranceRadioButton;
    @FXML
    private RadioButton enrollmentProofGivenRadioButton;
    @FXML
    private RadioButton enrollmentProofnotGivenRadioButton;
    @FXML
    private TextArea display1;
    @FXML
    private RadioButton financialProofgivenRadioBUtton;
    @FXML
    private RadioButton visaApplicationFormRecievedRadioButton;
    @FXML
    private RadioButton visaApplicationFormNotRecievedRadioButton;
    @FXML
    private RadioButton financialProofNotgivenRadioBUtton;
    private ObservableList<VisaApplication> result = FXCollections.observableArrayList();
    
    
    private ToggleGroup insuranceToggleGroup = new ToggleGroup();
    private ToggleGroup enrollmentToggleGroup = new ToggleGroup();
    private ToggleGroup visaAppFormToggleGroup = new ToggleGroup();
    private ToggleGroup financialProofToggleGroup = new ToggleGroup();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        PreferredCountryComboBox.getItems().addAll("Canada","USA","UK"
                ,"Malaysia","Australia","Germany"
                ,"Italy","Japan","France");
        
        visaTypeComboBox.getItems().addAll("Student Visa","Work Visa");
        
        hasInsuranceRadioButton.setToggleGroup(insuranceToggleGroup);
        DontHaveInsuranceRadioButton.setToggleGroup(insuranceToggleGroup);

        enrollmentProofGivenRadioButton.setToggleGroup(enrollmentToggleGroup);
        enrollmentProofnotGivenRadioButton.setToggleGroup(enrollmentToggleGroup);

        visaApplicationFormRecievedRadioButton.setToggleGroup(visaAppFormToggleGroup);
        visaApplicationFormNotRecievedRadioButton.setToggleGroup(visaAppFormToggleGroup);

        financialProofgivenRadioBUtton.setToggleGroup(financialProofToggleGroup);
        financialProofNotgivenRadioBUtton.setToggleGroup(financialProofToggleGroup);
        
    }    

    @FXML
    private void submitRecordsToDocManagerOnCLick(ActionEvent event) {
        String visaType = visaTypeComboBox.getValue();
        String name = clientNameTExtFeild.getText();
        String contactInfo = clientContactInfo.getText();
        LocalDate dateOfApplication = dateOfApplyingDatePicker.getValue();
        String prefferedCountry = PreferredCountryComboBox.getValue();
        

        String insurance = "";
        if (hasInsuranceRadioButton.isSelected()) {
            insurance = "Yes";
        } else if (DontHaveInsuranceRadioButton.isSelected()) {
            insurance = "No";
            } else {
            showErrorAlert("Please select a insurance Status type.");
            return;
                
        }
        String enrollment ="";
        if (enrollmentProofGivenRadioButton.isSelected()) {
            enrollment = "Yes";
        } else if (enrollmentProofnotGivenRadioButton.isSelected()) {
            enrollment = "No";
            } else {
            showErrorAlert("Please select a enrollment Proof Status type.");
            return;
        }
        String visaapplicationformstatus="";
        if (visaApplicationFormRecievedRadioButton.isSelected()) {
            visaapplicationformstatus = "Yes";
        } else if (visaApplicationFormNotRecievedRadioButton.isSelected()) {
            visaapplicationformstatus = "No";
            } else {
            showErrorAlert("Please select a visa Application Form  status type.");
            return;
        }
        String financialproof="";
        if (financialProofgivenRadioBUtton.isSelected()) {
            financialproof = "Yes";
        } else if (financialProofNotgivenRadioBUtton.isSelected()) {
            financialproof = "No";
            } else {
            showErrorAlert("Please select a financial Proof Status type.");
            return;
        }
            VisaApplication visaApplication = new VisaApplication(name, contactInfo, prefferedCountry,
               visaType, dateOfApplication,
               insurance,
               financialproof, visaapplicationformstatus, enrollment
       );
        
        Consultant.VisaProcessingFileWrite(visaApplication);
        clearFields();
        showInfoAlert("Submitted successfully.");

    }

    @FXML
    private void addAllDataToTExtArea(ActionEvent event) {
        String selectedVisaType = visaTypeComboBox.getValue();
        String enteredClientName = clientNameTExtFeild.getText();
        String enteredClientContact = clientContactInfo.getText();
        LocalDate selectedDateOfApplying = dateOfApplyingDatePicker.getValue();
        String selectedPreferredCountry = PreferredCountryComboBox.getValue();
        

        String insuranceStatus;
        if (hasInsuranceRadioButton.isSelected()) {
            insuranceStatus = "Yes";
        } else if (DontHaveInsuranceRadioButton.isSelected()) {
            insuranceStatus = "No";
        } else {
            insuranceStatus = "Not selected";
        }

        String enrollmentProofStatus;
        if (enrollmentProofGivenRadioButton.isSelected()) {
            enrollmentProofStatus = "Yes";
        } else if (enrollmentProofnotGivenRadioButton.isSelected()) {
            enrollmentProofStatus = "No";
        } else {
            enrollmentProofStatus = "Not selected";
        }

        String visaApplicationFormStatus;
        if (visaApplicationFormRecievedRadioButton.isSelected()) {
            visaApplicationFormStatus = "Yes";
        } else if (visaApplicationFormNotRecievedRadioButton.isSelected()) {
            visaApplicationFormStatus = "No";
        } else {
            visaApplicationFormStatus = "Not selected";
        }

        String financialProofStatus;
        if (financialProofgivenRadioBUtton.isSelected()) {
            financialProofStatus = "Yes";
        } else if (financialProofNotgivenRadioBUtton.isSelected()) {
            financialProofStatus = "No";
        } else {
            financialProofStatus = "Not selected";
        }
            VisaApplication visaApplication = new VisaApplication(enteredClientName, enteredClientContact, selectedPreferredCountry,
               selectedVisaType, selectedDateOfApplying,
               insuranceStatus,
               financialProofStatus, visaApplicationFormStatus, enrollmentProofStatus
       );
         String displayText = visaApplication.generateDisplayText();
         display1.clear();
         display1.appendText(displayText);
         

   }

    private void showAlert(Alert.AlertType alerttype,String title,String body){
        Alert a = new Alert(alerttype);
        a.setHeaderText(title);
        a.setContentText(body);
        a.showAndWait();
    }
        private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
         private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        clientNameTExtFeild.clear();
        display1.clear();
        clientContactInfo.clear();
        hasInsuranceRadioButton.setSelected(false);
        DontHaveInsuranceRadioButton.setSelected(false);
        enrollmentProofGivenRadioButton.setSelected(false);
        enrollmentProofnotGivenRadioButton.setSelected(false);
        visaApplicationFormRecievedRadioButton.setSelected(false);
        visaApplicationFormNotRecievedRadioButton.setSelected(false);
        financialProofgivenRadioBUtton.setSelected(false);
        financialProofNotgivenRadioBUtton.setSelected(false);

    }

}
