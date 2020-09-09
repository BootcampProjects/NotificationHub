package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.ElasticNotificationSender;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Message;

public class EmailElasticNotificationSender implements ElasticNotificationSender {
    @Override
    public void SendNotification(Company company, Message message) {

    }

    @Override
    public void addUnitPriceToInvoice(Company company) {

    }
}
