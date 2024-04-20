package main_pkg;

import java.io.Serializable;

public class AuditReport implements Serializable {

    private String reportTitle;
    private String reportDescription;
    private String submissionDateTime;

    @Override
    public String toString() {
        return "AuditReport{" + "reportTitle=" + reportTitle + ", reportDescription=" + reportDescription + ", submissionDateTime=" + submissionDateTime + '}';
    }

    public AuditReport(String reportTitle, String reportDescription, String submissionDateTime) {
        this.reportTitle = reportTitle;
        this.reportDescription = reportDescription;
        this.submissionDateTime = submissionDateTime;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getSubmissionDateTime() {
        return submissionDateTime;
    }

    public void setSubmissionDateTime(String submissionDateTime) {
        this.submissionDateTime = submissionDateTime;
    }

}