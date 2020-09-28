package org.kodluyoruz.trendyol.datastructures;

import org.kodluyoruz.trendyol.business.notification.EmailElasticNotificationSender;
import org.kodluyoruz.trendyol.datastructures.abstraction.EmailPackage;

public class EmailElasticPackage extends EmailPackage {
    public double limitExcessUnitPrice;

    public EmailElasticPackage() {
        super.notificationSender = new EmailElasticNotificationSender();
        super.limit = 2000;
        super.packagePrice = 7.5;
        limitExcessUnitPrice = 0.03;
    }
}
