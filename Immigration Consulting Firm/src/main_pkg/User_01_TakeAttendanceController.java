package main_pkg;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class User_01_TakeAttendanceController implements Initializable {

    
    @FXML
    private ComboBox<String> nameComboBox;
    
    @FXML
    private TableView<Attendance> tableView;
    @FXML
    private TableColumn<Attendance, String> nameCol;
    @FXML
    private TableColumn<Attendance, String> postCol;
    @FXML
    private TableColumn<Attendance, String> TimeCol;
    @FXML
    private ComboBox<String> postComboBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        postCol.setCellValueFactory(new PropertyValueFactory<>("post"));
        TimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = currentDate.format(dateFormatter);
        String fileName = "attendanceRecords_" + dateString + ".bin";
        List<Object> attendanceList = Receptionist.readObjectsFromFile(fileName);
        ObservableList<Attendance> loadedItems = FXCollections.observableArrayList();
        for (Object obj : attendanceList) {
            if (obj instanceof Attendance) {
                loadedItems.add((Attendance) obj);
            }
        }
        tableView.getItems().addAll(loadedItems);

        
        
        postComboBox.getItems().addAll("Receptionist", "Lawyer", "Accountant",
                "Document Manager", "Consultant",
                "IT Support Specialist", "Human Resources Manager");

        postComboBox.setOnAction(event -> {
            String selectedPost = postComboBox.getValue();
            if (null == selectedPost) {
                nameComboBox.getItems().clear();
            } else {
                switch (selectedPost) {
                    case "Receptionist":
                        nameComboBox.getItems().clear();
                        nameComboBox.getItems().addAll("Naima Siddiqui", "Sumaiya Akter");
                        break;
                    case "Lawyer":
                        nameComboBox.getItems().clear();
                        nameComboBox.getItems().addAll("Fahmida Haque", "Sumaiya Akter");
                        break;
                    case "Accountant":
                        nameComboBox.getItems().clear();
                        nameComboBox.getItems().addAll("Nasrin Ahmed", "Zahirul Islam Chowdhury");
                        break;
                    case "Document Manager":
                        nameComboBox.getItems().clear();
                        nameComboBox.getItems().addAll("Nafisa Rahman", "Jamaluddin Ahmed");
                        break;
                    case "Consultant":
                        nameComboBox.getItems().clear();
                        nameComboBox.getItems().addAll("Farida Akhter", "Shahidul Haque Siddique", "Raihan Atiq", "Zahid Islam");
                        break;
                    case "IT Support Specialist":
                        nameComboBox.getItems().clear();
                        nameComboBox.getItems().addAll("Rumana Khan", "Mahbubul Alam Choudhury");
                        break;
                    case "Human Resources Manager":
                        nameComboBox.getItems().clear();
                        nameComboBox.getItems().add("Sadekur Rahman");
                        break;
                    default:
                        nameComboBox.getItems().clear();
                        break;
                }
            }
        });
    }

    
    @FXML
    private void submitButtonOnClick(ActionEvent event) {
        String selectedPost = postComboBox.getValue();
        String selectedName = nameComboBox.getValue();

        if (selectedPost != null && selectedName != null) {
            String checkInTime = ClockUtil.getCurrentTimeString();

            tableView.getItems().add(new Attendance(selectedName, selectedPost, checkInTime));
        }
    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(formatter);
    }

    @FXML
    private void createButtonOnClick(ActionEvent event) {
        ObservableList<Attendance> items = tableView.getItems();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = currentDate.format(dateFormatter);
        String fileName = "attendanceRecords_" + dateString + ".bin";
        boolean addStatus = Receptionist.write_Objects_ToFile(items, fileName);
        if (addStatus) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Attendance Added Successfully!");
                a.showAndWait();
        } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Oops, something went wrong");
                a.showAndWait();
        }
    }

    @FXML
    private void saveAsPDF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(new Stage());

        if (selectedFile != null) {
            try {
                PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(selectedFile));
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                Document document = new Document(pdfDocument);

                document.add(new Paragraph("Attendance Report"));

                ObservableList<Attendance> items = tableView.getItems();
                for (Attendance attendance : items) {
                    document.add(new Paragraph("Name: " + attendance.getName()));
                    document.add(new Paragraph("Post: " + attendance.getPost()));
                    document.add(new Paragraph("Time: " + attendance.getTime()));
                    document.add(new Paragraph("----------------------------------"));
                }

                document.close();
                showAlert("PDF file saved successfully!");
            } catch (IOException e) {
                showErrorAlert("Error saving PDF.");
                e.printStackTrace();
            }
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}