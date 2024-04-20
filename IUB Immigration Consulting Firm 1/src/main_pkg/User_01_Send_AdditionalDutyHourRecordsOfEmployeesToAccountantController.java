package main_pkg;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalDateStringConverter;

/**
 * FXML Controller class
 *
 * @author mdsha
 */
public class User_01_Send_AdditionalDutyHourRecordsOfEmployeesToAccountantController implements Initializable {

    @FXML
    private ComboBox<String> DesignationComboBox;
    @FXML
    private ComboBox<String> NameComboBox;
    @FXML
    private TextField HoursTextField;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private Button LoadInformationButton;
    @FXML
    private TableColumn<AdditionalDutyHours, String> DesignationColumn;
    @FXML
    private TableColumn<AdditionalDutyHours, String> NameColumn;
    @FXML
    private TableColumn<AdditionalDutyHours, Float> HoursColumn;
    @FXML
    private TableView<AdditionalDutyHours> TableView;
    @FXML
    private TableColumn<AdditionalDutyHours, LocalDate> DateColumn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DesignationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        HoursColumn.setCellValueFactory(new PropertyValueFactory<>("hour"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        DateTimeFormatter customDateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DatePicker.setValue(LocalDate.now());
        DatePicker.setConverter(new LocalDateStringConverter(customDateFormat, null));
        
        DesignationComboBox.getItems().addAll("Receptionist", "Lawyer", "Accountant",
                "Document Manager", "Consultant",
                "IT Support Specialist", "Human Resources Manager");

        DesignationComboBox.setOnAction(event -> {
            String selectedPost = DesignationComboBox.getValue();
            if (null == selectedPost) {
                NameComboBox.getItems().clear();
            } else {
                switch (selectedPost) {
                    case "Receptionist":
                        NameComboBox.getItems().clear();
                        NameComboBox.getItems().addAll("Naima Siddiqui", "Sumaiya Akter");
                        break;
                    case "Lawyer":
                        NameComboBox.getItems().clear();
                        NameComboBox.getItems().addAll("Fahmida Haque", "Sumaiya Akter");
                        break;
                    case "Accountant":
                        NameComboBox.getItems().clear();
                        NameComboBox.getItems().addAll("Nasrin Ahmed", "Zahirul Islam Chowdhury");
                        break;
                    case "Document Manager":
                        NameComboBox.getItems().clear();
                        NameComboBox.getItems().addAll("Nafisa Rahman", "Jamaluddin Ahmed");
                        break;
                    case "Consultant":
                        NameComboBox.getItems().clear();
                        NameComboBox.getItems().addAll("Farida Akhter", "Shahidul Haque Siddique", "Raihan Atiq", "Zahid Islam");
                        break;
                    case "IT Support Specialist":
                        NameComboBox.getItems().clear();
                        NameComboBox.getItems().addAll("Rumana Khan", "Mahbubul Alam Choudhury");
                        break;
                    case "Human Resources Manager":
                        NameComboBox.getItems().clear();
                        NameComboBox.getItems().add("Sadekur Rahman");
                        break;
                    default:
                        NameComboBox.getItems().clear();
                        break;
                }
            }
        });
        
    }    

    @FXML
    private void loadInformationOnClick(ActionEvent event) {
        String designation = DesignationComboBox.getValue();
        String name = NameComboBox.getValue();
        String hoursText = HoursTextField.getText();
        Float hoursValue = Float.valueOf(hoursText);
        LocalDate date = DatePicker.getValue();
        TableView.getItems().add(new AdditionalDutyHours(designation, name, hoursValue, date));
        
    }

    @FXML
    private void sendInformationOnClick(ActionEvent event) {
        String designation = DesignationComboBox.getValue();
        String name = NameComboBox.getValue();
        String hoursText = HoursTextField.getText();
        LocalDate date = DatePicker.getValue();

        if (designation == null || name == null || hoursText.isEmpty() || date == null) {
            showErrorAlert("Error", "Please enter all required data.");
            return;
        }

        float hoursValue;
        try {
            hoursValue = Float.parseFloat(hoursText);
        } catch (NumberFormatException e) {
            showErrorAlert("Error", "Invalid hours format. Please enter a valid number.");
            return;
        }

        if (hoursValue <= 0) {
            showErrorAlert("Error", "Hours must be a positive value.");
            return;
        }
        
        
        //////////Validation 
            List<Object> additionalDutyHoursList = Receptionist.readObjectsFromFile("AdditionalHourRecords.bin");
        for (Object obj : additionalDutyHoursList) {
            if (obj instanceof AdditionalDutyHours) {
                AdditionalDutyHours existingRecord = (AdditionalDutyHours) obj;
                if (existingRecord.getDate().equals(date)) {
                    showErrorAlert("Error", "Record already submitted for today.");
                    return;
                }
            }
        }
            ////////////
        AdditionalDutyHours newAdditionalDutyHours = new AdditionalDutyHours(designation, name, hoursValue, date);
        boolean addStatus = Receptionist.addNewAddi_DutyHour(newAdditionalDutyHours, "AdditionalHourRecords.bin");
        if (addStatus) {
            showInfoAlert("Success", "Send Successfully!");
        } else {
            showErrorAlert("Error", "Oops, something went wrong");
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
