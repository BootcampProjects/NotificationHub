package org.kodluyoruz.trendyol.business.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodluyoruz.trendyol.datastructures.SmsElasticPackage;
import org.kodluyoruz.trendyol.exceptions.InvalidMessageContentException;
import org.kodluyoruz.trendyol.models.Company;
import org.kodluyoruz.trendyol.models.Sms;
import org.kodluyoruz.trendyol.models.dtos.NotificationSendDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class SmsElasticNotificationSenderTest {

    SmsElasticNotificationSender sut = new SmsElasticNotificationSender();
    NotificationSendDTO notificationSendDTO;
    Company company;
    Sms sms;

    @BeforeEach
    public void setUp() {
        company = new Company("Comp1", 0, new SmsElasticPackage());

        sms = new Sms("Hello");

        notificationSendDTO = new NotificationSendDTO();
        notificationSendDTO.setCompany(company);
        notificationSendDTO.setMessage(sms);
        notificationSendDTO.setUserName("userTest");
    }

    @Test
    public void sendNotification_WhenSentSms_ShouldDecreaseSmsLimit() {
        // Arrange
        // defined at beforeEach

        // Act
        sut.sendNotification(notificationSendDTO);

        // Assert
        assertThat(company.getSmsPackage().limit).isEqualTo(1999);
    }

    @Test
    public void sendNotification_WhenSmsLimitExceeded_ShouldAddUnitPriceToInvoice() {
        // Arrange
        // defined at beforeEach
        company.getSmsPackage().limit = 0;

        // Act
        sut.sendNotification(notificationSendDTO);

        // Assert
        assertThat(company.getSmsPackage().limit).isEqualTo(0);
        assertThat(company.getInvoice()).isEqualTo(30.10);
    }

    @Test
    public void sendNotification_WhenSmsLimitExceeded_ShouldAddUnitPriceToInvoiceForEachSms() {
        // Arrange
        // defined at beforeEach
        company.getSmsPackage().limit = 0;

        // Act
        sut.sendNotification(notificationSendDTO);
        sut.sendNotification(notificationSendDTO);
        sut.sendNotification(notificationSendDTO);
        sut.sendNotification(notificationSendDTO);

        // Assert
        assertThat(company.getSmsPackage().limit).isEqualTo(0);
        assertThat(company.getInvoice()).isEqualTo(30.400000000000006);
    }

    @Test
    public void sendNotification_WhenInvalidSms_ShouldThrowInvalidMessageContentException() {
        // Arrange
        // defined at beforeEach
        sms = new Sms("Hello' A");
        notificationSendDTO.setMessage(sms);

        // Act
        Throwable throwable = catchThrowable(() -> sut.sendNotification(notificationSendDTO));

        // Assert
        assertThat(throwable).isInstanceOf(InvalidMessageContentException.class).hasMessage("Invalid Message Content");
    }

    @Test
    public void addUnitPriceToInvoice_TrueStory() {
        // Arrange
        // defined at beforeEach

        // Act
        sut.addUnitPriceToInvoice(company);

        // Assert
        assertThat(company.getInvoice()).isEqualTo(30.10);
    }
}
