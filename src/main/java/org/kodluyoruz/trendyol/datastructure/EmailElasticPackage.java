package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.business.notification.EmailElasticNotificationSender;
import org.kodluyoruz.trendyol.datastructure.abstraction.EmailPackage;

public class EmailElasticPackage extends EmailPackage {
    public double limitExcessUnitPrice;

    public EmailElasticPackage() {
        super.notificationSender = new EmailElasticNotificationSender();
        super.limit = 2000;
        super.packagePrice = 7.5;
        limitExcessUnitPrice = 0.03;
    }
}
