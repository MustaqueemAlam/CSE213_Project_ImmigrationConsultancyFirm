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


public class TermsAndPoliciesController implements Initializable {

    @FXML
    private TextArea termslabel;
    @FXML
    private Button sendFeedback;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DocumentManager documentManager = new DocumentManager();
        String policyText = documentManager.loadPolicy();
        termslabel.setText(policyText);

    }    

    @FXML
    private void sendFeedbackOnClick(ActionEvent event) {
            try {
            FXMLLoader termsLoader = new FXMLLoader(getClass().getResource("SendFeedBackOnTermsAndPolicy.fxml"));
            Parent termsRoot = termsLoader.load();
            Stage termsStage = new Stage();
            termsStage.setTitle("Send feedback");
            termsStage.setScene(new Scene(termsRoot));
            termsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
