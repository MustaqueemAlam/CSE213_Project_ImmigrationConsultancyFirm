/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
*/
public class User_04_ViewPolicyFeedBacksController implements Initializable {

    @FXML
    private TableView<UserFeedback> feedbackTableView;
    @FXML
    private TableColumn<UserFeedback, String> feedbackTypeColumn;
    @FXML
    private TableColumn<UserFeedback, String> nameColumn;
    @FXML
    private TableColumn<UserFeedback, String> emailColumn;
    @FXML
    private TableColumn<UserFeedback, String> feedbackColumn;
    private ComboBox<String> sortDataByComboBox;
    @FXML
    private Button backToDashboard;

    /**
     * Initializes the controller class.
     */
    private ObservableList<UserFeedback> feedbackList;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        feedbackTypeColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFeedbackType()));
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        emailColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));
        feedbackColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFeedback()));
        ObservableList<UserFeedback> userFeedbacks = UserFeedback.loadFeedbackFromFile();
        feedbackTableView.setItems(userFeedbacks);
        
    }    

    @FXML
    private void backToDashboardOnClick(ActionEvent event) {
    }


}
