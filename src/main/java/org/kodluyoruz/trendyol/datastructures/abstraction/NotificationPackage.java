package org.kodluyoruz.trendyol.datastructures.abstraction;

import org.kodluyoruz.trendyol.business.notification.abstraction.NotificationSender;

import java.util.Calendar;
import java.util.Date;

public abstract class NotificationPackage {
    public NotificationSender notificationSender;
    public int limit;
    public double packagePrice;
    public Date validityDate;

    public NotificationPackage() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        validityDate = cal.getTime();
    }
}
