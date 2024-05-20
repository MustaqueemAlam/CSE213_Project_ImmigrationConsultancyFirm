
package main_pkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class ClientReview implements Serializable{
    private String comments;
    private String sessionDate;
    private String rating;  
    private String name; 
    private static String storedName;

    public ClientReview(String comments, String sessionDate, String rating,String name) {
        this.comments = comments;
        this.sessionDate = sessionDate;
        this.rating = rating;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getComments() {
        return comments;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public String getRating() {
        return rating;
    }


    public static Boolean fileWriteReview(ClientReview userInfo){
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Boolean result=false;
        try{
            f= new File("ClientReview.bin");
            if(f.exists()){
                 
                 fos = new FileOutputStream(f,true);
                 oos = new AppendableObjectOutputStream(fos);
            }
            else{
                 
                 fos = new FileOutputStream(f);
                 oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(userInfo);
            result = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(oos!=null) oos.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void viewDetails(String name){
       storedName=name;
    }
    public static String returnDetail(){
        return storedName;
    }
}
