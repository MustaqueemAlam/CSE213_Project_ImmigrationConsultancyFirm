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


public class User_1_MainDashboardController implements Initializable {

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
                Parent parent = FXMLLoader.load(getClass().getResource("User_01_MainDashboardWelcomePage.fxml"));
                dashboard.setCenter(parent);    
            } catch (IOException ex) {
                Logger.getLogger(User_1_MainDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void createAppoinment(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_01_CreateAppoinment.fxml"));
        dashboard.setCenter(parent);
    }

    private void paymentMemo(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("xxx.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void staffAttendence(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_01_TakeAttendance.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void navigateToDashboard(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_01_MainDashboardWelcomePage.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void logout(ActionEvent event){
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
    private void ViewTermsPolicy(ActionEvent event)  throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("Terms and Policies.fxml"));
        dashboard.setCenter(parent);
    }


    @FXML
    private void RequestReimursementforPaymentOnCLick(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("RequestReimbursementPayment.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void ShowCaseAssignmentAppointmentstoClientoNcLICK(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_01_CaseAssignmentPortalViewRecords.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void additionalDutyHoursOnClick(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_01_Send_AdditionalDutyHourRecordsOfEmployeesToAccountant.fxml"));
        dashboard.setCenter(parent);
    }

    @FXML
    private void outsidersAttendanceOnClick(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("User_01_TakeOutsidersAttendance.fxml"));
        dashboard.setCenter(parent);
    }

}
