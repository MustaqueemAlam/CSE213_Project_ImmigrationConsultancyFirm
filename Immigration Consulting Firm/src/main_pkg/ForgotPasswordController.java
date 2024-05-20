/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField userIdTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField verificationCodeTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getCodeButtonOnClick(ActionEvent event) {
    }

    @FXML
    private void nextButtonOnClick(ActionEvent event) {
    }

    @FXML
    private void previousButtonOnClick(ActionEvent event) {
    }
    
}
