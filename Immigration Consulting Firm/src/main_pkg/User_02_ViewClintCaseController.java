package main_pkg;

import java.net.URL;
import java.util.List;
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


public class User_02_ViewClintCaseController implements Initializable {

    @FXML
    private TableView<Case> tableView;
    @FXML
    private TableColumn<Case, String> nameCol;
    @FXML
    private TableColumn<Case, Integer> phoneCol;
    @FXML
    private TableColumn<Case, String> detailsCol;
    @FXML
    private TableColumn<Case, String> typeCol;
    @FXML
    private TableColumn<Case, String> genderCol;
    @FXML
    private Button loadButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("clientType"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
    }    

    @FXML
    private void loadButtonOnClick(ActionEvent event) {
        List<Object> infoList = Lawyer.readObjectsFromFile("RequestForAttorney.bin");
        ObservableList<Case> loadedItems = FXCollections.observableArrayList();
        for (Object obj : infoList) {
            if (obj instanceof Case) {
                loadedItems.add((Case) obj);
            }
        }
        tableView.getItems().addAll(loadedItems);        
    }
      
}
