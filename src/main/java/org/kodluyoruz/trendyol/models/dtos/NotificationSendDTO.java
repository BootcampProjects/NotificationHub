package org.kodluyoruz.trendyol.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kodluyoruz.trendyol.models.Company;
import org.kodluyoruz.trendyol.models.Message;

@Getter
@Setter
@NoArgsConstructor
public class NotificationSendDTO {
    private Company company;
    private Message message;
    private String userName;
}
