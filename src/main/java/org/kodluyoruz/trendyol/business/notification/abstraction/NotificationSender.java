package org.kodluyoruz.trendyol.business.notification.abstraction;

import org.kodluyoruz.trendyol.model.dto.NotificationSendDTO;

public interface NotificationSender {
    void SendNotification(NotificationSendDTO notificationSendDTO);
}
