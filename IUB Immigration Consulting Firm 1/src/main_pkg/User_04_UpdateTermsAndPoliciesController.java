/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_04_UpdateTermsAndPoliciesController implements Initializable {

    @FXML
    private TextArea ViewTermsAndPolicyEditableTextArea;

    /**
     * Initializes the controller class.
     */
    private DocumentManager documentManager;
    @FXML
    private Button backToDashboard;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        documentManager = new DocumentManager(); // Use the class-level variable
        String policyText = documentManager.loadPolicy();
        ViewTermsAndPolicyEditableTextArea.setText(policyText);

       
}    

    @FXML
    private void updateTermsOnClick(ActionEvent event) {
        String policyText = ViewTermsAndPolicyEditableTextArea.getText();
        documentManager.savePolicy(policyText);
        
    }

    @FXML
    private void loadPrviousTermsOnClick(ActionEvent event) {
        String policyText = documentManager.loadPolicy();
        ViewTermsAndPolicyEditableTextArea.setText(policyText);
    }

    @FXML
    private void backToDashboardOnClick(ActionEvent event) {
                                        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_04_MainDashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) backToDashboard.getScene().getWindow();
            currentStage.setScene(scene);
            }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
