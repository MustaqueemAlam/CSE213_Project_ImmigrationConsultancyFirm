
package main_pkg;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;


public class User_05_CoursePaymentController implements Initializable {

    @FXML
    private TableView<Course> tableview;
    @FXML
    private TableColumn<Course, String> coursetableColumn;
    @FXML
    private TableColumn<Course, Integer> pricetableColumn;
    private ObservableList<Course> obs= FXCollections.observableArrayList();
    @FXML
    private TextArea totalpriceTextArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      coursetableColumn.setCellValueFactory(new PropertyValueFactory<>("coursename"));
      pricetableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
      tableview.setItems(obs);
    }
   public void viewcourse(ArrayList<Course> myList){
       for(Course c:myList){
           obs.add(c);
       }
    };    

    @FXML
    private void totalpriceButtonOnClick(ActionEvent event) {
        int totalPrice=Course.priceCourse(obs);
        String items=Course.toString(obs, totalPrice);
        totalpriceTextArea.setText(items);
        totalpriceTextArea.setEditable(false);
    }

    @FXML
    private void downloadPDFSlipasPDF(ActionEvent event) {
            String slipContent = totalpriceTextArea.getText();

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File selectedFile = fileChooser.showSaveDialog(null);

            if (selectedFile != null) {
                try {
                    PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(selectedFile));
                    PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                    Document document = new Document(pdfDocument);

                    // Add slip content to the PDF
                    Paragraph slipParagraph = new Paragraph(slipContent);
                    document.add(slipParagraph);

                    document.close();
                    showConfirmationAlert("Course slip PDF generated and saved successfully.");
                } catch (IOException e) {
                    showErrorAlert("Error generating or saving the PDF.");
                    e.printStackTrace();
                }

            }
        }
        private void showConfirmationAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
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
