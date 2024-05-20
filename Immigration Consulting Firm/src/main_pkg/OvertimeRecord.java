package main_pkg;

import java.io.Serializable;


public class OvertimeRecord implements Serializable {
    
    private final String designation;
    private final int overtimeHours;
    
    public OvertimeRecord(String designation, int overtimeHours) {
        this.designation = designation;
        this.overtimeHours = overtimeHours;
    }

    public String getDesignation() {
        return designation;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    @Override
    public String toString() {
        return "OvertimeRecord{" + "designation=" + designation + ", overtimeHours=" + overtimeHours + '}';
    }
    
}
