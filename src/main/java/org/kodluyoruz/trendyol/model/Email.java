package org.kodluyoruz.trendyol.model;

public class Email extends Message {
    private String subject;

    public Email(String content, String subject) {
        super(content);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
