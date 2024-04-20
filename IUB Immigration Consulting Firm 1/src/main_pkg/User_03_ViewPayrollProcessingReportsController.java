/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main_pkg;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Mustaqueem Alam
 */
public class User_03_ViewPayrollProcessingReportsController implements Initializable {

    @FXML
    private TableView<OvertimeRecord> payrollTableView;
    @FXML
    private TableColumn<OvertimeRecord, String> designationColumn;
    @FXML
    private TableColumn<OvertimeRecord, Integer> overtimeHoursColumn;
    @FXML
    private PieChart overtimeHoursPieChart;
    @FXML
    private ComboBox<String> designationComboBox;
    private List<Payroll> payrollRecords; 
    @FXML
    private BarChart<String, Number> barchart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        designationComboBox.getItems().addAll("HR Manager", "Accountant", "Receptionist",
                "Consultant", "IT Specialist", "Document Manager", "Lawyer");

        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        overtimeHoursColumn.setCellValueFactory(new PropertyValueFactory<>("overtimeHours"));

        payrollRecords = Accountant.loadPayrollRecords();
    }

    @FXML
    private void generateReportandLoadDataOnTableview(ActionEvent event) {
        String selectedDesignation = designationComboBox.getValue();
        if (selectedDesignation == null) {
            showAlert("Please select a designation from the combo box.");
            return;
        }
        int totalOvertimeHours = calculateTotalOvertimeHours(selectedDesignation);
        double overtimeRatio = calculateOvertimeRatio(selectedDesignation, totalOvertimeHours);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Payroll payroll : payrollRecords) {
            double ratio = calculateOvertimeRatio(payroll.getDesignation(), payroll.getOvertimeHours());
            pieChartData.add(new PieChart.Data(
                payroll.getDesignation() + " (" + String.format("%.2f", ratio) + "%)",
                payroll.getOvertimeHours())
            );
        }
        overtimeHoursPieChart.setData(pieChartData);
        ObservableList<OvertimeRecord> selectedOvertimeRecords = FXCollections.observableArrayList();
        for (Payroll payroll : payrollRecords) {
            if (payroll.getDesignation().equals(selectedDesignation)) {
                selectedOvertimeRecords.add(new OvertimeRecord(payroll.getDesignation(), payroll.getOvertimeHours()));
            }
        }
        payrollTableView.setItems(selectedOvertimeRecords);
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Payroll payroll : payrollRecords) {
            if (!payroll.getDesignation().equals(selectedDesignation)) {
                double ratio = calculateOvertimeRatio(payroll.getDesignation(), payroll.getOvertimeHours());
                series.getData().add(new XYChart.Data<>(payroll.getDesignation(), ratio));
            }
        }

        series.getData().add(new XYChart.Data<>(selectedDesignation, overtimeRatio));

        barchart.getData().clear();
        barchart.getData().add(series);
    
    }

    private int calculateTotalOvertimeHours(String designation) {
        int totalOvertime = 0;
        int count = 0;
        for (Payroll payroll : payrollRecords) {
            if (payroll.getDesignation().equals(designation)) {
                totalOvertime += payroll.getOvertimeHours();
                count++;
            }
        }
        return count > 0 ? totalOvertime / count : 0;
    }

    private double calculateOvertimeRatio(String designation, int overtimeHours) {
        int totalOvertime = 0;
        int totalEmployees = 0;
        for (Payroll payroll : payrollRecords) {
            if (!payroll.getDesignation().equals(designation)) {
                totalOvertime += payroll.getOvertimeHours();
                totalEmployees++;
            }
        }
        if (totalEmployees > 0) {
            return (overtimeHours * 100.0) / totalOvertime;
        }
        return 0;
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
