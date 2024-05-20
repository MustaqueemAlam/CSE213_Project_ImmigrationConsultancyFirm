
package main_pkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;


public class User_05_AssignmentController implements Initializable {

    @FXML
    private Label answerLabel;
    @FXML
    private TextArea questionTextArea;
     private ArrayList<String> fileTypeList, fileTypeList2; 
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileTypeList = new ArrayList<>();
        fileTypeList.add("*.txt");
        fileTypeList.add("*.doc");
        fileTypeList.add("*.docx");
        fileTypeList.add("*.TXT");
        fileTypeList.add("*.DOC");
        fileTypeList.add("*.DOCX");

        fileTypeList2 = new ArrayList<String>();
        fileTypeList2.add("*.*");
    }    

    @FXML
    private void ieltsButtonOnClick(ActionEvent event) {
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setHeaderText("Time Limit");
        a.setContentText("Make Sure You Practise This In 15 Mins");
        a.showAndWait();
        questionTextArea.clear();
        File f = null;
        Scanner sc;
        String content = "";
        try {
         f = new File("IELTS.txt");
         sc = new Scanner(f);
         if (f.exists()) {
               questionTextArea.appendText("Content of IELTS.txt:\n");
               while (sc.hasNextLine()) {
               content += sc.nextLine() + " "; 
            }
             String[] contentParts = content.split("[.?]"); 
             for (String part : contentParts) {
                questionTextArea.appendText(part+".\n"); 
             }
         }
         else {
                questionTextArea.setText("Oops! IELTS.txt does not exist...");
         }
      } 
        catch (Exception e) {
        
   }      
       } 
        
    

    @FXML
    private void UploadFileAnswerButtonOnClick(ActionEvent event) {
         FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", fileTypeList));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files", fileTypeList2));
        //File f2 = fc.showSaveDialog(null);
        File f = fc.showOpenDialog(null);
        if(f != null){
            try {
                Scanner sc = new Scanner(f);
                String str="";
                while(sc.hasNextLine()){
                    str+=sc.nextLine()+"\n";
                }
                answerLabel.setText(str);
            } catch (FileNotFoundException ex) {
                
            }
        }
    }
    
    
}
