package main_pkg;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import static main_pkg.Accountant.loadReimbursementRecords;
/**
 *
 * @author Mustaqueem Alam
 */
public class DocumentManager implements Serializable{  
    //   uploadclientDocuments
    private static final String BIN_FILE_PATH = "ClientDocumentsDatabase.bin";
    public static void saveDocumentInfoToBinaryFile(ObservableList<ClientDocumentInfo> documentList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIN_FILE_PATH))) {
            for (ClientDocumentInfo documentInfo : documentList) {
                oos.writeObject(documentInfo);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print the exception details
        }
    }
    public static void loadDocumentInfoFromBinaryFile(ObservableList<ClientDocumentInfo> documentList) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BIN_FILE_PATH))) {
            while (true) 
            {
                ClientDocumentInfo documentInfo = (ClientDocumentInfo) ois.readObject();
                documentList.add(documentInfo);
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void uploadDocumentAndAddInfo(ComboBox<String> documentTypeComboBox, TableView<ClientDocumentInfo> documentTable,
        ObservableList<ClientDocumentInfo> documentList, TextField nameField, TextField passportField) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialDirectory(new File("I:/.shortcut-targets-by-id/1-5XpgZLlKuPq1oQh87JMzwmlJUVInsPA/FXML Explorers Group Project/IUB Immigration Consulting Firm_2/IUB Immigration Consulting Firm/src/ClientDocumentInfo_GeneratedByDocumentManager/"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            System.out.println("Selected File: " + selectedFile.getAbsolutePath());
            if (!isValidPDF(selectedFile)) {
                showAlert("Error", "Selected file is not a valid PDF.");
                return;
            }
            String documentType = documentTypeComboBox.getValue().toString();
            String clientName = nameField.getText().trim();
            String passportNumber = passportField.getText().trim();
            documentList.add(new ClientDocumentInfo(clientName, passportNumber, documentType));
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIN_FILE_PATH, true))) {
                oos.writeObject(new ClientDocumentInfo(clientName, passportNumber, documentType));
            }
            catch (IOException e) 
            {
                showAlert("Error", "Failed to save data to file.");
            }
        }
    }   
    private static boolean isValidPDF(File file) 
    {
        return file.getName().toLowerCase().endsWith(".pdf");
    }
    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
 
    //update privacy and policy
    private final File policyFile;

    public DocumentManager() {
        policyFile = new File("Terms.bin");
    }

    public void savePolicy(String policyText) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(policyFile))) {
            oos.writeObject(policyText);
            showAlert("Policy saved successfully.", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            showAlert("Error saving policy.", Alert.AlertType.ERROR);
        }
    }

    public String loadPolicy() {
        String policyText = "";

        if (policyFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(policyFile))) {
                policyText = (String) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                showAlert("Error loading policy.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Policy file not found.", Alert.AlertType.WARNING);
        }

        return policyText;
    }
    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Policy Update");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //report on invoices
    public static String readTextFile(String filePath) {
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return content.toString();
    }
    public static void saveInvoiceReports(List<InvoiceReport> reports, String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(reports);
            System.out.println("Invoice reports saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<InvoiceReport> LoadInvoiceReports(String filePath) {
        List<InvoiceReport> invoiceReports = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            invoiceReports = (List<InvoiceReport>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return invoiceReports;
    }
    //req reimbursement
    public static boolean writeReimbursements(ObservableList<Reimbursement> reimbursements, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (Reimbursement reimbursement : reimbursements) {
                oos.writeObject(reimbursement);
            }

            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Reimbursement> loadReimbursements(String fileName) {
        ArrayList<Reimbursement> existingReimbursements = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Reimbursement reimbursement = (Reimbursement) ois.readObject();
                    existingReimbursements.add(reimbursement);
                } catch (EOFException eof) {
                    // End of file reached, break the loop
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return existingReimbursements;
    }
//visaApp
    public static ArrayList<VisaApplication> readVisaApplicationsFromFile(String fileName) {
        ArrayList<VisaApplication> list = new ArrayList<>();
        File f= null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(fileName);
            ois= new ObjectInputStream(fis);
            while(true){
               try{
                VisaApplication Info= (VisaApplication) ois.readObject();
                list.add(Info);
               }
               catch(EOFException ef){
                   break;
               }
            }
        }
        catch(Exception e){
            
        }
        finally{
            try{
                if(ois!=null) ois.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return list;
    }
    // read outside attendance---------------------------
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





































































































        //ObservableList<VisaApplication> visaApplicationsList = DocumentManager.loadVisaAppRecord("VisaProcessingFile.bin");
        //tableview.setItems(visaApplicationsList);
        //public static ArrayList<VisaApplication> readVisaAppRecords(String fileName) {
        //ArrayList<VisaApplication> Records = new ArrayList<>();

       // try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
           // while (true) {
           //     try {
            //        VisaApplication reimbursementRecord = (VisaApplication) ois.readObject();
            //        Records.add(reimbursementRecord);
           // //    } catch (EOFException eof) {
            //        break;
           // /    }
          //  }
       // } catch (IOException | ClassNotFoundException ex) {
    //}

       // return Records;
    //}
///viewReimbursementRequests
     //   public static ObservableList<VisaApplication> loadVisaAppRecord(String fileName) {
      ///  ArrayList<VisaApplication> records = readVisaAppRecords(fileName);
      ///  ObservableList<VisaApplication> observableList = FXCollections.observableArrayList(records);
        
       // return observableList;
  //  }
    
 