
package main_pkg;

import java.io.Serializable;


public class ConsultancyHour implements Serializable {
    private String consultname;
    private String studentname;
    private String phonenumber;
    private String time;

    public ConsultancyHour(String consultname, String studentname, String phonenumber, String time) {
        this.consultname = consultname;
        this.studentname = studentname;
        this.phonenumber = phonenumber;
        this.time = time;
    }



    public String getConsultname() {
        return consultname;
    }

    public String getStudentname() {
        return studentname;
    }

    public String getTime() {
        return time;
    }
   
}
