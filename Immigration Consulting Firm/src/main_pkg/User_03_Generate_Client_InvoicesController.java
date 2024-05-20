package main_pkg;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleFloatProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;




public class User_03_Generate_Client_InvoicesController implements Initializable {
    @FXML
    private ComboBox<String> selectProductComboBox;
    @FXML
    private TextField rateTextFeild;
    @FXML
    private ComboBox<Integer> quantityComboBox;
    @FXML
    private TextField vatTextField;
    @FXML
    private TableView<Invoices> tableVIew;
    @FXML
    private TableColumn<Invoices, String> productNameCol;
    @FXML
    private TableColumn<Invoices, Integer> quantityCol;
    @FXML
    private TableColumn<Invoices, Float> vatCol;
    @FXML
    private TableColumn<Invoices, Float> vatAmountCol;
    @FXML
    private TableColumn<Invoices, Float> grossTotalCol;
    @FXML
    private Label totalPayableTextField;
    @FXML
    private TextField NameTextFeild;
    @FXML
    private ComboBox<String> CityComboBox;
    @FXML
    private DatePicker dueDate;
    @FXML
    private TextField contactTextFeild;
    @FXML
    private TextArea personalDetailsTextArea;
    @FXML
    private TableColumn<Invoices, Float> rateCol;   
    private ArrayList<Invoices> InvoiceList;
    private final float[] predefinedPrices = {500.0f, 750.0f, 1200.0f, 2000.0f, 1200.0f, 300.0f, 500.0f, 350.0f};
    private final int[] predefinedVat = {3, 5, 3, 5, 1, 0, 5, 4};
    private int invoiceCounter = 1;
    private static boolean clientDataEntered = false;
    private Button BACK;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        selectProductComboBox.getItems().addAll("Appointment Fee", "Document Preparation Fee", "Application Processing Fee",
        "Case Management Fee", "Translation Services", "Filing Fees", "Notary Fees", "Miscellaneous Charges");
        
        quantityComboBox.getItems().addAll(1, 2, 3);
        
        selectProductComboBox.setOnAction(this::handleProductSelection);
        
        CityComboBox.getItems().addAll("Dhaka", "CTG", "Cumilla", "Barisal", "Sylhet", "Khulna", "Rajshahi");
        
        invoiceCounter = Invoices.findHighestInvoiceNumber(totalPayableTextField, invoiceCounter);
        
        InvoiceList = new ArrayList<>();
        
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        rateCol.setCellValueFactory(new PropertyValueFactory<>("rate"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        vatCol.setCellValueFactory(new PropertyValueFactory<>("vatRate"));
        
        vatAmountCol.setCellValueFactory(cellData -> {
            float rate = cellData.getValue().getRate();
            int quantity = cellData.getValue().getQuantity();
            float vatRate = cellData.getValue().getVatRate();
            float vatAmount = (rate * quantity * vatRate) / 100;
            return new SimpleFloatProperty(vatAmount).asObject();
        });
        
        grossTotalCol.setCellValueFactory(cellData -> {
            float rate = cellData.getValue().getRate();
            int quantity = cellData.getValue().getQuantity();
            float vatRate = cellData.getValue().getVatRate();
            float vatAmount = (rate * quantity * vatRate) / 100;
            float grossTotal = (rate * quantity) + vatAmount;
            return new SimpleFloatProperty(grossTotal).asObject();
        });
        
        Invoices.findHighestInvoiceNumber(totalPayableTextField, invoiceCounter);
    }
    
    @FXML
    private void addToTableOnClick(ActionEvent event) {
        if (selectProductComboBox.getSelectionModel().isEmpty() || quantityComboBox.getSelectionModel().isEmpty()) {
            showAlert("Error", "Please select a product and quantity before adding items to the table.");
            return;
        }
        Invoices.addToTable(selectProductComboBox, quantityComboBox, predefinedPrices, predefinedVat, InvoiceList, clientDataEntered, rateTextFeild, vatTextField);
        selectProductComboBox.getSelectionModel().clearSelection();
        quantityComboBox.getSelectionModel().clearSelection();
        Invoices.calculateTotalPayableAndUpdateView(InvoiceList, totalPayableTextField, tableVIew, grossTotalCol);     
    }
    
    @FXML
    private void checkOutAndShowBillButtonOnClick(ActionEvent event) {
        
        String name = NameTextFeild.getText().trim();
        
        if (!isValidName(name)) {
            showAlert("Error", "Invalid name. Please enter a valid name without numeric characters.");
            return;
        }
        
        if (NameTextFeild.getText().trim().isEmpty() ||
                    CityComboBox.getValue() == null ||
                    dueDate.getValue() == null ||
                    contactTextFeild.getText().trim().isEmpty()) {
                    showAlert("Error", "Please fill in all the required fields before generating the invoice.");
                    return;
                }
        
        invoiceCounter = Invoices.findHighestInvoiceNumber(totalPayableTextField, invoiceCounter);
        
        Accountant.checkOutAndShowBill(NameTextFeild, CityComboBox, dueDate, contactTextFeild, InvoiceList, personalDetailsTextArea, invoiceCounter);
        
        clearFields();
        tableVIew.getItems().clear();
        vatTextField.clear();
        rateTextFeild.clear();
    }
    
    private boolean isValidName(String name) {
        return !name.isEmpty() && !name.matches(".*\\d.*");
    }
    
    @FXML
    private void addClientOnClick(ActionEvent event) {
        if (NameTextFeild.getText().trim().isEmpty() ||
            CityComboBox.getValue() == null ||
            dueDate.getValue() == null ||
            contactTextFeild.getText().trim().isEmpty()) {
            showAlert("Error", "Please fill in all the required fields.");
            return;
        }
        if (!Accountant.isValidContact(contactTextFeild.getText().trim())) {
            showAlert("Error", "Invalid contact number. Please enter a valid 11-digit number starting with 01.");
            return;
        }
        
        Accountant.addClient(NameTextFeild, CityComboBox, dueDate, contactTextFeild, personalDetailsTextArea, clientDataEntered);       
    }
    
    private void handleProductSelection(ActionEvent event) {
        Invoices.handleProductSelection(selectProductComboBox, rateTextFeild, vatTextField, predefinedPrices, predefinedVat);
    }
    
    private void clearFields() {
        
        NameTextFeild.clear();
        CityComboBox.getSelectionModel().clearSelection();
        dueDate.setValue(null);
        contactTextFeild.clear();
        personalDetailsTextArea.clear();
        InvoiceList.clear();
        totalPayableTextField.setText("");
        
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void detailsOnClick(ActionEvent event) {
        try {
            FXMLLoader termsLoader = new FXMLLoader(getClass().getResource("User_03_Goal_01_Details.fxml"));
            Parent termsRoot = termsLoader.load();
            Stage termsStage = new Stage();
            termsStage.setTitle("Details of GENERATE INVOICES GOAL");
            termsStage.setScene(new Scene(termsRoot));
            termsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goBackOnCLick(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User_03_MainDashboard.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) BACK.getScene().getWindow();
                currentStage.setScene(scene);
            }catch (IOException e) {
                e.printStackTrace();
        }
    }
}
