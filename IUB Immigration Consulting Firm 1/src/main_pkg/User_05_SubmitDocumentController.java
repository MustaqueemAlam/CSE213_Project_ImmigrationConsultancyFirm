
package main_pkg;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;


public class User_05_SubmitDocumentController implements Initializable {

    
    
    @FXML
    private ImageView sscImageView;
    @FXML
    private ImageView hscImageView;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    
    @FXML
    private void sscfileOnClick(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        try{
            if(f!=null){
                Image image = new Image(f.toURI().toString());
                sscImageView.setImage(image);
            }           
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void hscfileOnClick(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        try{
            if(f!=null){
                Image image = new Image(f.toURI().toString());
                hscImageView.setImage(image);
            }           
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
