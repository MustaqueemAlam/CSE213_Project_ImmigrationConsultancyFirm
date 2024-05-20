
package main_pkg;

public class ConsultantReviewApplication {
    private String countryname;
    private int malenumber;
    private int femalenumber;

    public ConsultantReviewApplication(String countryname, int malenumber, int femalenumber) {
        this.countryname = countryname;
        this.malenumber = malenumber;
        this.femalenumber = femalenumber;
    }

    public String getCountryname() {
        return countryname;
    }

    public int getMalenumber() {
        return malenumber;
    }

    public int getFemalenumber() {
        return femalenumber;
    }
    
}
