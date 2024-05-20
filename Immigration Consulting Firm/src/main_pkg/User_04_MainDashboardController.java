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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_04_MainDashboardController implements Initializable {

    @FXML
    private Button logout;
    @FXML
    private Label clockLabel;
    @FXML
    private Label userName;
    @FXML
    private Label UPLOADcLIENTdOC;
    @FXML
    private Label MYdRIVE;
    @FXML
    private Label FEEDBACKrEPORT;
    @FXML
    private Label OUTSIDERaTTEN;
    @FXML
    private Label DATAPROCoFcLIENT;
    @FXML
    private Label REQrEIM;
    @FXML
    private Label tRASH;
    @FXML
    private Label bACKuP;
    @FXML
    private Label STORdETAILS;
    @FXML
    private ImageView INVOICErEP;
    @FXML
    private ImageView TERMSfROMlAWYER;
    @FXML
    private ImageView UPDAETteRMSANDpOLICY;

    public void setUserFullName(String fullName) {
        userName.setText(fullName);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClockUtil clockUtil = new ClockUtil(clockLabel);
    }

    @FXML
    private void UPLOADclIENTdOCoNcLICK(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_04_UploadClientDocument.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) UPLOADcLIENTdOC.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void myDriveOnClick(MouseEvent event) {

    }

    @FXML
    private void FeedbackreportOnClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) FEEDBACKrEPORT.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void OutsiderAttOnCLick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_04_ReviewNonEmployeeAttendance.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) OUTSIDERaTTEN.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void CLicentDataProcessOnClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_04_GetVisaApplicationFromConsultant.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) DATAPROCoFcLIENT.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void ReqReimOnClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestReimbursementPayment.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) REQrEIM.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void TrashOnClick(MouseEvent event) {

    }

    @FXML
    private void BackUpOnClick(MouseEvent event) {

    }

    @FXML
    private void StrageDetailOnClick(MouseEvent event) {
    }

    @FXML
    private void InvoiceReportONcLICK(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_04_ReviewAndGenerateReportOnOfflineInvoices.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) INVOICErEP.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void tERMSbYlAWYERoNcLICK(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_04_GetUpdatesAboutTermsAndPolicyFromLawyer.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) TERMSfROMlAWYER.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void uPDATEpOLICYoNcLICK(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_04_UpdateTermsAndPolicies.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) UPDAETteRMSANDpOLICY.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void logoutonClick(ActionEvent event) {
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

}
