package main_pkg;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class User_02_MainDashboardController implements Initializable {

    @FXML
    private BorderPane dashboard;
    @FXML
    private Label userName;
    @FXML
    private Label clockLabel;
    @FXML
    private Button logout;
    
    public void setUserFullName(String fullName) {
        userName.setText(fullName);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClockUtil clockUtil = new ClockUtil(clockLabel);
        try {
                Parent parent = FXMLLoader.load(getClass().getResource("User_02_DashboardWelcomeScreen.fxml"));
                dashboard.setCenter(parent);    
            } catch (IOException ex) {
                Logger.getLogger(User_1_MainDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void navigateToDashboard(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_02_DashboardWelcomeScreen.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage currentStage = (Stage) logout.getScene().getWindow();
        currentStage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void checkNotification(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_02_CheckNotification.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void checkClientDocument(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_02_AcessClientDocuments.fxml"));
        dashboard.setCenter(parent);
    }


    @FXML
    private void reimbursementOnClick(ActionEvent event)  throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("RequestReimbursementPayment.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void caseAssignmentOnCLick(ActionEvent event)throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_02_CaseAssignmentPortal.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void caseChartOnClick(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_02_ViewCaseTypeRatioChart.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void SendTermsAndPolicToDocManagerOnClick(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Full Screen Alert");
        alert.setHeaderText(null);
        alert.setContentText("Please view full screen for better experience.");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            Stage stage = (Stage) dashboard.getScene().getWindow(); // Get the Stage from the dashboard's Scene
            stage.setFullScreen(true);
            Parent parent = FXMLLoader.load(getClass().getResource("User_02_WriteTermsAndPolicyAndSendToDocumentManagerForReviewAndUploading.fxml"));
            dashboard.setCenter(parent);
        } 
        if (result.isPresent() && result.get() == noButton){
            Parent parent = FXMLLoader.load(getClass().getResource("User_02_WriteTermsAndPolicyAndSendToDocumentManagerForReviewAndUploading.fxml"));
            dashboard.setCenter(parent);
        }
    }

    @FXML
    private void termsOnClick(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("Terms and Policies.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void viewClintCaseRequests(ActionEvent event)throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_02_ViewClintCase.fxml"));
        dashboard.setCenter(parent);
    }

    
}
