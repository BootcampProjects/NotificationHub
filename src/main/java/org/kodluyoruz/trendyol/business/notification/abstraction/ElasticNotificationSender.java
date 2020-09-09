package org.kodluyoruz.trendyol.business.notification.abstraction;

import org.kodluyoruz.trendyol.model.Company;

public interface ElasticNotificationSender extends NotificationSender {
    void addUnitPriceToInvoice(Company company);
}
