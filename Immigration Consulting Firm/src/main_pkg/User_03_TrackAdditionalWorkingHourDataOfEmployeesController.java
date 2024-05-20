package main_pkg;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class User_03_TrackAdditionalWorkingHourDataOfEmployeesController implements Initializable {

    @FXML
    private ListView<AdditionalDutyHours> listView;
    @FXML
    private TableView<Attendance> tableView;
    @FXML
    private TableColumn<Attendance, String> nameCol;
    @FXML
    private TableColumn<Attendance, String> postCol;
    @FXML
    private TableColumn<Attendance, String> TimeCol;
    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        postCol.setCellValueFactory(new PropertyValueFactory<>("post"));
        TimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        List<Object> additionalDutyHoursList = Accountant.readObjectsFromFile("AdditionalHourRecords.bin");
        List<AdditionalDutyHours> additionalDutyHours = new ArrayList<>();
        for (Object obj : additionalDutyHoursList) {
            if (obj instanceof AdditionalDutyHours) {
                additionalDutyHours.add((AdditionalDutyHours) obj);
            }
        }
        listView.getItems().addAll(additionalDutyHours);
    }    

    @FXML
    private void loadAttendanceRecordOnClick(ActionEvent event) {
        LocalDate currentDate = datePicker.getValue();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = currentDate.format(dateFormatter);
        String fileName = "attendanceRecords_" + dateString + ".bin";

        List<Object> attendanceList = Accountant.readObjectsFromFile(fileName);
        ObservableList<Attendance> loadedItems = FXCollections.observableArrayList();
        for (Object obj : attendanceList) {
            if (obj instanceof Attendance) {
                loadedItems.add((Attendance) obj);
            }
        }
        tableView.getItems().addAll(loadedItems);
    }

}