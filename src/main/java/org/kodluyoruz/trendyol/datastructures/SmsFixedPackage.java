package org.kodluyoruz.trendyol.datastructures;

import org.kodluyoruz.trendyol.business.notification.SmsFixedNotificationSender;
import org.kodluyoruz.trendyol.datastructures.abstraction.SmsPackage;

public class SmsFixedPackage extends SmsPackage {
    public int limitExcessExtraLimit;
    public double limitExcessPackagePrice;

    public SmsFixedPackage() {
        super.notificationSender = new SmsFixedNotificationSender();
        super.limit = 1000;
        super.packagePrice = 20;
        limitExcessExtraLimit = 1000;
        limitExcessPackagePrice = 20;
    }
}
