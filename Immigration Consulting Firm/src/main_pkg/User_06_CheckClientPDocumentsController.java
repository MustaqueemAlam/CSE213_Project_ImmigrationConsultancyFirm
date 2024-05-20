
package main_pkg;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class User_06_CheckClientPDocumentsController implements Initializable {

    @FXML
    private TableView<ClientInformation> clientTableView;
    @FXML
    private TableColumn<ClientInformation, String> nameColumn;
    @FXML
    private TableColumn<ClientInformation, String> emailColumn;
    @FXML
    private TableColumn<ClientInformation, Integer> phoneColumn;
    @FXML
    private TableColumn<ClientInformation, String> documentnameColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        documentnameColumn.setCellValueFactory(new PropertyValueFactory<>("documentname"));
    }    

    @FXML
    private void uploadClientDetails(ActionEvent event) {
        ArrayList<ClientInformation> myList= Consultant.ReadClientPersonalDocumentFile();
        ObservableList<ClientInformation> list = FXCollections.observableArrayList(myList);
        clientTableView.setItems(list);
    }
    
}
