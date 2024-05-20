
package main_pkg;

import java.io.Serializable;


public class ClientInformation implements Serializable{

    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname;
    }
    private String name;
    private String phonenumber;
    private String email;
    private String address;
    private String clientType;
    private String gender;
    private String documentname;

    public ClientInformation(String name, String phonenumber, String email, String address, String clientType, String gender, String documentname) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.clientType = clientType;
        this.gender = gender;
        this.documentname = documentname;
    }

    public String getName() {
        return name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getClientType() {
        return clientType;
    }

    public String getGender() {
        return gender;
    }

    public String getDocumentname() {
        return documentname;
    }

    
   
    
}
