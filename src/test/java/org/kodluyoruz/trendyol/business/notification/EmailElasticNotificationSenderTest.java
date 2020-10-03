package org.kodluyoruz.trendyol.business.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodluyoruz.trendyol.datastructures.EmailElasticPackage;
import org.kodluyoruz.trendyol.exceptions.InvalidMessageContentException;
import org.kodluyoruz.trendyol.models.Company;
import org.kodluyoruz.trendyol.models.Email;
import org.kodluyoruz.trendyol.models.dtos.NotificationSendDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class EmailElasticNotificationSenderTest {

    EmailElasticNotificationSender sut = new EmailElasticNotificationSender();
    NotificationSendDTO notificationSendDTO;
    Company company;
    Email email;

    @BeforeEach
    public void setUp() {
        company = new Company("Comp1", 0, new EmailElasticPackage());

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
        assertThat(company.getEmailPackage().limit).isEqualTo(1999);
    }

    @Test
    public void sendNotification_WhenEmailLimitExceeded_ShouldAddUnitPriceToInvoice() {
        // Arrange
        // defined at beforeEach
        company.getEmailPackage().limit = 0;

        // Act
        sut.sendNotification(notificationSendDTO);

        // Assert
        assertThat(company.getEmailPackage().limit).isEqualTo(0);
        assertThat(company.getInvoice()).isEqualTo(7.53);
    }

    @Test
    public void sendNotification_WhenEmailLimitExceeded_ShouldAddUnitPriceToInvoiceForEachEmail() {
        // Arrange
        // defined at beforeEach
        company.getEmailPackage().limit = 0;

        // Act
        sut.sendNotification(notificationSendDTO);
        sut.sendNotification(notificationSendDTO);
        sut.sendNotification(notificationSendDTO);
        sut.sendNotification(notificationSendDTO);

        // Assert
        assertThat(company.getEmailPackage().limit).isEqualTo(0);
        assertThat(company.getInvoice()).isEqualTo(7.620000000000001);
    }

    @Test
    public void sendNotification_WhenInvalidEmail_ShouldThrowInvalidMessageContentException() {
        // Arrange
        // defined at beforeEach
        email = new Email("Hello' A", "InvalidContentTest");
        notificationSendDTO.setMessage(email);

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
        assertThat(company.getInvoice()).isEqualTo(7.53);
    }
}
