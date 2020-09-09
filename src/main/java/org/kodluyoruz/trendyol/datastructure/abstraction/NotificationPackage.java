package org.kodluyoruz.trendyol.datastructure.abstraction;

import org.kodluyoruz.trendyol.business.notification.abstraction.NotificationSender;

public abstract class NotificationPackage {
    public NotificationSender notificationSender;
    public int limit;
    public double packagePrice;
}