package com.jobApplication.generateMail.Model;


public class MailData {
    private String receiverName;
    private String receiverEmail;
    private String receiverCompany;
    private String subject;
    private String body;

    public MailData() {
    }

    public MailData(String receiverEmail, String receiverName, String receiverCompany, String subject, String body) {
        this.receiverEmail = receiverEmail;
        this.receiverName = receiverName;
        this.receiverCompany = receiverCompany;
        this.subject = subject;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReceiverCompany() {
        return receiverCompany;
    }

    public void setReceiverCompany(String receiverCompany) {
        this.receiverCompany = receiverCompany;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
