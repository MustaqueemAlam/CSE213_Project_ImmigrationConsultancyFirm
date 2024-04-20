
package main_pkg;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class User_05_UniversityListController implements Initializable {

    @FXML
    private RadioButton ukRadioButton;
    @FXML
    private RadioButton usaRadioButton;
    @FXML
    private RadioButton canadaRadioButton;
    private ToggleGroup togglegroup = new ToggleGroup();
    @FXML
    private TableView<UniversityList> tableview;
    @FXML
    private TableColumn<UniversityList, String> universityNameColumn;
    @FXML
    private TableColumn<UniversityList, Float> ieltsColumn;
    @FXML
    private TableColumn<UniversityList, Integer> greColumn;
    @FXML
    private BarChart<String, Float> barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    private String countryname;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ukRadioButton.setToggleGroup(togglegroup);
       usaRadioButton.setToggleGroup(togglegroup);
       canadaRadioButton.setToggleGroup(togglegroup);
       
       universityNameColumn.setCellValueFactory(new PropertyValueFactory<>("Universityname"));
       ieltsColumn.setCellValueFactory(new PropertyValueFactory<>("IELTS"));
       greColumn.setCellValueFactory(new PropertyValueFactory<>("GRE"));
       
          
    }    

    @FXML
    private void detailButtonOnClick(ActionEvent event) {
        
        if( ukRadioButton.isSelected()){
            countryname="UK";
        }
        else if(usaRadioButton.isSelected()){
            countryname="USA";
        }
         else if(canadaRadioButton.isSelected()){
            countryname="Canada";
        }
        
        ArrayList<UniversityList> mylist = Client.getUniversityReviewFileRead(countryname);
        ObservableList<UniversityList> univerlistlist =  FXCollections.observableArrayList(mylist);
        tableview.setItems(univerlistlist);
        
    
    
    }

    @FXML
    private void acceptanceButtonOnClick(ActionEvent event) {
        
        ArrayList<UniversityList> list = Client.getUniversityReviewFileRead(countryname);
        XYChart.Series series = new XYChart.Series();
        for(UniversityList u: list){
            series.getData().add(new XYChart.Data<>(u.getUniversityname(),u.getAcceptancerate()));
             System.out.println(u.getUniversityname() +  u.getAcceptancerate());

        }
        series.setName("Acceptance Ratio");
        barChart.getData().clear(); 
        barChart.getData().add(series); 
    }
}




    
