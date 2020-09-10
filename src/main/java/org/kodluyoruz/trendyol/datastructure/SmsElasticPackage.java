package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.business.notification.SmsElasticNotificationSender;
import org.kodluyoruz.trendyol.datastructure.abstraction.SmsPackage;

public class SmsElasticPackage extends SmsPackage {
    public double limitExcessUnitPrice;

    public SmsElasticPackage() {
        super.notificationSender = new SmsElasticNotificationSender();
        super.limit = 2000;
        super.packagePrice = 30;
        limitExcessUnitPrice = 0.10;
    }
}
