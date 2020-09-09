package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.business.notification.EmailFixedNotificationSender;
import org.kodluyoruz.trendyol.datastructure.abstraction.EmailPackage;

public class EmailFixedPackage extends EmailPackage {
    public int limitExcessExtraLimit;
    public double limitExcessPackagePrice;

    public EmailFixedPackage() {
        super.notificationSender = new EmailFixedNotificationSender();
        super.limit = 1;
        super.packagePrice = 10;
        limitExcessExtraLimit = 2;
        limitExcessPackagePrice = 10;
    }
}