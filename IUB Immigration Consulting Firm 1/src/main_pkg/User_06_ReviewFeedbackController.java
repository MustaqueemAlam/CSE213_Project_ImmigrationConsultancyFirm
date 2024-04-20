
package main_pkg;

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

/**
 * FXML Controller class
 *
 * @author User
 */
public class User_06_ReviewFeedbackController implements Initializable {

    @FXML
    private TableView<ClientReview> feedbackTableView;
    @FXML
    private PieChart ratingPieChart;
    @FXML
    private TableColumn<ClientReview, String> nameTableColumn;
    @FXML
    private TableColumn<ClientReview, String> sessionTableColumn;
    @FXML
    private TableColumn<ClientReview, String> ratingTableColumn;
    @FXML
    private TableColumn<ClientReview, String> commentTableColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameTableColumn.setCellValueFactory(new PropertyValueFactory <>("name"));
        sessionTableColumn.setCellValueFactory(new PropertyValueFactory <>("sessionDate"));
        ratingTableColumn.setCellValueFactory(new PropertyValueFactory <>("rating"));
        commentTableColumn.setCellValueFactory(new PropertyValueFactory <>("comments"));
        
    }    

    @FXML
    private void pieChartButtonOnClick(ActionEvent event) {
        ArrayList<String> myList = ClientFeedback.rating();
         ObservableList<PieChart.Data> pieChartData = ratingPieChart.getData();
         pieChartData.clear();
        int count_1=0;
        int count_2=0;
        int count_3=0;
        int count_4=0;
        int count_5=0;
        for(String r:myList){
            if(r.equals("1")){
               count_1=count_1+1; 
            }
            else if(r.equals("2")){
               count_2=count_2+1; 
            }
            else if(r.equals("3")){
               count_3=count_3+1; 
            }
            else if(r.equals("4")){
               count_4=count_4+1; 
            }
            else if(r.equals("5")){
               count_5=count_5+1; 
            }

        }
         if ( count_1> 0) {
        ratingPieChart.getData().add(new PieChart.Data("1", count_1));
       }
        if ( count_2> 0) {
        ratingPieChart.getData().add(new PieChart.Data("2", count_2));
       }
        if ( count_3> 0) {
        ratingPieChart.getData().add(new PieChart.Data("3", count_3));
       }
       if ( count_4> 0) {
        ratingPieChart.getData().add(new PieChart.Data("4", count_4));
       }
       if ( count_5> 0) {
        ratingPieChart.getData().add(new PieChart.Data("5", count_5));
       }
        
    }

    @FXML
    private void showTableButtonOnClick(ActionEvent event) {
        ArrayList<ClientReview> myList = ClientFeedback.reviewFileRead();
        ObservableList<ClientReview> reviewlist = FXCollections.observableArrayList(myList);
         feedbackTableView.setItems(reviewlist);
    }
    
}
