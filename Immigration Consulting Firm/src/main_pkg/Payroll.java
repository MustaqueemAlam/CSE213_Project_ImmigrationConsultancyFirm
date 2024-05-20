package main_pkg;

import java.io.Serializable;
import java.time.LocalDate;

public class Payroll implements Serializable {
    private String employeeName;
    private double basicSalary;
    private int overtimeHours;
    private double deductions;
    private LocalDate paymentDate;
    private String designation;

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Payroll{" + "employeeName=" + employeeName + ", basicSalary=" + basicSalary + ", overtimeHours=" + overtimeHours + ", deductions=" + deductions + ", paymentDate=" + paymentDate + ", designation=" + designation + '}';
    }

    public Payroll(String employeeName, double basicSalary, int overtimeHours, double deductions,
                   LocalDate paymentDate, String designation) {
        this.employeeName = employeeName;
        this.basicSalary = basicSalary;
        this.overtimeHours = overtimeHours;
        this.deductions = deductions;
        this.paymentDate = paymentDate;
        this.designation = designation;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public double getDeductions() {
        return deductions;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getDesignation() {
        return designation;
    }
    private static final long serialVersionUID = -4574020008366545983L;
    
    private static final double OVERTIME_RATE_12_HOURS = 0.10; // 10% of basic salary
    private static final double OVERTIME_RATE_24_HOURS = 0.15; // 15% of basic salary
    private static final double OVERTIME_RATE_48_HOURS = 0.20; // 20% of basic salary
    private static final double OVERTIME_RATE_72_HOURS = 0.25; // 25% of basic salary
    private static final double OVERTIME_RATE_96_HOURS = 0.30; // 30% of basic salary
    private static final double OVERTIME_RATE_112_HOURS = 0.35; // 35% of basic salary
    public static int calculateTotalPayablePayroll(int basicSalary, int overtimeHours, int deductions) {
        double overtimeRate = getOvertimeRateForPayroll(overtimeHours);
        int totalPayable = (int) (basicSalary + (basicSalary * overtimeRate) - deductions);
        return totalPayable;
    }
    private static double getOvertimeRateForPayroll(int overtimeHours) 
    {
        if (overtimeHours >= 112) {
            return OVERTIME_RATE_112_HOURS;
        } else if (overtimeHours >= 96) {
            return OVERTIME_RATE_96_HOURS;
        } else if (overtimeHours >= 72) {
            return OVERTIME_RATE_72_HOURS;
        } else if (overtimeHours >= 48) {
            return OVERTIME_RATE_48_HOURS;
        } else if (overtimeHours >= 24) {
            return OVERTIME_RATE_24_HOURS;
        } else if (overtimeHours >= 12) {
            return OVERTIME_RATE_12_HOURS;
        } else {
            return 0.0;
        }
    }
}
