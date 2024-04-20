
package main_pkg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class User_05_ReviewController implements Initializable {

    @FXML
    private TextField clientNameField;
    @FXML
    private TextField sessionDateField;
    @FXML
    private TextField ratingField;
    @FXML
    private TextArea commentsArea;
    @FXML
    private Button submitButton;
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void submitButtonOnClick(ActionEvent event) {
    String clientName = clientNameField.getText();
    String sessionDate = sessionDateField.getText();
    String ratingStr = ratingField.getText();
    String comments = commentsArea.getText();

    if (clientName.isEmpty() || sessionDate.isEmpty() || ratingStr.isEmpty() || comments.isEmpty()) {
        showAlert("Please fill in all form details.");
        return;
    }

    int rating;
    try {
        rating = Integer.parseInt(ratingStr);
        if (rating < 0 || rating > 5) {
            showAlert("Rating should be between 0 and 5.");
            return;
        }
    } catch (NumberFormatException e) {
        showAlert("Invalid rating value. Please enter a number.");
        return;
    }

    ClientReview review = new ClientReview(comments, sessionDate, ratingStr, clientName);
    Boolean isReviewSaved = Client.fileWriteReview(review);

    if (isReviewSaved) {
        showConfirmationAlert("Your review has been recorded.");
    } else {
        showErrorAlert("An error occurred. Please try again.");
    }
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) {
                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User Login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) back.getScene().getWindow();
                currentStage.setScene(scene);
            }catch (IOException e) {
                e.printStackTrace();
        }
    }
    private void showAlert(String message) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

private void showConfirmationAlert(String message) {
    Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation");
    confirmationAlert.setHeaderText(message);
    confirmationAlert.setContentText("Click OK to confirm.");
    confirmationAlert.showAndWait();
}

private void showErrorAlert(String message) {
    Alert errorAlert = new Alert(AlertType.ERROR);
    errorAlert.setTitle("Error");
    errorAlert.setHeaderText("An error occurred");
    errorAlert.setContentText(message);
    errorAlert.showAndWait();
}
        
    
}
