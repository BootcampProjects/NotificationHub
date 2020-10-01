package org.kodluyoruz.trendyol.business.notification.abstraction;

import org.kodluyoruz.trendyol.models.dtos.NotificationSendDTO;

public interface NotificationSender {
    void sendNotification(NotificationSendDTO notificationSendDTO);
}
