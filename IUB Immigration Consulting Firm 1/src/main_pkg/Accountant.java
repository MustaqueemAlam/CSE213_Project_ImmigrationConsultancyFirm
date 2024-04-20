package main_pkg;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


    public class Accountant implements Serializable {
//GenerateInvoices  
     public static void addToTable(ComboBox<String> selectProductComboBox, ComboBox<Integer> quantityComboBox,
         float[] predefinedPrices, int[] predefinedVat, ArrayList<Invoices> InvoiceList,
         boolean clientDataEntered) {
         String selectedProduct = selectProductComboBox.getValue();
         float rate = 0.0f;
         float vatRate = 0.0f;
         Integer quantity = quantityComboBox.getValue();
         if (selectedProduct == null || quantity == null) {
             showAlert("Error", "Please select a product and quantity before adding items to the table.");
             return;
         }
         int index = selectProductComboBox.getItems().indexOf(selectedProduct);
             if (index >= 0 && index < predefinedPrices.length && index < predefinedVat.length) {
                 rate = predefinedPrices[index];
                 vatRate = predefinedVat[index];
             }
             boolean isAlreadyAdded = false;
         for (Invoices item : InvoiceList) 
         {
             if (item.getProductName().equals(selectedProduct)) 
                 {
                     item.setQuantity(item.getQuantity() + quantity);
                     isAlreadyAdded = true;
                     break;
                 }
             }
             if (!isAlreadyAdded)
             {
                 Invoices newItem = new Invoices(selectedProduct, rate, vatRate, quantity);
                 InvoiceList.add(newItem);
             }
         }
    ///InvoiceWrite
    private static void saveAsTextFile(String content, int invoiceCounter){ 
        String invoiceFolderPath="I:/.shortcut-targets-by-id/1-5XpgZLlKuPq1oQh87JMzwmlJUVInsPA/FXML Explorers Group Project/IUB Immigration Consulting Firm_2/IUB Immigration Consulting Firm/src/ClientInvoices_GeneratedByAccountant/";
        String fileName = invoiceFolderPath + "Invoice" + invoiceCounter + ".txt";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = sdf.format(new Date());
            fileWriter.write("Invoice generated on: " + dateTime + "\n\n");
            fileWriter.write(content);
            System.out.println("Invoice saved successfully as a text file: " + fileName);
        } catch (IOException e) 
            {
                e.printStackTrace();
                System.out.println("Error saving invoice as a text file!");
            }
        }
    private static void updatePersonalDetailsTextArea(TextArea personalDetailsTextArea, String name, String city,LocalDate dueDateValue, String contactText) {
        String updatedPersonalDetails = "Invoice generated on: " + getCurrentDateTime() + "\n\n" +
                "Bill Summary\n" +
                "Name: " + name + "\n" +
                "City: " + city + "\n" +
                "Due Date: " + dueDateValue + "\n" +
                "Contact: " + contactText + "\n\n";
                personalDetailsTextArea.setText(updatedPersonalDetails);
        }   
        private static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
        }
    public static void checkOutAndShowBill(TextField NameTextFeild, ComboBox<String> CityComboBox, DatePicker dueDate,
        TextField contactTextFeild, ArrayList<Invoices> InvoiceList,
        TextArea personalDetailsTextArea, int invoiceCounter) 
        {
        String name = NameTextFeild.getText().trim();
        String city = CityComboBox.getValue();
        LocalDate dueDateValue = dueDate.getValue();
        String contactText = contactTextFeild.getText().trim();
        if (!clientDataEntered) 
            {
                showAlert("Error", "Please add client data first.");
                return;
            }
        if (name.isEmpty() || city == null || dueDateValue == null || contactText.isEmpty()) 
            {
                showAlert("Error", "Please fill in all the required fields before generating the invoice.");
                return;
            }
        if (!InvoiceList.isEmpty()) {
            StringBuilder billContent = new StringBuilder();
            billContent.append("Bill Summary\n\n");
            billContent.append("Name: ").append(name).append("\n");
            billContent.append("Phone Number: ").append(contactText).append("\n");
            billContent.append("City: ").append(city).append("\n\n");
            billContent.append("Due Date: ").append(dueDateValue).append("\n\n");
                for (Invoices item : InvoiceList) {
                    String productName = item.getProductName();
                    float unitPrice = item.getRate();
                    int quantity = item.getQuantity();
                    float grossTotal = item.getGrossTotal();
                    billContent.append("Service Type: ").append(productName).append(" (  Quantity:  ").append(quantity).append(")\n\n");
                    billContent.append("Rate: ").append(unitPrice).append("\n");
                    billContent.append("Gross Total: ").append(grossTotal).append("\n\n");
                }
            float totalPayable = calculateTotalPayable(InvoiceList);
            billContent.append("Total Payable: ").append(totalPayable).append(" BDT\n");
            Text billText = new Text(billContent.toString());
            billText.setFont(Font.font("Courier New", 12));
            billText.setTextAlignment(TextAlignment.LEFT);
            Alert billAlert = new Alert(Alert.AlertType.INFORMATION);
            billAlert.setTitle("Invoice");
            billAlert.setHeaderText(null);
            DialogPane dialogPane = billAlert.getDialogPane();
            dialogPane.setMinHeight(Region.USE_PREF_SIZE);
            billAlert.getDialogPane().setContent(billText);
            billAlert.showAndWait();
            saveAsTextFile(billContent.toString(), invoiceCounter);
            InvoiceList.clear();
            updatePersonalDetailsTextArea(personalDetailsTextArea, name, city, dueDateValue, contactText);
            clientDataEntered = false;
            } else 
            {
                System.out.println("Table is empty! Nothing to generate an invoice.");
            }
        }
    
    public static void addClient(TextField NameTextFeild, ComboBox<String> CityComboBox, DatePicker dueDate,
        TextField contactTextFeild, TextArea personalDetailsTextArea,
        boolean clientDataEntered) 
        {
        String name = NameTextFeild.getText().trim();
        String city = CityComboBox.getValue();
        LocalDate dueDateValue = dueDate.getValue();
        String contactText = contactTextFeild.getText().trim();
        if (name.isEmpty() || city == null || dueDateValue == null || contactText.isEmpty()) 
        {
            showAlert("Error", "Please fill in all the required fields.");
            return;
        }
        if (!isValidContact(contactText)) {
            showAlert("Error", "Invalid contact number. Please enter a valid 11-digit number starting with 01.");
            return;
        }
        String updatedPersonalDetails = "Invoice generated on: " + getCurrentDateTime() + "\n\n" +
            "Bill Summary\n" +
            "Name: " + name + "\n" +
            "City: " + city + "\n" +
            "Due Date: " + dueDateValue + "\n" +
            "Contact: " + contactText + "\n\n";
        personalDetailsTextArea.setText(updatedPersonalDetails);
        Accountant.clientDataEntered = true;
    }      
    ///Validations:
    private static boolean clientDataEntered = false;
    public static boolean isClientDataEntered() {
        return clientDataEntered;
    }
     public static void setClientDataEntered(boolean value) {
         clientDataEntered = value;
    }
    public static boolean isValidContact(String contactText) 
        {
        return contactText.matches("^01[0-9]{9}$");
        }
    
    private static void showAlert(String title, String message) 
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        }
    static float calculateTotalPayable(ArrayList<Invoices> InvoiceList) 
        {
        float totalPayable = 0.0f;
        for (Invoices item : InvoiceList) 
        {
            float grossTotal = item.getGrossTotal();
            totalPayable += grossTotal;
        }
        return totalPayable;
        }

