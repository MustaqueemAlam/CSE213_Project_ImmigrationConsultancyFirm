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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


    public class Receptionist implements Serializable {

    static boolean writeObjectsToFile(ObservableList<Attendance> items, String lawyer_Appointmentbin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String userName;

    public Receptionist(String userName){
                this.userName = userName;
    }
    public String getUserName() {
            return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Override
    public String toString() {
        return "User_01_Receptionist{" + "userName=" + userName + '}';
    }
    
    
    
    //-------------File read & write for Receptionist
    public static List<Object> readObjectsFromFile(String fileName) {
           List<Object> objects = new ArrayList<>();
           try (FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
               while (true) {
                   try {
                       Object obj = ois.readObject();
                       if (obj != null) {
                           objects.add(obj);
                       }
                   } catch (EOFException e) {
                       break; 
                   }
               }
           } catch (IOException | ClassNotFoundException ex) {
               ex.printStackTrace();
           }
           return objects;
           }
    
        public static boolean writeObjectsToFile(List<Object> objects, String fileName) {
           try (FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
               for (Object obj : objects) {
                   oos.writeObject(obj);
               }
               return true;
           } catch (IOException ex) {
               ex.printStackTrace();
               return false;
           }
       }

       //----------------------------------------------
     
       public static boolean addNewAppointment(Appointment appointment, String fileName) {
           List<Object> appointments = readObjectsFromFile(fileName);
           appointments.add(appointment);
           return writeObjectsToFile(appointments, fileName);
       } 
       //-----------------------------------------------
       
       public static boolean write_Objects_ToFile(ObservableList<Attendance> items, String fileName) {
        List<Object> attendanceList = new ArrayList<>(items);
        return writeObjectsToFile(attendanceList, fileName);
    }
       //----------------------------------------------
       
       public static boolean addNewAddi_DutyHour(AdditionalDutyHours information, String fileName) {
           List<Object> information_row = readObjectsFromFile(fileName);
           information_row.add(information);
           return writeObjectsToFile(information_row, fileName);
       } 
       
       //-----------------------------------------------
    public static boolean addNewOutsiderAttendance(NonEmployeeAttendance items, String fileName) {
        List<Object> attendanceList = readObjectsFromFile(fileName);
        attendanceList.add(items);
        return writeObjectsToFile(attendanceList, fileName);
    }
       ///////////////////////read case assignment appointments/////////////////////
       
    public static ArrayList<CaseAssignment> loadCaseAssignmentsFromFile(String fileName) {
        ArrayList<CaseAssignment> caseAssignments = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    CaseAssignment caseAssignment = (CaseAssignment) ois.readObject();
                    caseAssignments.add(caseAssignment);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return caseAssignments;
    }

    public static ObservableList<CaseAssignment> loadCaseAssignmentsFromFiles(String fileName) {
        ArrayList<CaseAssignment> caseAssignments = loadCaseAssignmentsFromFile(fileName);
        ObservableList<CaseAssignment> observableList = FXCollections.observableArrayList(caseAssignments);
        return observableList;
    }
    
    public static void updateFile(ObservableList<CaseAssignment> caseAssignments, String fileName) {
    try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
        for (CaseAssignment assignment : caseAssignments) {
            output.writeObject(assignment);
        }
    } catch (IOException e) {
        e.printStackTrace();
        }
    }
    
    public static void saveSortedCaseAssignment(CaseAssignment caseAssignment, String fileName) {
        ArrayList<CaseAssignment> existingAssignments = loadCaseAssignmentsFromFile(fileName);
        existingAssignments.add(caseAssignment);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (CaseAssignment assignment : existingAssignments) {
                oos.writeObject(assignment);
            }
            System.out.println("Case assignment saved: " + caseAssignment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
