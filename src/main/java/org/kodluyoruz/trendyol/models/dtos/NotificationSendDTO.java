package org.kodluyoruz.trendyol.models.dtos;

import org.kodluyoruz.trendyol.models.Company;
import org.kodluyoruz.trendyol.models.Message;

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
