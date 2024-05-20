
package main_pkg;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author User
 */
public class User_05_UploadDocumentController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passandnidnoTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private RadioButton maleradioButton;
    @FXML
    private RadioButton femaleradioButton;
    @FXML
    private RadioButton otherradioButton;
    @FXML
    private RadioButton studentradioButton;
    @FXML
    private RadioButton professionradioButton;

    ToggleGroup togglegroup = new ToggleGroup();
    ToggleGroup togglegroup1 = new ToggleGroup();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maleradioButton.setToggleGroup(togglegroup);
        femaleradioButton.setToggleGroup(togglegroup);
        otherradioButton.setToggleGroup(togglegroup);
        
        studentradioButton.setToggleGroup(togglegroup1);
        professionradioButton.setToggleGroup(togglegroup1);
        
    }    

    @FXML
    private void savsanduploadButtonOnClick(ActionEvent event) {
        
         if (nameTextField.getText().isEmpty() || passandnidnoTextField.getText().isEmpty() ||
            phoneTextField.getText().isEmpty() || emailTextField.getText().isEmpty() ||
            addressTextField.getText().isEmpty() ||
            (!maleradioButton.isSelected() && !femaleradioButton.isSelected() && !otherradioButton.isSelected()) ||
            (!studentradioButton.isSelected() && !professionradioButton.isSelected())) {
            
            // Show an alert for incomplete information
            showAlert(Alert.AlertType.ERROR, "Incomplete Information", "Please fill all required fields.");
            return;
        }
        
        // Check if the phone number has 7 digits
        if (phoneTextField.getText().length() != 7) {
            // Show an alert for invalid phone number
            showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone number should be 7 digits.");
            return;
        }
        else{
        String gender = "";
        if (maleradioButton.isSelected()) {
            gender = "Male";
    }   else if (femaleradioButton.isSelected()) {
            gender = "Female";
    }   else if (otherradioButton.isSelected()) {
            gender = "Other";
    }
    
   
       String category = "";
       if (studentradioButton.isSelected()) {
          category = "Student";
    }  else if (professionradioButton.isSelected()) {
          category = "Profession";
        }
    
           String name = nameTextField.getText();
           String phoneNumber = phoneTextField.getText();
           String email = emailTextField.getText();
           String address = addressTextField.getText();
           String documentname=name+"Document";
    
           ClientInformation uploadDocument = new ClientInformation(name, phoneNumber, email, address,category,gender,documentname);
           Boolean filecreate= Client.UploadClientDocumentFileWriter(uploadDocument);
           if(filecreate){
               showAlert(Alert.AlertType.INFORMATION,"Succesfully",
                      " Your File has been recorded now upload document and wait for consultant to raech you");
           }
           else{
               showAlert(Alert.AlertType.ERROR,"ERROR","Something went wrong!!!");
           }
        }
      
       
    }
    
    // Helper method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void uploadDocumentButtonOnClick(ActionEvent event) {
           FileChooser fc = new FileChooser();
           fc.setTitle("Upload Document");   
           fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));    
           File selectedFile = fc.showOpenDialog(null);
           if (selectedFile != null) {
                String name = nameTextField.getText();
                String customFileName = name + "Document.pdf";
                String saveDirectory = "I:\\.shortcut-targets-by-id\\1-5XpgZLlKuPq1oQh87JMzwmlJUVInsPA\\FXML Explorers Group Project\\IUB Immigration Consulting Firm_2\\IUB Immigration Consulting Firm\\src\\ClientPDocument";
                try {
                   File saveFile = new File(saveDirectory, customFileName);
                   Files.copy(selectedFile.toPath(), saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                   System.out.println("File saved to: " + saveFile.getAbsolutePath());
                } 
                catch (IOException e) {
                  e.printStackTrace();
                  System.out.println("Failed to save the file.");
                }
         }

       }
    
    }
        
        
        
        
        
        
      
 
    

