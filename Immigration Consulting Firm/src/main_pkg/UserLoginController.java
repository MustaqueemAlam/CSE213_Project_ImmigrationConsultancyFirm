package main_pkg;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class UserLoginController implements Initializable {

    @FXML
    private TextField passwordTextField;
    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private Button signupButton;
    @FXML
    private TextField userIdTextField;
    @FXML
    private Label clockLabel;
    @FXML
    private Button back;
    @FXML
    private Button login;
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeComboBox.getItems().addAll("Receptionist","Lawyer",
                "Accountant","Document Manager","Client"
        ,"Consultant","IT Support Specialist","Human Resources Manager");
        
        ClockUtil clockUtil = new ClockUtil(clockLabel);
        //this 2 lines will be deleted when all the work is done
        userIdTextField.setText("admin");
        passwordTextField.setText("12345678");
    }    
    
    
    @FXML
    private void forgotPasswordButtonOnClick(ActionEvent event) {
    }

    @FXML
    private void loginButtonOnClick(ActionEvent event) throws IOException {
        String userType = userTypeComboBox.getValue();
            if (userType == null) {
        showErrorAlert("Error", "Please select a user type.");
        return;
        }

        String username = userIdTextField.getText();
        String password = passwordTextField.getText();
 
        
        boolean isValidLogin = SignUpFile.SignUpFileRead(userType, username, password); 

        if (isValidLogin) {
            
            String userFullName = SignUpFile.FileRead_forUserName(userType, username, password);
            
             Stage currentStage = (Stage) login.getScene().getWindow();
             if(userType.equals("Receptionist")){
                 //SceneChange newScene= new SceneChange();
                 //newScene.Scenechanger(currentStage, "User_1_MainDashboard.fxml");
                 
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("User_1_MainDashboard.fxml"));
                 Parent root = loader.load();
                 User_1_MainDashboardController dashboardController = loader.getController();
                 dashboardController.setUserFullName(userFullName); 
                 Scene scene = new Scene(root);
                 currentStage.setScene(scene);
             }
             if(userType.equals("Lawyer")){
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("User_02_MainDashboard.fxml"));
                 Parent root = loader.load();
                 User_02_MainDashboardController dashboardController = loader.getController();
                 dashboardController.setUserFullName(userFullName); 
                 Scene scene = new Scene(root);
                 currentStage.setScene(scene);
             }
            if(userType.equals("Accountant")){
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("User_03_MainDashboard.fxml"));
                 Parent root = loader.load();
                 User_03_MainDashboardController dashboardController = loader.getController();
                 dashboardController.setUserFullName(userFullName); 
                 Scene scene = new Scene(root);
                 currentStage.setScene(scene);
             }
             if(userType.equals("Document Manager")){
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("User_04_MainDashboard.fxml"));
                 Parent root = loader.load();
                 User_04_MainDashboardController dashboardController = loader.getController();
                 dashboardController.setUserFullName(userFullName); 
                 Scene scene = new Scene(root);
                 currentStage.setScene(scene);
             }
             if(userType.equals("Client")){
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("User_05_MainDashboard.fxml"));
                 Parent root = loader.load();
                 User_05_MainDashboardController dashboardController = loader.getController();
                 
                 dashboardController.setUserFullName(userFullName); 
                 Scene scene = new Scene(root);
                 currentStage.setScene(scene);
             }
            if(userType.equals("Consultant")){
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("User_06_MainDashBoard.fxml"));
                 Parent root = loader.load();
                 User_06_MainDashBoardController dashboardController = loader.getController();
                 dashboardController.setUserFullName(userFullName); 
                 Scene scene = new Scene(root);
                 currentStage.setScene(scene);
             }
            if(userType.equals("IT Support Specialist")){
                 SceneChange newScene= new SceneChange();
                 newScene.Scenechanger(currentStage, "User_07_MainDashboard.fxml");
             }
             if(userType.equals("Human Resources Manager")){
                 SceneChange newScene= new SceneChange();
                 newScene.Scenechanger(currentStage, "User_08_MainDashBoard.fxml");
             }
        }
        
        else{
            
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Unable to Login");
            a.setContentText("UserID and Password is Invalid");
            a.showAndWait();
          
        }

        
        
    }
    @FXML
    private void signUpButtonOnClick(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign Up.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Get the current stage from any node in the current scene
                Stage currentStage = (Stage) signupButton.getScene().getWindow();
                currentStage.setScene(scene);
            }catch (IOException e) {
                e.printStackTrace();
        }
        
    }

    @FXML
    private void backonClick(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home_Screen.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Get the current stage from any node in the current scene
                Stage currentStage = (Stage) signupButton.getScene().getWindow();
                currentStage.setScene(scene);
            }catch (IOException e) {
                e.printStackTrace();
        }
        
    }

    private void ExitButtonOnClick(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    

}
