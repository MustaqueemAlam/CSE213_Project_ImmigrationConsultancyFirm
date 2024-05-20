
package main_pkg;

import java.io.*;
import java.time.LocalDate;

public class CaseAssignment implements Serializable {
    private String clientName;
    private String contactInfo;
    private String caseType;
    private String assignedAttorneyOrParalegal;
    private LocalDate assignmentDate;

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }


    public void setAssignedAttorneyOrParalegal(String assignedAttorneyOrParalegal) {
        this.assignedAttorneyOrParalegal = assignedAttorneyOrParalegal;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
    public CaseAssignment(String clientName, String contactInfo, String caseType,
                           String assignedAttorneyOrParalegal, LocalDate assignmentDate) {
        this.clientName = clientName;
        this.contactInfo = contactInfo;
        this.caseType = caseType;
        this.assignedAttorneyOrParalegal = assignedAttorneyOrParalegal;
        this.assignmentDate = assignmentDate;
    }

    // Getters and setters for all fields

    public String getClientName() {
        return clientName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getCaseType() {
        return caseType;
    }


    public String getAssignedAttorneyOrParalegal() {
        return assignedAttorneyOrParalegal;
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    @Override
    public String toString() {
        return "CaseAssignment{" +
                "clientName='" + clientName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", caseType='" + caseType + '\'' +
                ", assignedAttorneyOrParalegal='" + assignedAttorneyOrParalegal + '\'' +
                ", assignmentDate=" + assignmentDate +
                '}';
    }
}
