
package main_pkg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class User_06_MainDashBoardController implements Initializable {

    @FXML
    private BorderPane borderpan;
    @FXML
    private Button logout;
    @FXML
    private Label userName;
    @FXML
    private Label clockLabel;
    
    public void setUserFullName(String fullName) {
        userName.setText(fullName);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClockUtil clockUtil = new ClockUtil(clockLabel);
        try {
                Parent parent = FXMLLoader.load(getClass().getResource("User_06_MainDashboardWelcomePage.fxml"));
                borderpan.setCenter(parent);    
            } catch (IOException ex) {
                Logger.getLogger(User_06_MainDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void reviewapplicationOnClick(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("User_06_ReviewApplication.fxml"));
         borderpan.setCenter(root);
    }

    @FXML
    private void checkappoinmentsOnClick(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("User_06_CheckAppoinment.fxml"));
         borderpan.setCenter(root);
    }

    @FXML
    private void logoutButtonOnClick(ActionEvent event) {
                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User Login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) logout.getScene().getWindow();
                currentStage.setScene(scene);
            }catch (IOException e) {
                e.printStackTrace();
        }
    }

    @FXML
    private void clientReviewOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_06_ReviewFeedback.fxml"));
         borderpan.setCenter(root);
    }

    private void BackToDashboard(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_06_MainDashBoard.fxml"));
        borderpan.setCenter(parent);
    }

    @FXML
    private void termsAndPOlicyOnCLick(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("Terms and Policies.fxml"));
        borderpan.setCenter(parent);
    }

    @FXML
    private void reqForReimbursementPAymentOnCLick(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("RequestReimbursementPayment.fxml"));
        borderpan.setCenter(parent);
    }

    @FXML
    private void univerListOnClick(ActionEvent event) throws IOException {
         Parent parent = FXMLLoader.load(getClass().getResource("User_06_UniversityListReview.fxml"));
        borderpan.setCenter(parent);
    }

    @FXML
    private void navigateToDashboard(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_06_MainDashboardWelcomePage.fxml"));
        borderpan.setCenter(parent);
    }

    @FXML
    private void visaprocessOnClcik(ActionEvent event)throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_06_VisaProcessingForClients.fxml"));
        borderpan.setCenter(parent);
    }

    @FXML
    private void checkDocumentsOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("User_06_CheckClientPDocuments.fxml"));
        borderpan.setCenter(parent);
    }
    
    
}
