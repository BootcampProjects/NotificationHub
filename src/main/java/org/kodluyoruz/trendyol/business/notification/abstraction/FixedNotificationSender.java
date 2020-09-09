package org.kodluyoruz.trendyol.business.notification.abstraction;

import org.kodluyoruz.trendyol.model.Company;

public interface FixedNotificationSender extends NotificationSender {
    void DefineExtraPackage(Company company);
}
