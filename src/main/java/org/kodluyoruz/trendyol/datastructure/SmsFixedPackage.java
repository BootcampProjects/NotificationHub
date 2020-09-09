package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.business.notification.SmsFixedNotificationSender;
import org.kodluyoruz.trendyol.datastructure.abstraction.FixedPackage;

public class SmsFixedPackage extends FixedPackage {
    public SmsFixedPackage() {
        super.notificationSender = new SmsFixedNotificationSender();
        super.limit = 3;
        super.packagePrice = 20;
        super.limitExcessExtraLimit = 2;
        super.limitExcessPackagePrice = 20;
    }
}
