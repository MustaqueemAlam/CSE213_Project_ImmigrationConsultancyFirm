
package main_pkg;

import java.io.Serializable;


public class UniversityList implements Serializable{
    private String Universityname;
    private String countryname;
    private Float IELTS;
    private Integer GRE;
    private Float Acceptancerate; 

    public UniversityList(String Universityname, String countryname, Float IELTS, Integer GRE, Float Acceptancerate) {
        this.Universityname = Universityname;
        this.countryname = countryname;
        this.IELTS = IELTS;
        this.GRE = GRE;
        this.Acceptancerate = Acceptancerate;
    }

    public String getUniversityname() {
        return Universityname;
    }

    public String getCountryname() {
        return countryname;
    }

    public Float getIELTS() {
        return IELTS;
    }

    public Integer getGRE() {
        return GRE;
    }

    public Float getAcceptancerate() {
        return Acceptancerate;
    }

    
    
}
