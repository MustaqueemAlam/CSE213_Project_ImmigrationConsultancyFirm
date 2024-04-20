
package main_pkg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class User_06_UniversityListReviewController implements Initializable {

    @FXML
    private TextField universitynameTextField;
    @FXML
    private TextField countrynameTextField;
    @FXML
    private TextField ieltsTextField;
    @FXML
    private TextField greTextField;
    @FXML
    private TextField acceptanceTextField;
    @FXML
    private RadioButton ukradioButton;
    @FXML
    private RadioButton usaradioButton;
    @FXML
    private RadioButton canadaradioButton;
    ToggleGroup togglegroup = new ToggleGroup();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ukradioButton.setToggleGroup(togglegroup);
    usaradioButton.setToggleGroup(togglegroup);
    canadaradioButton.setToggleGroup(togglegroup);
    
    
    ukradioButton.setOnAction(event -> {
        countrynameTextField.setText("UK");
        countrynameTextField.setEditable(false);
    });

    usaradioButton.setOnAction(event -> {
        countrynameTextField.setText("USA");
        countrynameTextField.setEditable(false);
    });

    canadaradioButton.setOnAction(event -> {
        countrynameTextField.setText("Canada");
        countrynameTextField.setEditable(false);
    });
    }    

    @FXML
    private void savefileButtonOnClick(ActionEvent event) {
    String universityName = universitynameTextField.getText();
    String countryName = countrynameTextField.getText();
    String ieltsStr = ieltsTextField.getText();
    String greStr = greTextField.getText();
    String acceptanceStr = acceptanceTextField.getText();

    if (universityName.isEmpty() || countryName.isEmpty() || ieltsStr.isEmpty() || greStr.isEmpty() || acceptanceStr.isEmpty()) {
        showAlert("Please fill in all the fields.");
        return;
    }

    try {
        float ielts = Float.parseFloat(ieltsStr);
        int gre = Integer.parseInt(greStr);
        float acceptance = Float.parseFloat(acceptanceStr);

        UniversityList list = new UniversityList(universityName, countryName, ielts, gre, acceptance);
        Boolean value = Consultant.UniversityReviewFileWrite(list, countryName);

        if (value) {
            showConfirmationAlert("Information is saved.");
        } else {
            showErrorAlert("Something went wrong.");
        }
    } catch (NumberFormatException e) {
        showAlert("Invalid numeric input. Please enter valid numbers.");
       }
    }

    private void showAlert(String message) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setHeaderText("Warning");
    alert.setContentText(message);
    alert.showAndWait();
}

private void showConfirmationAlert(String message) {
    Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
    confirmationAlert.setHeaderText("Confirmed");
    confirmationAlert.setContentText(message);
    confirmationAlert.showAndWait();
}

private void showErrorAlert(String message) {
    Alert errorAlert = new Alert(AlertType.ERROR);
    errorAlert.setHeaderText("Error");
    errorAlert.setContentText(message);
    errorAlert.showAndWait();
   }

}
