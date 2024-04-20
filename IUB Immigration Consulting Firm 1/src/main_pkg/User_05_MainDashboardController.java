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


public class User_05_MainDashboardController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button logout;
    @FXML
    private Label userName;
    @FXML
    private Label clockLabel;
    
    public void setUserFullName(String fullName) {
        userName.setText(fullName);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClockUtil clockUtil = new ClockUtil(clockLabel);
        try {
                Parent parent = FXMLLoader.load(getClass().getResource("User_05_MainDashboardWelcomePage.fxml"));
                borderPane.setCenter(parent);    
            } catch (IOException ex) {
                Logger.getLogger(User_05_MainDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void logout(ActionEvent event) {
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
    private void SubmitImmigrationApplicationOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_05_Submit_Immigration_Application.fxml"));
        borderPane.setCenter(root);
    }

    @FXML
    private void courseOnClick(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("User_05_Courses.fxml"));
        borderPane.setCenter(root);
    }

    @FXML
    private void consultancyOnClick(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("User_05_ConsultancyHours.fxml"));
        borderPane.setCenter(root);
    }

    @FXML
    private void universityOnClick(ActionEvent event) throws IOException {
         Parent root= FXMLLoader.load(getClass().getResource("User_05_UniversityList.fxml"));
        borderPane.setCenter(root);
    }

    @FXML
    private void reviewOnClick(ActionEvent event) throws IOException {
         Parent root= FXMLLoader.load(getClass().getResource("User_05_Review.fxml"));
        borderPane.setCenter(root);
    }

    @FXML
    private void ViewpolicyOnClick(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Terms and Policies.fxml"));
        borderPane.setCenter(root);
    }

    @FXML
    private void navigateToDashboard(ActionEvent event)throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("User_05_MainDashboardWelcomePage.fxml"));
        borderPane.setCenter(parent);
    }

    @FXML
    private void requestForAttorneyOnClick(ActionEvent event) throws IOException {
         Parent root= FXMLLoader.load(getClass().getResource("User_05_RequestForAttorney.fxml"));
        borderPane.setCenter(root);
    }

    @FXML
    private void uploaddocumentOnClick(ActionEvent event) throws IOException {
         Parent root= FXMLLoader.load(getClass().getResource("User_05_UploadDocument.fxml"));
        borderPane.setCenter(root);
    }

    
}
