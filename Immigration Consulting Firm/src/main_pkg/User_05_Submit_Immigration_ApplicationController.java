
package main_pkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class User_05_Submit_Immigration_ApplicationController implements Initializable {

    @FXML
    private DatePicker DOBdatepicker;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField nidTextField;
    
    @FXML
    private ComboBox<String> cityComboBox;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private RadioButton otherRadioButton;
    @FXML
    private ComboBox<String> selectcountryComboBox;
    private ToggleGroup toggle = new ToggleGroup();
    private Button Back;
    @FXML
    private TextField passportTextField;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maleRadioButton.setToggleGroup(toggle);
        femaleRadioButton.setToggleGroup(toggle);
        otherRadioButton.setToggleGroup(toggle);
        
        cityComboBox.getItems().addAll("Dhaka"
                ,"Khulan","Rajshahi","Dinajpur"
                ,"Rangpur","Chittagong");
        selectcountryComboBox.getItems().addAll("USA"
                ,"Canada"
                ,"UK"
                ,"Malaysia");
        
    }    

 

    @FXML
    private void submitButtonOnClick(ActionEvent event) {
        String gender = "";
    if (maleRadioButton.isSelected()) {
        gender = "Male";
    } else if (femaleRadioButton.isSelected()) {
        gender = "Female";
    } else if (otherRadioButton.isSelected()) {
        gender = "Other";
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please select a gender.");
        alert.showAndWait();
        return;
    }

    String name = nameTextField.getText();
    String phone = phoneTextField.getText();
    String nid = nidTextField.getText();
    String passport = passportTextField.getText();
    LocalDate dob = DOBdatepicker.getValue();
    String email = emailTextField.getText();

    if (gender.isEmpty() || name.isEmpty() || phone.isEmpty() || nid.isEmpty() || passport.isEmpty() || dob == null || email.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please fill in all the required fields.");
        alert.showAndWait();
        return;
    }

    if (passport.length() != 11 || nid.length() != 10 || phone.length() != 11) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Invalid input length for password, NID, or phone number.");
        alert.showAndWait();
        return;
    }

    LocalDate currentDate = LocalDate.now();
    LocalDate eighteenYearsAgo = currentDate.minusYears(18);
    if (dob.isAfter(eighteenYearsAgo)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Applicant must be 18 years or older.");
        alert.showAndWait();
        return;
    }

    Client newclient = new Client(name,email,cityComboBox.getValue(),selectcountryComboBox.getValue(),gender,passport,nid,dob);
                                     
   

    Boolean success = Client.FileWriter(newclient);
    if (success) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation");
        confirm.setContentText("YOUR APPLICATION IS Submitted");
        confirm.showAndWait();
    } else {
        Alert confirm = new Alert(Alert.AlertType.ERROR);
        confirm.setTitle("ERROR");
        confirm.setContentText("OOPS! Something Went Wrong");
        confirm.showAndWait();
    }
  }

}
