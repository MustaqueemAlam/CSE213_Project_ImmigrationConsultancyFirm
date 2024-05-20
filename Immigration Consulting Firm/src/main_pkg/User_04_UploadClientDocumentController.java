package main_pkg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class User_04_UploadClientDocumentController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField passportField;
    @FXML
    private ComboBox<String> documentTypeComboBox;
    @FXML
    private TableView<ClientDocumentInfo> documentTable;
    @FXML
    private TableColumn<ClientDocumentInfo, String> clientNameColumn;
    @FXML
    private TableColumn<ClientDocumentInfo, String> passportNumberColumn;
    @FXML
    private TableColumn<ClientDocumentInfo, String> documentTypeColumn;
    private ObservableList<ClientDocumentInfo> documentList;
    @FXML
    private Button returnBackTODashboard;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        documentTypeComboBox.getItems().addAll("Passport", "NID", "IELTS", "Notary Form");      
        documentList = FXCollections.observableArrayList();
        documentTable.setItems(documentList);
        DocumentManager.loadDocumentInfoFromBinaryFile(documentList);
        clientNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClientName()));
        passportNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassportNumber()));
        documentTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDocumentType()));       
        documentTable.refresh();
    }
    @FXML
    private void uploadButtonOnClick(ActionEvent event) {
        String clientName = nameField.getText().trim();
        String passportNumber = passportField.getText().trim();
        String documentType = documentTypeComboBox.getValue();

        if (clientName.isEmpty() || passportNumber.isEmpty() || documentType == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all required fields.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            System.out.println("Selected File: " + selectedFile.getAbsolutePath());
            if (!isValidPDF(selectedFile)) {
                showAlert(Alert.AlertType.ERROR, "Error", "Selected file is not a valid PDF.");
                return;
            }

            ClientDocumentInfo documentInfo = new ClientDocumentInfo(clientName, passportNumber, documentType);
            documentList.add(documentInfo);

            DocumentManager.saveDocumentInfoToBinaryFile(documentList);
            documentTable.refresh();

            File destinationFolder = new File("I:/.shortcut-targets-by-id/1-5XpgZLlKuPq1oQh87JMzwmlJUVInsPA/FXML Explorers Group Project/IUB Immigration Consulting Firm_2/IUB Immigration Consulting Firm/src/ClientDocumentInfo_GeneratedByDocumentManager/");
            File destinationFile = new File(destinationFolder, selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to move the PDF file.");
            }
        nameField.clear();
        passportField.clear();
        documentTypeComboBox.getSelectionModel().clearSelection();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "No file selected.");
        }
    }

    private boolean isValidPDF(File file) {
        return file.getName().toLowerCase().endsWith(".pdf");
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void backToDashboardOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_04_MainDashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) returnBackTODashboard.getScene().getWindow();
            currentStage.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
