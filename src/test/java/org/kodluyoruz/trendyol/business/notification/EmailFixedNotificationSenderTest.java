package org.kodluyoruz.trendyol.business.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodluyoruz.trendyol.datastructures.EmailFixedPackage;
import org.kodluyoruz.trendyol.exceptions.InvalidMessageContentException;
import org.kodluyoruz.trendyol.models.Company;
import org.kodluyoruz.trendyol.models.Email;
import org.kodluyoruz.trendyol.models.dtos.NotificationSendDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class EmailFixedNotificationSenderTest {

    EmailFixedNotificationSender sut = new EmailFixedNotificationSender();
    NotificationSendDTO notificationSendDTO;
    Company company;
    Email email;

    @BeforeEach
    public void setUp() {
        company = new Company("Comp1", 0, new EmailFixedPackage());

        email = new Email("Hello", "Greeting");

        notificationSendDTO = new NotificationSendDTO();
        notificationSendDTO.setCompany(company);
        notificationSendDTO.setMessage(email);
        notificationSendDTO.setUserName("userTest");
    }

    @Test
    public void sendNotification_WhenSentEmail_ShouldDecreaseEmailLimit() {
        // Arrange
        // defined at beforeEach

        // Act
        sut.sendNotification(notificationSendDTO);

        // Assert
        assertThat(company.getEmailPackage().limit).isEqualTo(999);
    }

    @Test
    public void sendNotification_WhenEmailLimitExceeded_ShouldDefineExtraPackage() {
        // Arrange
        // defined at beforeEach
        company.getEmailPackage().limit = 0;

        // Act
        sut.sendNotification(notificationSendDTO);

        // Assert
        assertThat(company.getEmailPackage().limit).isEqualTo(999);
        assertThat(company.getInvoice()).isEqualTo(20.00);
    }

    @Test
    public void sendNotification_WhenEmailLimitExceeded_ShouldDefineExtraPackageForEachLimitExcess() {
        // Arrange
        // defined at beforeEach
        company.getEmailPackage().limit = 0;

        // Act
        sut.sendNotification(notificationSendDTO);

        company.getEmailPackage().limit = 0;

        sut.sendNotification(notificationSendDTO);
        sut.sendNotification(notificationSendDTO);
        sut.sendNotification(notificationSendDTO);

        // Assert
        assertThat(company.getEmailPackage().limit).isEqualTo(997);
        assertThat(company.getInvoice()).isEqualTo(30.00);
    }

    @Test
    public void sendNotification_WhenInvalidEmail_ShouldThrowInvalidMessageContentException() {
        // Arrange
        // defined at beforeEach
        email = new Email("H", "InvalidContentTest");
        notificationSendDTO.setMessage(email);

        // Act
        Throwable throwable = catchThrowable(() -> sut.sendNotification(notificationSendDTO));

        // Assert
        assertThat(throwable).isInstanceOf(InvalidMessageContentException.class).hasMessage("Invalid Message Content");
    }

    @Test
    public void defineExtraPackage_TrueStory() {
        // Arrange
        // defined at beforeEach

        // Act
        sut.defineExtraPackage(company);

        // Assert
        assertThat(company.getEmailPackage().limit).isEqualTo(1000);
        assertThat(company.getInvoice()).isEqualTo(20.00);
    }
}
