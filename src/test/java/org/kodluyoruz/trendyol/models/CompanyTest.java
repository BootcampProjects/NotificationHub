package org.kodluyoruz.trendyol.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kodluyoruz.trendyol.datastructures.EmailElasticPackage;
import org.kodluyoruz.trendyol.datastructures.EmailFixedPackage;
import org.kodluyoruz.trendyol.datastructures.SmsElasticPackage;
import org.kodluyoruz.trendyol.datastructures.SmsFixedPackage;
import org.kodluyoruz.trendyol.exceptions.InvalidPaymentException;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CompanyTest {

    static Date threeMonthsAgo;
    static BlackList blackList;

    @BeforeAll
    public static void setUpAll() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -3);
        threeMonthsAgo = cal.getTime();

        blackList = BlackList.getInstance();
    }

    @Test
    public void createCompany_WhenSelectSmsPackage_ShouldCreateCompany() {
        // Arrange
        Company sut;
        SmsElasticPackage smsElasticPackage = new SmsElasticPackage();

        // Act
        sut = new Company("CompTest", 0, smsElasticPackage);

        // Assert
        assertThat(sut.getId()).isGreaterThan(0).isLessThan(99999);
        assertThat(sut.getInvoice()).isEqualTo(30.0);
        assertThat(sut.getSmsPackage().limit).isEqualTo(2000);
    }

    @Test
    public void createCompany_WhenSelectEmailPackage_ShouldCreateCompany() {
        // Arrange
        Company sut;
        EmailFixedPackage emailFixedPackage = new EmailFixedPackage();

        // Act
        sut = new Company("CompTest", 0, emailFixedPackage);

        // Assert
        assertThat(sut.getId()).isGreaterThan(0).isLessThan(99999);
        assertThat(sut.getInvoice()).isEqualTo(10.0);
        assertThat(sut.getEmailPackage().limit).isEqualTo(1000);
    }

    @Test
    public void createCompany_WhenSelectSmsAndEmailPackage_ShouldCreateCompany() {
        // Arrange
        Company sut;
        SmsFixedPackage smsFixedPackage = new SmsFixedPackage();
        EmailElasticPackage emailElasticPackage = new EmailElasticPackage();

        // Act
        sut = new Company("CompTest", 0, smsFixedPackage, emailElasticPackage);

        // Assert
        assertThat(sut.getId()).isGreaterThan(0).isLessThan(99999);
        assertThat(sut.getInvoice()).isEqualTo(27.5);
        assertThat(sut.getSmsPackage().limit).isEqualTo(1000);
        assertThat(sut.getEmailPackage().limit).isEqualTo(2000);
    }

    @Test
    public void sendSms_WhenSentGroupSms_ShouldDecreaseSmsLimit() {
        // Arrange
        Company sut = new Company("CompTest", 0, new SmsFixedPackage());
        Sms sms = new Sms("Hello");
        PostGroup postGroup = new PostGroup();
        postGroup.addUsers(new User("Test1"), new User("Test2"), new User("Test3"));

        // Act
        sut.sendSms(sms, postGroup);

        // Assert
        assertThat(sut.getSmsPackage().limit).isEqualTo(997);
    }

    @Test
    public void sendSms_WhenInvalidPayment_ShouldThrowInvalidPaymentException() {
        // Arrange
        Company sut = new Company("CompTest", 2, new SmsFixedPackage());
        Sms sms = new Sms("Hello");
        PostGroup postGroup = new PostGroup();
        postGroup.addUser(new User("Test1"));

        // Act
        sut.setCreatedDate(threeMonthsAgo);
        sut.setLastPaidInvoiceDate(threeMonthsAgo);

        Throwable throwable = catchThrowable(() -> sut.sendSms(sms, postGroup));

        // Assert
        assertThat(sut.getSmsPackage().limit).isEqualTo(1000);
        assertThat(throwable).isInstanceOf(InvalidPaymentException.class).hasMessage("Invalid Payment Error");
        assertThat(blackList.getBlackListCompanies().get(0).getCompanyId()).isEqualTo(sut.getId());
    }

    @Test
    public void sendEmail_WhenSentGroupEmail_ShouldDecreaseEmailLimit() {
        // Arrange
        Company sut = new Company("CompTest", 0, new EmailElasticPackage());
        Email email = new Email("Hello", "Greeting");
        PostGroup postGroup = new PostGroup();
        postGroup.addUsers(new User("Test1"), new User("Test2"), new User("Test3"), new User("Test4"));

        // Act
        sut.sendEmail(email, postGroup);

        // Assert
        assertThat(sut.getEmailPackage().limit).isEqualTo(1996);
    }

    @Test
    public void sendEmail_WhenInvalidPayment_ShouldThrowInvalidPaymentException() {
        // Arrange
        Company sut = new Company("CompTest", 1, new EmailElasticPackage());
        Email email = new Email("Hello", "Greeting");
        PostGroup postGroup = new PostGroup();
        postGroup.addUser(new User("Test1"));

        // Act
        sut.setCreatedDate(threeMonthsAgo);
        sut.setLastPaidInvoiceDate(threeMonthsAgo);

        Throwable throwable = catchThrowable(() -> sut.sendEmail(email, postGroup));

        // Assert
        assertThat(sut.getEmailPackage().limit).isEqualTo(2000);
        assertThat(throwable).isInstanceOf(InvalidPaymentException.class).hasMessage("Geçersiz Ödeme Hatası");
        assertThat(blackList.getBlackListCompanies().get(1).getCompanyId()).isEqualTo(sut.getId());
    }
}