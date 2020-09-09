package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.business.notification.EmailElasticNotificationSender;
import org.kodluyoruz.trendyol.datastructure.abstraction.ElasticPackage;

public class EmailElasticPackage extends ElasticPackage {
    public EmailElasticPackage() {
        super.notificationSender = new EmailElasticNotificationSender();
        super.limit = 2000;
        super.packagePrice = 7.5;
        super.limitExcessUnitPrice = 0.03;
    }
}
