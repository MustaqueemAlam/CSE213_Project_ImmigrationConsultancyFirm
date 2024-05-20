
package main_pkg;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;



   
    public class Client implements Serializable{
      protected String name;
      protected String email;
      protected String city;
      protected String country;
      protected String gender;
      private String passport;
      private String nid;
      protected LocalDate DOB;
      public static ArrayList<Client> clientList=new ArrayList<>();

    public Client(String name, String email, String city, String country, String gender, String passport, String nid, LocalDate DOB) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.country = country;
        this.gender = gender;
        this.passport = passport;
        this.nid = nid;
        this.DOB = DOB;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getGender() {
        return gender;
    }

    public String getPassport() {
        return passport;
    }

    public String getNid() {
        return nid;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public static ArrayList<Client> getClientList() {
        return clientList;
    }
     private static final long serialVersionUID = 3533266393664733847L;
        public static Boolean FileWriter(Client info){
        File f= null;
        FileOutputStream fos= null;
        ObjectOutputStream oos = null;
        boolean success = false;
        try{            
        
          f = new File("Client.bin");
            if(f.exists()){
                fos= new FileOutputStream(f,true);
                oos= new AppendableObjectOutputStream(fos);
            }
               else{
                fos =new FileOutputStream(f);
                oos =new  ObjectOutputStream(fos);
            }
            oos.writeObject(info);
            success=true;
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
        
       return success;   
    }
        public static ArrayList<Client> getlist(){
            return clientList;
        }
        
    public static ArrayList<UniversityList> getUniversityReviewFileRead(String name) {
       File f = null;
       FileInputStream fis = null;
       ObjectInputStream ois = null;
       ArrayList<UniversityList> newlist= new ArrayList<>();
       try{
           fis = new FileInputStream(name+"UniversityListandRequirements.bin");
           ois = new ObjectInputStream(fis);
           while(true){
               try{
                    UniversityList list = (UniversityList) ois.readObject();
                   
                   newlist.add(list);
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

       return newlist;
}
     public static Boolean ConsultancyhourFileWriter(ConsultancyHour schedule){
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean result=false;
        
        try{
            f= new File("ConsultancyHours.bin");
            if(f.exists()){
                 fos = new FileOutputStream(f,true);
                 oos= new AppendableObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream(f);
                oos= new ObjectOutputStream(fos);
            }
            oos.writeObject(schedule);
            result=true;
        }
        catch(IOException e){
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
     
   
      public static Boolean RequestForAttorneyFileWriter(Case request){
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean result=false;
        
        try{
            f= new File("RequestForAttorney.bin");
            if(f.exists()){
                 fos = new FileOutputStream(f,true);
                 oos= new AppendableObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream(f);
                oos= new ObjectOutputStream(fos);
            }
            oos.writeObject(request);
            result=true;
        }
        catch(IOException e){
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
      
      
     
     
    

      public static Boolean UploadClientDocumentFileWriter(ClientInformation document){
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean result=false;
        
        try{
            f= new File("ClientDocument.bin");
            if(f.exists()){
                 fos = new FileOutputStream(f,true);
                 oos= new AppendableObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream(f);
                oos= new ObjectOutputStream(fos);
            }
            oos.writeObject(document);
            result=true;
        }
        catch(IOException e){
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


}

    
    