package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.business.notification.SmsFixedNotificationSender;
import org.kodluyoruz.trendyol.datastructure.abstraction.SmsPackage;

public class SmsFixedPackage extends SmsPackage {
    public int limitExcessExtraLimit;
    public double limitExcessPackagePrice;

    public SmsFixedPackage() {
        super.notificationSender = new SmsFixedNotificationSender();
        super.limit = 3;
        super.packagePrice = 20;
        limitExcessExtraLimit = 2;
        limitExcessPackagePrice = 20;
    }
}
