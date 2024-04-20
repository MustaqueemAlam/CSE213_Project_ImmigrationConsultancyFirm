package main_pkg;

import java.io.*;

public class FileHandler {

    public static void saveUser(User user, String filePath, boolean append) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, append))) {
            outputStream.writeObject(user);
            System.out.println("User data saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save user data.");
        }
    }

    public static User loadUser(String filePath) {
        User user = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            user = (User) inputStream.readObject();
            System.out.println("User data loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load user data.");
        }
        return user;
    }

    public static boolean checkFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
