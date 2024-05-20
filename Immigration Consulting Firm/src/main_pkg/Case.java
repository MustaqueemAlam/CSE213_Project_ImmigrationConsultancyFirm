
package main_pkg;

import java.io.Serializable;

public class Case implements Serializable{
    private String name;
    private int phonenumber;
    private String details;
    private String clientType;
    private String gender;

    public Case(String name, int phonenumber, String details, String clientType, String gender) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.details = details;
        this.clientType = clientType;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public String getDetails() {
        return details;
    }

    public String getClientType() {
        return clientType;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   
    
}
