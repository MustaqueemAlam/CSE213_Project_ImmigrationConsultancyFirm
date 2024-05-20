
package main_pkg;

import java.io.Serializable;
import java.time.LocalDate;


public class VisaApplication implements Serializable{
    private String name;
    private String contactInfo;
    private String prefferedCountry;
    private String visaType;
    private LocalDate dateOfApplication;
    private String insurance;
    private String financialproof;
    private String visaapplicationformstatus;
    private String enrollment;

    public void setName(String name) {
        this.name = name;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setPrefferedCountry(String prefferedCountry) {
        this.prefferedCountry = prefferedCountry;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public void setDateOfApplication(LocalDate dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public void setFinancialproof(String financialproof) {
        this.financialproof = financialproof;
    }

    public void setVisaapplicationformstatus(String visaapplicationformstatus) {
        this.visaapplicationformstatus = visaapplicationformstatus;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }
    public VisaApplication(String name, String contactInfo, String prefferedCountry, String visaType, LocalDate dateOfApplication, String insurance, String financialproof, String visaapplicationformstatus, String enrollment) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.prefferedCountry = prefferedCountry;
        this.visaType = visaType;
        this.dateOfApplication = dateOfApplication;
        this.insurance = insurance;
        this.financialproof = financialproof;
        this.visaapplicationformstatus = visaapplicationformstatus;
        this.enrollment = enrollment;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getPrefferedCountry() {
        return prefferedCountry;
    }

    public String getVisaType() {
        return visaType;
    }

    public LocalDate getDateOfApplication() {
        return dateOfApplication;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getFinancialproof() {
        return financialproof;
    }

    public String getVisaapplicationformstatus() {
        return visaapplicationformstatus;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public String generateDisplayText() {
        StringBuilder displayText = new StringBuilder();
        displayText.append("Visa Type: ").append(visaType).append("\n");
        displayText.append("Client Name: ").append(name).append("\n");
        displayText.append("Client Contact Info: ").append(contactInfo).append("\n");
        displayText.append("Date of Applying: ").append(dateOfApplication).append("\n");
        displayText.append("Preferred Country: ").append(prefferedCountry).append("\n");
        displayText.append("Has Insurance: ").append(insurance).append("\n");
        displayText.append("Enrollment Proof Given: ").append(enrollment).append("\n");
        displayText.append("Visa Application Form Received: ").append(visaapplicationformstatus).append("\n");
        displayText.append("Financial Proof Given: ").append(financialproof).append("\n");

        return displayText.toString();
    }
}
