package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.business.notification.SmsElasticNotificationSender;
import org.kodluyoruz.trendyol.datastructure.abstraction.ElasticPackage;

public class SmsElasticPackage extends ElasticPackage {
    public SmsElasticPackage() {
        super.notificationSender = new SmsElasticNotificationSender();
        super.limit = 2;
        super.packagePrice = 30;
        super.limitExcessUnitPrice = 0.10;
    }
}
