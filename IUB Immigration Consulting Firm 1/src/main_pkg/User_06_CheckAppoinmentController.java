
package main_pkg;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class User_06_CheckAppoinmentController implements Initializable {

    @FXML
    private TableView<ConsultancyHour> tableview;
    @FXML
    private TableColumn<ConsultancyHour, String> consultantnameTableColumn;
    @FXML
    private TableColumn<ConsultancyHour, String> studentnameTableColumn;
    @FXML
    private TableColumn<ConsultancyHour, String> timeTableColumn;
    @FXML
    private Button backButtonOnClick;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       consultantnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("consultname"));
       studentnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("studentname"));
       timeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        
    }    

    @FXML
    private void checkappoinmentButtonOnClick(ActionEvent event) {
        ArrayList<ConsultancyHour> list = Consultant.ReadConsultancyHourFile();
        ObservableList<ConsultancyHour> observableList = FXCollections.observableArrayList(list);
        tableview.setItems(observableList);
    }
    
    
}
