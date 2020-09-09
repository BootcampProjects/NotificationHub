package org.kodluyoruz.trendyol.model.dto;

import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Message;

public class NotificationSendDTO {
    private Company company;
    private Message message;
    private String userName;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
