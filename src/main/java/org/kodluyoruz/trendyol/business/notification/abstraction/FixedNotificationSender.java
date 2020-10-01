package org.kodluyoruz.trendyol.business.notification.abstraction;

import org.kodluyoruz.trendyol.models.Company;

public interface FixedNotificationSender extends NotificationSender {
    void defineExtraPackage(Company company);
}
