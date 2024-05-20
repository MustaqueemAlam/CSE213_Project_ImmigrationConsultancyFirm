package main_pkg;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class User_02_CaseAssignmentPortalController implements Initializable {

    @FXML
    private TextField nameTextFeild;
    @FXML
    private TextField ContactInfo;
    @FXML
    private ComboBox<String> caseTypeComboBox;
    @FXML
    private ComboBox<String> attorneyOrParalegalcomboBox;
    @FXML
    private DatePicker assignmentDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caseTypeComboBox.getItems().addAll(
            "Emergency",
            "Pro bono",
            "National security",
            "Special",
            "Random");
        attorneyOrParalegalcomboBox.getItems().addAll(
          "Zaman", "Shad", "Asif", "Mehrima",
                "Sadia", "Nafisa", "Rashid");
    }    
    
    @FXML
    private void AssignCaseOnClick(ActionEvent event) {
       String clientName = nameTextFeild.getText();
        String contactInfo = ContactInfo.getText();
        String selectedAttorney = attorneyOrParalegalcomboBox.getValue();
        String selectedCaseType = caseTypeComboBox.getValue();
        LocalDate selectedDate = assignmentDate.getValue();

        if (clientName.isEmpty() || selectedAttorney == null ||
                selectedCaseType == null || selectedDate == null) {
            showErrorAlert("Missing Information", "Please fill in all fields.");
            return;
        }
        CaseAssignment caseAssignment = new CaseAssignment(clientName, contactInfo,
                selectedCaseType, 
                selectedAttorney,
                selectedDate);
        Lawyer.saveCaseAssignment(caseAssignment,"CaseAssignment.bin");
        nameTextFeild.clear();
        ContactInfo.clear();
        attorneyOrParalegalcomboBox.getSelectionModel().clearSelection();
        caseTypeComboBox.getSelectionModel().clearSelection();
        assignmentDate.setValue(null);

        showSuccessAlert("Case Assignment Saved", "The case assignment has been saved successfully.");
    }

    private void showSuccessAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Wait for user's response
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;

    }

    
}
