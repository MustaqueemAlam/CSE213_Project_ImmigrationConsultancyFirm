
package main_pkg;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Consultant implements Serializable{
    public static ArrayList<String> genderlist = new ArrayList<>();
    public static ArrayList<String> countryList = new ArrayList<>();
    public Consultant() {
    }
   public static ArrayList<String> getGenderFileRead() throws IOException{
       File f = null;
       FileInputStream fis = null;
       ObjectInputStream ois = null;
       
       try{
           fis = new FileInputStream("Client.bin");
           ois = new ObjectInputStream(fis);
           while(true){
               try{
                   Client client = (Client) ois.readObject();
                   String group = client.getGender();
                   genderlist.add(group);
               }
               catch(EOFException e){
                   break;
               }
                        
           }
       }
       catch(Exception e){
           e.printStackTrace();
       }
       finally{
           try{
               if(ois!=null) ois.close();
           }
           catch(Exception e){
               e.printStackTrace();
           }
       }

       return genderlist;
   }
     public static ArrayList<String> getCountryFileRead() throws IOException{
       File f = null;
       FileInputStream fis = null;
       ObjectInputStream ois = null;
       
       try{
           fis = new FileInputStream("Client.bin");
           ois = new ObjectInputStream(fis);
           while(true){
               try{
                   Client client = (Client) ois.readObject();
                   String group = client.getCountry();
                   countryList.add(group);
               }
               catch(EOFException e){
                   break;
               }
                        
           }
       }
       catch(Exception e){
           e.printStackTrace();
       }
       finally{
           try{
               if(ois!=null) ois.close();
           }
           catch(Exception e){
               e.printStackTrace();
           }
       }

       return countryList;
   }
     public static Boolean UniversityReviewFileWrite(UniversityList list,String countryname){
         File f= null;
         FileOutputStream fos = null;
         ObjectOutputStream oos = null;
         Boolean result = false;
         try{
             f= new File(countryname+"UniversityListandRequirements.bin");
             if(f.exists()){
                 fos = new FileOutputStream(f,true);
                 oos= new AppendableObjectOutputStream(fos);
             }
             else{
                 fos = new FileOutputStream(f);
                 oos= new ObjectOutputStream(fos);
             }
             oos.writeObject(list);
             result=true;
         }
         catch(IOException ex){
             ex.printStackTrace();
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
     public static ArrayList<ConsultantReviewApplication> tableviewreport() {
    ArrayList<ConsultantReviewApplication> myList = new ArrayList<>();
    File f = null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    
    try {
        fis = new FileInputStream("Student.bin");
        ois = new ObjectInputStream(fis);
        
        for (String c : countryList) {
            int male = 0;
            int female = 0;
            
            try {
                while (true) {
                    try {
                        Client client = (Client) ois.readObject();
                        if (client.getCountry().equals(c)) {
                            if (client.getGender().equals("Male")) {
                                male++;
                            } else {
                                female++;
                            }
                        }
                    } catch (EOFException e) {
                        break;
                    }
                }
                
                ConsultantReviewApplication review = new ConsultantReviewApplication(c, male, female);
                myList.add(review);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (ois != null) ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    return myList;
}

         public static Boolean VisaProcessingFileWrite(VisaApplication Info){
         File f= null;
         FileOutputStream fos = null;
         ObjectOutputStream oos = null;
         Boolean result = false;
         try{
             f= new File("VisaProcessingFile.bin");
             if(f.exists()){
                 fos = new FileOutputStream(f,true);
                 oos= new AppendableObjectOutputStream(fos);
             }
             else{
                 fos = new FileOutputStream(f);
                 oos= new ObjectOutputStream(fos);
             }
             oos.writeObject(Info);
             result=true;
         }
         catch(IOException ex){
             ex.printStackTrace();
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
  public static ArrayList<ClientInformation> ReadClientPersonalDocumentFile(){
        ArrayList<ClientInformation> list = new ArrayList<>();
        File f= null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream("ClientDocument.bin");
            ois= new ObjectInputStream(fis);
            while(true){
               try{
                ClientInformation schedule= (ClientInformation) ois.readObject();
                list.add(schedule);
               }
               catch(EOFException ef){
                   break;
               }
            }
        }
        catch(Exception e){
            
        }
        finally{
            try{
                if(ois!=null) ois.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return list;
    }
  public static ArrayList<ConsultancyHour> ReadConsultancyHourFile(){
       File f = null;
       FileInputStream fis = null;
       ObjectInputStream ois = null;
       ArrayList<ConsultancyHour> hours = new ArrayList<>();
       try{
           fis = new FileInputStream("Student.bin");
           ois = new ObjectInputStream(fis);
           while(true){
               try{
                   ConsultancyHour client = (ConsultancyHour) ois.readObject();
                   
                   hours.add(client);
               }
               catch(EOFException e){
                   break;
               }
                        
           }
       }
       catch(Exception e){
           e.printStackTrace();
       }
       finally{
           try{
               if(ois!=null) ois.close();
           }
           catch(Exception e){
               e.printStackTrace();
           }
       }

       return hours;
   }
 
}
