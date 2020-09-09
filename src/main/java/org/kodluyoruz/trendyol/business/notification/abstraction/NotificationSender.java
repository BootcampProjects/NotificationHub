package org.kodluyoruz.trendyol.business.notification.abstraction;

import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Message;

public interface NotificationSender {
    void SendNotification(Company company, Message message);
}
