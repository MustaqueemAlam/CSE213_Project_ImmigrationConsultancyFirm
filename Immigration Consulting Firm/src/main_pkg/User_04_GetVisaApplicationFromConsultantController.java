/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_04_GetVisaApplicationFromConsultantController implements Initializable {

    @FXML
    private TableView<VisaApplication> tableview;
    @FXML
    private TableColumn<VisaApplication, String> nameCol;
    @FXML
    private TableColumn<VisaApplication, String> contactcol;
    @FXML
    private TableColumn<VisaApplication, LocalDate> appDateCol;
    @FXML
    private TableColumn<VisaApplication, String> visaTypeCol;
    @FXML
    private TableColumn<VisaApplication, Boolean> insuranceCol;
    @FXML
    private TableColumn<VisaApplication, Boolean> enrolllProofCol;
    @FXML
    private TableColumn<VisaApplication, Boolean> financialProofCol;
    @FXML
    private TableColumn<VisaApplication, String> countryCol;
    @FXML
    private TableColumn<VisaApplication, Boolean> visaFormCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactcol.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("prefferedCountry"));
        visaTypeCol.setCellValueFactory(new PropertyValueFactory<>("visaType"));
        appDateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfApplication"));
        insuranceCol.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        financialProofCol.setCellValueFactory(new PropertyValueFactory<>("financialproof"));
        visaFormCol.setCellValueFactory(new PropertyValueFactory<>("visaapplicationformstatus"));
        enrolllProofCol.setCellValueFactory(new PropertyValueFactory<>("enrollment"));
    }

    @FXML
    private void loadRecordsFromVisaProcessingBinFIle(ActionEvent event) {
        
        ArrayList<VisaApplication> visaApplications = DocumentManager.readVisaApplicationsFromFile("VisaProcessingFile.bin");
        ObservableList<VisaApplication> visaApplicationsList = FXCollections.observableArrayList(visaApplications);
        tableview.setItems(visaApplicationsList);
       
    }
      
}
