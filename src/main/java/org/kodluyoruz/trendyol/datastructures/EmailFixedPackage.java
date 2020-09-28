package org.kodluyoruz.trendyol.datastructures;

import org.kodluyoruz.trendyol.business.notification.EmailFixedNotificationSender;
import org.kodluyoruz.trendyol.datastructures.abstraction.EmailPackage;

public class EmailFixedPackage extends EmailPackage {
    public int limitExcessExtraLimit;
    public double limitExcessPackagePrice;

    public EmailFixedPackage() {
        super.notificationSender = new EmailFixedNotificationSender();
        super.limit = 1000;
        super.packagePrice = 10;
        limitExcessExtraLimit = 1000;
        limitExcessPackagePrice = 10;
    }
}
