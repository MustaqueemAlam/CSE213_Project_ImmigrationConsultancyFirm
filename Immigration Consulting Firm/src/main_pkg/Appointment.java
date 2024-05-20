package main_pkg;

import java.io.Serializable;
import java.time.LocalDate;

public class Appointment implements Serializable{
    private String name;
    private Integer phoneNumber;
    private LocalDate date;
    private String appoinmentWith;
    private String purpose;

    public Appointment(String name, Integer phoneNumber, LocalDate date, String appoinmentWith, String purpose) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.appoinmentWith = appoinmentWith;
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "Appoinment{" + "name=" + name + ", phoneNumber=" + phoneNumber + ", date=" + date + ", appoinmentWith=" + appoinmentWith + ", purpose=" + purpose + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAppoinmentWith() {
        return appoinmentWith;
    }

    public void setAppoinmentWith(String appoinmentWith) {
        this.appoinmentWith = appoinmentWith;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

}
