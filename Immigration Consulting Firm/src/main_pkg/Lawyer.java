
package main_pkg;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lawyer implements Serializable {
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
           
           
    public static void saveCaseAssignment(CaseAssignment caseAssignment, String fileName) {
        ArrayList<CaseAssignment> existingAssignments = Receptionist.loadCaseAssignmentsFromFile(fileName);
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
    
    //////////////////////caseTypeChart////////////////////////////////
     public static Map<String, Double> calculateCaseTypeRatio(ArrayList<CaseAssignment> caseAssignments) {
        Map<String, Double> caseTypeRatioMap = new HashMap<>();
        int totalCases = caseAssignments.size();

        for (String caseType : getUniqueCaseTypes(caseAssignments)) {
            long count = caseAssignments.stream()
                    .filter(ca -> ca.getCaseType().equals(caseType))
                    .count();
            double ratio = (double) count / totalCases * 100;
            caseTypeRatioMap.put(caseType, ratio);
        }

        return caseTypeRatioMap;
    }

    private static ArrayList<String> getUniqueCaseTypes(ArrayList<CaseAssignment> caseAssignments) {
        ArrayList<String> uniqueCaseTypes = new ArrayList<>();
        for (CaseAssignment assignment : caseAssignments) {
            if (!uniqueCaseTypes.contains(assignment.getCaseType())) {
                uniqueCaseTypes.add(assignment.getCaseType());
            }
        }
        return uniqueCaseTypes;
    }


//////////////send termsand policy to doc manager//////////////////////
    public static void writeToFile(ArrayList<String> data, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> data = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            data = (ArrayList<String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
    
   
}