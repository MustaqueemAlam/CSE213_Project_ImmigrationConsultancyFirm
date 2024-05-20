/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main_pkg;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class InvoiceReport implements Serializable {
    private String invoiceNumber;
    private Float totalAmount;
    private String clientName;
    private String paymentStatus;
    private LocalDate date;
    private int countDueDateOver;
    private int countDueDate;

    public int getCountDueDateOver() {
        return countDueDateOver;
    }

    public void setCountDueDateOver(int countDueDateOver) {
        this.countDueDateOver = countDueDateOver;
    }

    public int getCountDueDate() {
        return countDueDate;
    }

    public void setCountDueDate(int countDueDate) {
        this.countDueDate = countDueDate;
    }

    public InvoiceReport(String invoiceNumber, Float totalAmount, String clientName, String paymentStatus, LocalDate date) {
        this.invoiceNumber = invoiceNumber;
        this.totalAmount = totalAmount;
        this.clientName = clientName;
        this.paymentStatus = paymentStatus;
        this.date = date;
    }

    @Override
    public String toString() {
        return "InvoiceReport{" + "invoiceNumber=" + invoiceNumber + ", totalAmount=" + totalAmount + ", clientName=" + clientName + ", paymentStatus=" + paymentStatus + ", date=" + date + '}';
    }

   

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    private boolean isInvoiceNumberUnique(String invoiceNumber) {
        List<InvoiceReport> existingReports = DocumentManager.LoadInvoiceReports("InvoicesReport.bin");

        for (InvoiceReport report : existingReports) {
            if (report.getInvoiceNumber().equals(invoiceNumber)) {
                return false;
            }
        }
        return true;
    }
}