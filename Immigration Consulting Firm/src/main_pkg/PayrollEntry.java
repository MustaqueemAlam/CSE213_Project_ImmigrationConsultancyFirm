/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main_pkg;


import java.io.Serializable;

public class PayrollEntry implements Serializable {

    private int serial;
    private String employeeName;
    private String designation;
    private double regularHours;
    private double overtimeHours;
    private double doubleTimeHours;
    private double amount;
    private double grossPay;

    public PayrollEntry(int serial, String employeeName, String designation, double regularHours,
                        double overtimeHours, double doubleTimeHours, double amount) {
        this.serial = serial;
        this.employeeName = employeeName;
        this.designation = designation;
        this.regularHours = regularHours;
        this.overtimeHours = overtimeHours;
        this.doubleTimeHours = doubleTimeHours;
        this.amount = amount;
        this.grossPay = calculateGrossPay();
    }

    private double calculateGrossPay() {
        return regularHours * 10 + overtimeHours * 15 + doubleTimeHours * 20 + amount;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(double regularHours) {
        this.regularHours = regularHours;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getDoubleTimeHours() {
        return doubleTimeHours;
    }

    public void setDoubleTimeHours(double doubleTimeHours) {
        this.doubleTimeHours = doubleTimeHours;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    @Override
    public String toString() {
        return "PayrollEntry{" + "serial=" + serial + ", employeeName=" + employeeName + ", designation=" + designation + ", regularHours=" + regularHours + ", overtimeHours=" + overtimeHours + ", doubleTimeHours=" + doubleTimeHours + ", amount=" + amount + ", grossPay=" + grossPay + '}';
    }
}
