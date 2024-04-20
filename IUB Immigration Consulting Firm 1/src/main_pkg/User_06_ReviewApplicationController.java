
package main_pkg;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class User_06_ReviewApplicationController implements Initializable {

    @FXML
    private PieChart pieChart;
  
    @FXML
    private TableColumn<ConsultantReviewApplication, String> countryColumn;
    @FXML
    private TableColumn<ConsultantReviewApplication, Integer> maleColumn;
    @FXML
    private TableColumn<ConsultantReviewApplication, Integer> femaleColumn;
    @FXML
    private TableView<ConsultantReviewApplication> tableView;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("countryname"));
        femaleColumn.setCellValueFactory(new PropertyValueFactory<>("femalenumber"));
        maleColumn.setCellValueFactory(new PropertyValueFactory<>("malenumber"));
    }    

    @FXML
    private void countryratioButtonOnClick(ActionEvent event) throws IOException {
              
               pieChart.getData().clear();
               ArrayList<String> countryList = Consultant.getCountryFileRead();
               ObservableList <PieChart.Data> PieChartData = FXCollections.observableArrayList();
               int count_USA=0;
               int count_UK=0;
               int count_Canada=0;
               int count_Malaysia=0;
               for(String s: countryList){
                   if(s.equals("USA")){
                       count_USA++;
                   }
                   else if(s.equals("UK")){
                       count_UK++;
                   }
                   else if(s.equals("Canada")){
                       count_Canada++;
                   }
                   else if(s.equals("Malaysia")){
                       count_Malaysia++;
                   }
               }
               if(count_USA>0){
                   PieChartData.add(new PieChart.Data("USA", count_USA));
               }
               if(count_UK>0){
                   PieChartData.add(new PieChart.Data("UK",count_UK));
               }
               if(count_Canada>0){
                   PieChartData.add(new PieChart.Data("Canada",count_Canada));
               }
               if(count_Malaysia>0){
                   PieChartData.add(new PieChart.Data("Malaysia",count_Malaysia));
               }
             pieChart.setData(PieChartData);
   }

    @FXML
    private void genderButtonOnClick(ActionEvent event) throws IOException {
        ArrayList<String> genderList = Consultant.getGenderFileRead();
        ObservableList<PieChart.Data> pieChartData = pieChart.getData();
        pieChart.getData().clear();
        pieChart.setPrefWidth(200); // Adjust the preferred width
        pieChart.setPrefHeight(200);
        int count_male=0;
        int count_female=0;
        int count_others=0;
        for(String g: genderList){
            //System.out.println("Gender value: " + g); 
            if(g.equals("Male")){
                count_male=count_male+1;
            }
            else if(g.equals("Female")){
                count_female=count_female+1;
            }
            else if(g.equals("other")){
                
                count_others=count_others+1;
            } 
        }
         if ( count_male> 0) {
        pieChart.getData().add(new PieChart.Data("Male", count_male));
       }
        if ( count_female> 0) {
        pieChart.getData().add(new PieChart.Data("Female", count_female));
       }
        if ( count_others> 0) {
        pieChart.getData().add(new PieChart.Data("other", count_others));
       }
    }

    @FXML
    private void displayTableButtonOnclick(ActionEvent event) {
       ArrayList<ConsultantReviewApplication> mylist=Consultant.tableviewreport();
       ObservableList<ConsultantReviewApplication> reviewList = FXCollections.observableArrayList(mylist);
       tableView.setItems(reviewList);
    }
    
}
