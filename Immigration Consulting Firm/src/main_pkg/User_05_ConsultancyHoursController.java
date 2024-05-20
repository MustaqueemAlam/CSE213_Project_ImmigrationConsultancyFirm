
package main_pkg;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class User_05_ConsultancyHoursController implements Initializable {

    
    
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private ComboBox<String> selecttimeComboBox;
    @FXML
    private TableView<ConsultancyHour> tableview;
    @FXML
    private TableColumn<ConsultancyHour, String> consultantnameTableColumn;
    @FXML
    private TableColumn<ConsultancyHour, String> studentnameTableColumn;
    @FXML
    private TableColumn<ConsultancyHour, String> timingTableColumn;
    @FXML
    private ComboBox<String> consultantComboBox;
    private ObservableList<ConsultancyHour> list = FXCollections.observableArrayList();
    @FXML
    private Button Back;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consultantComboBox.getItems().setAll("Amina Khan"
                ,"Tamim Ahmed","Anisa Islam","Mosharraf Hossain");
        selecttimeComboBox.getItems().addAll("12 P.M Sunday"
                ,"1 P.M Saturday","5 P.M Monday"
                ,"5 P.M Tuesday"
                ,"5 P.M Wednesday"
                ,"5 P.M Thursday");
       consultantnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("consultname"));
       studentnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("studentname"));
       timingTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
       tableview.setItems(list);
    }    

    @FXML
    private void confirmButtonOnClick(ActionEvent event) {
        ConsultancyHour consultancyhour = new ConsultancyHour(consultantComboBox.getValue()
                ,nameTextField.getText()
                ,phoneTextField.getText()
                ,selecttimeComboBox.getValue());
        list.add(consultancyhour);
        boolean filewrite=Client.ConsultancyhourFileWriter(consultancyhour);
        if(filewrite){
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setHeaderText("Submitted");
            a.setContentText("Your Request has been Submitted");
            a.showAndWait();
        }
        else{
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("ERROR");
            a.setContentText("ERROR has been OCurred");
            a.showAndWait();
        }
      
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) {
                        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User Login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Get the current stage from any node in the current scene
                Stage currentStage = (Stage) Back.getScene().getWindow();
                currentStage.setScene(scene);
            }catch (IOException e) {
                e.printStackTrace();
        }
    }
    
}
