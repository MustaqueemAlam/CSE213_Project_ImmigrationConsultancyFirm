package main_pkg;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;


public class User_02_CheckNotificationController implements Initializable {

    @FXML
    private ComboBox<String> selectFileComboBox;
    @FXML
    private TextArea textArea;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectFileComboBox.getItems().addAll("Appointment", 
                "Legal Documents");
    }    

    @FXML
    private void loadButtonClick(ActionEvent event) {
        String selectedFileName = null;
        if (selectFileComboBox.getValue() == "Appointment"){
            selectedFileName = "Lawyer Appointment.bin";
        }
        
        List<Object> objects = Lawyer.readObjectsFromFile(selectedFileName);
        StringBuilder contentBuilder = new StringBuilder();
        
        for (Object obj : objects) {
            if (obj instanceof Appointment) {
                contentBuilder.append(((Appointment) obj).toString()).append("\n");
            }
        }
        textArea.setText(contentBuilder.toString());
    }
}