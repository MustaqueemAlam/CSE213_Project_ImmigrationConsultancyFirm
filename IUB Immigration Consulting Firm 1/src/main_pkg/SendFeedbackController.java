/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class SendFeedbackController implements Initializable {

    @FXML
    private TextField nameTextFeild;
    @FXML
    private TextField emailTextFeild;
    @FXML
    private RadioButton concernRadioButton;
    @FXML
    private RadioButton sugRadioButton;
    @FXML
    private RadioButton praiseRadioButton;
    @FXML
    private TextArea feedBackTextArea;

    /**
     * Initializes the controller class.
     */
    
    private ObservableList<UserFeedback> feedbackList = FXCollections.observableArrayList();

    private UserFeedback userFeedback  = new UserFeedback();
    @FXML
    private Button submitButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitFeedbackOnClick(ActionEvent event) {
        String feedbackType = "";
                if (concernRadioButton.isSelected()) {
                    feedbackType = "Concern";
                } else if (sugRadioButton.isSelected()) {
                    feedbackType = "Suggestion";
                } else if (praiseRadioButton.isSelected()) {
                    feedbackType = "Praise";
                } else {
                    showErrorAlert("Please select a feedback type.");
                    return;
                }

                String name = nameTextFeild.getText().trim();
                String email = emailTextFeild.getText().trim();
                String feedback = feedBackTextArea.getText().trim();

                if (name.isEmpty() || email.isEmpty() || feedback.isEmpty()) {
                    showErrorAlert("Please fill in all fields.");
                    return;
                }

                // Use the UserFeedback class to handle feedback submission and saving
                userFeedback.submitFeedbackOnClick(feedbackType, name, email, feedback, feedbackList);

                clearFields();
                showInfoAlert("Feedback submitted successfully.");
}

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        nameTextFeild.clear();
        emailTextFeild.clear();
        feedBackTextArea.clear();
        concernRadioButton.setSelected(false);
        sugRadioButton.setSelected(false);
        praiseRadioButton.setSelected(false);

    }
    
}
