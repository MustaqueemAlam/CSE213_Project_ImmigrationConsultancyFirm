
package main_pkg;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneChange {

    public SceneChange() {
    }
    
    public void Scenechanger(Stage currentstage,String filename) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(filename));
        Scene scene= new Scene(root);
         currentstage.setScene(scene);
    }
}