//AuditReport
    private static final String AUDIT_REPORT_FOLDER_PATH = "I:/.shortcut-targets-by-id/1-5XpgZLlKuPq1oQh87JMzwmlJUVInsPA/FXML Explorers Group Project/IUB Immigration Consulting Firm_2/IUB Immigration Consulting Firm/src/AuditReport_GeneratedByAccountant/";
    public static File browseFile() {
        File auditFolder = new File(AUDIT_REPORT_FOLDER_PATH);
        if (!auditFolder.exists()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Path not found");
            alert.showAndWait();
            return null;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(auditFolder);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("BIN Files", "*.bin"));
        Stage stage = new Stage();
        return fileChooser.showOpenDialog(stage);
    }
    private static int auditReportCount = 1;
    public static void saveAuditReport(String reportTitle, String reportDescription) {
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "AuditReport_" + formattedDateTime + ".bin";
        if (reportTitle.isEmpty() || reportTitle.length() > 20 || reportDescription.length() < 200) {
            return;
        }
        File auditFolder = new File(AUDIT_REPORT_FOLDER_PATH);
        if (!auditFolder.exists()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Path not found");
            alert.showAndWait();
            return;
        }
        File auditFile = new File(auditFolder, fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(auditFile))) {
            oos.writeObject(new AuditReport(reportTitle, reportDescription, formattedDateTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static AuditReport readAuditReport(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (AuditReport) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
//Payroll Processing
    private static final String PAYROLL_FILE_PATH = "payroll_records.bin";
    
    public static void savePayrollRecord(String employeeName, int basicSalary, int overtimeHours,int deductions, LocalDate paymentDate, String designation) {
        Payroll payrollRecord = new Payroll(employeeName, basicSalary, overtimeHours, deductions, paymentDate, designation);
        List<Payroll> existingRecords = loadPayrollRecords();
        existingRecords.add(payrollRecord);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PAYROLL_FILE_PATH))) {
            for (Payroll record : existingRecords) {
                oos.writeObject(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Payroll> loadPayrollRecords() {
        List<Payroll> payrollRecords = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PAYROLL_FILE_PATH))) {
            while (true) {
                try {
                    Payroll record = (Payroll) ois.readObject();
                    payrollRecords.add(record);
                } catch (EOFException e) {
                    break; // Exit loop when EOF is reached
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return payrollRecords;
    }
//ProcessReimbursement
    public static boolean writeReimbursementRecords(ArrayList<ReimbursementRecords> reimbursementRecords, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (ReimbursementRecords reimbursementRecord : reimbursementRecords) {
                oos.writeObject(reimbursementRecord);
            }

            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static ArrayList<ReimbursementRecords> loadReimbursementRecords(String fileName) {
        ArrayList<ReimbursementRecords> existingReimbursementRecords = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    ReimbursementRecords reimbursementRecord = (ReimbursementRecords) ois.readObject();
                    existingReimbursementRecords.add(reimbursementRecord);
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
    }

        return existingReimbursementRecords;
    }
///viewReimbursementRequests
        public static ObservableList<ReimbursementRecords> loadReimbursementRecord(String fileName) {
        ArrayList<ReimbursementRecords> records = loadReimbursementRecords(fileName);
        ObservableList<ReimbursementRecords> observableList = FXCollections.observableArrayList(records);
        
        return observableList;
    }
//OvertimeRatio
    public static double calculateAverageOvertime(String designation) {
           int totalOvertime = 0;
           int totalEmployees = 0;
           for (Payroll payroll : loadPayrollRecords()) {
               if (payroll.getDesignation().equals(designation)) {
                   totalOvertime += payroll.getOvertimeHours();
                   totalEmployees++;
               }
           }
           if (totalEmployees > 0) {
               return (double) totalOvertime / totalEmployees;
           }
           return 0;
       }
// CheckAdditionalTimerecords
    public static List<Object> readObjectsFromFile(String fileName) {
        List<Object> objects = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj != null) {
                       objects.add(obj);
                       }
                   } catch (EOFException e) {
                       break; 
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return objects;
    }


}


