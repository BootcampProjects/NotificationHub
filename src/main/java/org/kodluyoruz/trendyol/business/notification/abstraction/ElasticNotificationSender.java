package org.kodluyoruz.trendyol.business.notification.abstraction;

import org.kodluyoruz.trendyol.models.Company;

public interface ElasticNotificationSender extends NotificationSender {
    void addUnitPriceToInvoice(Company company);
}
