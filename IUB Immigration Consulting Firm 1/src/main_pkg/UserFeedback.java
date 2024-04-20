package main_pkg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class UserFeedback implements Serializable {
    private String feedbackType;
    private String name;
    private String email;
    private String feedback;

    public UserFeedback() {
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    @Override
    public String toString() {
        return "UserFeedback{" + "feedbackType=" + feedbackType + ", name=" + name + ", email=" + email + ", feedback=" + feedback + '}';
    }

    public UserFeedback(String feedbackType, String name, String email, String feedback) {
        this.feedbackType = feedbackType;
        this.name = name;
        this.email = email;
        this.feedback = feedback;
    }

    private final String feedbackFileName = "UserFeedback.bin";

    private static class SerializableFeedbackList implements Serializable {
        private List<UserFeedback> feedbackList;

        public SerializableFeedbackList(List<UserFeedback> feedbackList) {
            this.feedbackList = feedbackList;
        }

        public List<UserFeedback> getFeedbackList() {
            return feedbackList;
        }
    }

    public void submitFeedbackOnClick(String feedbackType, String name, String email, String feedback, ObservableList<UserFeedback> feedbackList) {
        UserFeedback userFeedback = new UserFeedback(feedbackType, name, email, feedback);
        feedbackList.add(userFeedback);

        saveFeedbackToFile(feedbackList);
    }

    private void saveFeedbackToFile(ObservableList<UserFeedback> feedbackList) {
        SerializableFeedbackList serializableFeedbackList = new SerializableFeedbackList(new ArrayList<>(feedbackList));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(feedbackFileName))) {
            oos.writeObject(serializableFeedbackList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final long serialVersionUID = -3387450580070637973L; // Keep this consistent

    public static ObservableList<UserFeedback> loadFeedbackFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("UserFeedback.bin"))) {
            SerializableFeedbackList serializableFeedbackList = (SerializableFeedbackList) ois.readObject();
            List<UserFeedback> feedbackList = serializableFeedbackList.getFeedbackList();
            return FXCollections.observableArrayList(feedbackList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }
}
