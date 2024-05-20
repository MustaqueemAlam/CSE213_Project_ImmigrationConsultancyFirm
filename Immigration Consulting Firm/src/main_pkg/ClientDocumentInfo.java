package main_pkg;

import java.io.File;
import java.io.Serializable;
public class ClientDocumentInfo implements Serializable {


    private  String clientName;
    private  String passportNumber;
    private  String documentType;

    public ClientDocumentInfo(String clientName, String passportNumber, String documentType) {
        this.clientName = clientName;
        this.passportNumber = passportNumber;
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "ClientDocumentInfo{" + "clientName=" + clientName + ", passportNumber=" + passportNumber + ", documentType=" + documentType + '}';
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
   
    private static boolean isValidPDF(File file) {
        return file.getName().toLowerCase().endsWith(".pdf");
    }


}