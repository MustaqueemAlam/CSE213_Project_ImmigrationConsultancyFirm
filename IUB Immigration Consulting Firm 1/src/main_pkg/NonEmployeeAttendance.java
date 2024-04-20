package main_pkg;

import java.io.Serializable;
import java.time.LocalDate;


public class NonEmployeeAttendance implements Serializable{
    private String name;
    private String phoneNumber;
    private String profession;
    private String reason;
    private LocalDate date;
    private String checkInTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public NonEmployeeAttendance(String name, String phoneNumber, String profession, String reason, LocalDate date, String checkInTime) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
        this.reason = reason;
        this.date = date;
        this.checkInTime = checkInTime;
    }

    @Override
    public String toString() {
        return "NonEmployeeAttendance{" + "name=" + name + ", phoneNumber=" + phoneNumber + ", profession=" + profession + ", reason=" + reason + ", date=" + date + ", checkInTime=" + checkInTime + '}';
    }

    public String generateDisplayText() {
        StringBuilder displayText = new StringBuilder();
        displayText.append("Name: ").append(name).append("\n");
        displayText.append("Contact Info: ").append(phoneNumber).append("\n");
        displayText.append("Profession: ").append(profession).append("\n");
        displayText.append("Reason: ").append(reason).append("\n");
        displayText.append("Date: ").append(date).append("\n");
        displayText.append("Check In Time: ").append(checkInTime).append("\n");
        return displayText.toString();
    }
}
