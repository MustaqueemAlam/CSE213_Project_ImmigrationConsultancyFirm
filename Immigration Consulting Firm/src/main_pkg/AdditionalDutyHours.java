package main_pkg;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author mdsha
 */
public class AdditionalDutyHours implements Serializable {
    private String designation;
    private String name;
    private float hour;
    private LocalDate date;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHour() {
        return hour;
    }

    public void setHour(float hour) {
        this.hour = hour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AdditionalDutyHours(String designation, String name, float hour, LocalDate date) {
        this.designation = designation;
        this.name = name;
        this.hour = hour;
        this.date = date;
    }

    @Override
    public String toString() {
        return "AdditionalDutyHours{" + "designation=" + designation + ", name=" + name + ", hour=" + hour + ", date=" + date + '}';
    }

  
}
