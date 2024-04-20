package main_pkg;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mdsha
 */
public class User_01_CreateAppoinmentController implements Initializable {

    @FXML
    private Button applyButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> appoinmentWithComboBox;
    @FXML
    private TextField purposeTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appoinmentWithComboBox.getItems().addAll("Lawyer","Consultant");
        // TODO
    }    

    @FXML
    private void applyButton(ActionEvent event) {
        String name = nameTextField.getText();     
        int phoneNumber = Integer.parseInt(numberTextField.getText());
        LocalDate date = datePicker.getValue();
        String appoinmentWith = appoinmentWithComboBox.getValue();
        String purpose = purposeTextField.getText();
        
 
        if (appoinmentWith == "Lawyer") {
            Appointment newAppointment = new Appointment(name, phoneNumber, date, appoinmentWith, purpose);
            boolean addStatus = Receptionist.addNewAppointment(newAppointment, "Lawyer Appointment.bin");

            if (addStatus) {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText("New Apointment Added Successfully!");
                a.showAndWait();
            } else {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Oops, something went wrong");
                a.showAndWait();
            }
            } else{
                Appointment newAppointment = new Appointment(name, phoneNumber, date, appoinmentWith, purpose);
                boolean addStatus = Receptionist.addNewAppointment(newAppointment, "Consultant Appointment.bin");

                if (addStatus) {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText("New Apointment Added Successfully!");
                a.showAndWait();
                } else {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Oops, something went wrong");
                a.showAndWait();
                }
        }
    }
    
}
