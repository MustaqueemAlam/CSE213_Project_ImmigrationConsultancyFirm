
package main_pkg;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class User_05_CoursesController implements Initializable {

    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private TextField priceTextfiled;
    @FXML
    private ComboBox<String> timeComboBox;
    ArrayList<Course> myList = new ArrayList<>();
    @FXML
    private Button Back;
    @FXML
    private ComboBox<String> classdayComboBox;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseComboBox.getItems().setAll("IELTS"
                ,"GRE",
                "GMAT","TOEFL");
            courseComboBox.setOnAction(event->{
            if(courseComboBox.getValue()!=null){
                if(courseComboBox.getValue().equals("IELTS")){
                    priceTextfiled.setText("17500");
                }
                else if(courseComboBox.getValue().equals("GRE")){
                    priceTextfiled.setText("20000");
                }
                else if(courseComboBox.getValue().equals("GMAT")){
                    priceTextfiled.setText("30000");
                }
                else if(courseComboBox.getValue().equals("TOEFL")){
                    priceTextfiled.setText("18000");
                }
                else{
                    priceTextfiled.setText("");
                }
            }
        });
        timeComboBox.getItems().addAll("11 A.M","12 A.M","3 P.M"
                ,"4 P.M","6 P.M"
                ,"7 A.M");
        
        classdayComboBox.getItems().addAll("Sunday","Monday","Tuesday","Wednesday","Thursday");
    }    

    @FXML
    private void addButtonOnClick(ActionEvent event) {
        
    String selectedCourse = courseComboBox.getValue();
    String selectedTime = timeComboBox.getValue();
    String selectedDay = classdayComboBox.getValue();

    
    for (Course course : myList) {
        if (course.getCoursename().equals(selectedCourse)) {
            showAlert("Course already selected!");
            return; 
        }
    }

   
    for (Course course : myList) {
        if (course.getTime().equals(selectedTime) && course.getCourseday().equals(selectedDay)) {
            showAlert("Time clash with another course!");
            return; 
        }
    }

    Course newCourse = new Course(selectedCourse, Integer.parseInt(priceTextfiled.getText()), selectedTime, selectedDay);
    myList.add(newCourse);
    }

    @FXML
    private void paymentButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("User_05_CoursePayment.fxml"));
        Parent main= loader.load();
        Scene newscene=new Scene(main);
        User_05_CoursePaymentController controller2=loader.getController();
        controller2.viewcourse(myList);
        Stage newwindow=new Stage();
        newwindow.setTitle("Detail Informations");
        newwindow.setScene(newscene);
        newwindow.show();
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) {
              try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User Login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) Back.getScene().getWindow();
                currentStage.setScene(scene);
            }catch (IOException e) {
                e.printStackTrace();
        }
    }
    private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
}
