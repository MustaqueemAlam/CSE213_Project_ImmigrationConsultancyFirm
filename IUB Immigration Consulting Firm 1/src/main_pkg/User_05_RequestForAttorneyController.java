package main_pkg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class User_05_RequestForAttorneyController implements Initializable {

    @FXML
    private ComboBox<String> clientTypeComboBox;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private TextArea additionalInfoTextArea;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    private ToggleGroup togglegroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maleRadioButton.setToggleGroup(togglegroup);
        femaleRadioButton.setToggleGroup(togglegroup);
        clientTypeComboBox.getItems().addAll("Student","Others");
        
    }    

    @FXML
    private void saveButtonOnClick(ActionEvent event) {
        String Gender;
        if((nameTextField.getText()!= null) && (phoneTextField.getText()!= null)&&
                (additionalInfoTextArea.getText()!= null)
                &&(maleRadioButton.isSelected() || femaleRadioButton.isSelected())
                &&(clientTypeComboBox.getValue()!=null)){
         if(maleRadioButton.isSelected()){
             Gender="Male";
         }
          else{
             Gender="Female";
         } 
        if((phoneTextField.getText()).length()==11 && (phoneTextField.getText()).startsWith("01")){
            int phonenumber= Integer.parseInt(phoneTextField.getText()); 
            Case request = new Case(nameTextField.getText(),
                    phonenumber,additionalInfoTextArea.getText()
                    ,clientTypeComboBox.getValue(),Gender);
            Boolean response = Client.RequestForAttorneyFileWriter(request);
            
            if(response){
             Alert a = new Alert(AlertType.CONFIRMATION);
            a.setHeaderText("Requested");
            a.setContentText("Your request has been submitted");
            a.showAndWait();
            }
            else{
                Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Error");
            a.setContentText("Something went wrong");
            a.showAndWait(); 
            }
        }
        else{
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Error");
            a.setContentText("Number should start with 01 and should be 11 didgit");
            a.showAndWait();
        }
    }
        
        else{
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Error");
            a.setContentText("Incomplete Information");
            a.showAndWait();
        }
        
    }

    @FXML
    private void cancelButtonOnclick(ActionEvent event) {
    }

    
}
