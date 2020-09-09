package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.FixedNotificationSender;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Message;

public class EmailFixedNotificationSender implements FixedNotificationSender {
    @Override
    public void SendNotification(Company company, Message message) {

    }

    @Override
    public void DefineExtraPackage(Company company) {

    }
}
