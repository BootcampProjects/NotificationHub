package org.kodluyoruz.trendyol.models;

import lombok.Getter;
import lombok.Setter;
import org.kodluyoruz.trendyol.business.validation.PaymentValidation;
import org.kodluyoruz.trendyol.constants.ErrorMessage;
import org.kodluyoruz.trendyol.datastructures.abstraction.EmailPackage;
import org.kodluyoruz.trendyol.datastructures.abstraction.NotificationPackage;
import org.kodluyoruz.trendyol.datastructures.abstraction.SmsPackage;
import org.kodluyoruz.trendyol.exceptions.InvalidPaymentException;
import org.kodluyoruz.trendyol.models.dtos.NotificationSendDTO;

import java.util.Date;
import java.util.Random;

@Getter
@Setter
public class Company {
    private int id;
    private String name;
    private int language; // 0 -> default / 1 -> TR / 2 -> EN
    private double invoice;
    private Date createdDate;
    private Date lastPaidInvoiceDate;
    private SmsPackage smsPackage;
    private EmailPackage emailPackage;

    public Company(String name, int language, SmsPackage smsPackage) {
        createCompany(name, language, smsPackage);
        this.smsPackage = smsPackage;
    }

    public Company(String name, int language, EmailPackage emailPackage) {
        createCompany(name, language, emailPackage);
        this.emailPackage = emailPackage;
    }

    public Company(String name, int language, SmsPackage smsPackage, EmailPackage emailPackage) {
        createCompany(name, language, smsPackage, emailPackage);
        this.smsPackage = smsPackage;
        this.emailPackage = emailPackage;
    }

    private void createCompany(String name, int language, NotificationPackage... notificationPackages) {
        this.id = new Random().nextInt(99999);
        this.name = name;
        this.language = language;
        this.createdDate = new Date();
        this.lastPaidInvoiceDate = new Date();
        this.invoice = 0;

        for (NotificationPackage notificationPackage : notificationPackages) {
            this.invoice += notificationPackage.packagePrice;
        }

        System.out.println(this.name + " company id : " + this.id);
        System.out.printf(this.name + " - invoice : %.2f \n\n", this.invoice);
    }

    public void SendSms(Sms sms, PostGroup postGroup) {
        BeforeCheckPayment(this);
        NotificationSendDTO notificationSendDTO = new NotificationSendDTO();

        for (User user : postGroup.getUsers()) {
            notificationSendDTO.setCompany(this);
            notificationSendDTO.setMessage(sms);
            notificationSendDTO.setUserName(user.getName());

            smsPackage.notificationSender.SendNotification(notificationSendDTO);
        }

    }

    public void SendEmail(Email email, PostGroup postGroup) {
        BeforeCheckPayment(this);
        NotificationSendDTO notificationSendDTO = new NotificationSendDTO();

        for (User user : postGroup.getUsers()) {
            notificationSendDTO.setCompany(this);
            notificationSendDTO.setMessage(email);
            notificationSendDTO.setUserName(user.getName());

            emailPackage.notificationSender.SendNotification(notificationSendDTO);
        }
    }

    private void BeforeCheckPayment(Company company) {
        boolean validPayment = PaymentValidation.CheckLastPaidInvoiceDate(company);

        if (!validPayment)
            throw new InvalidPaymentException(ErrorMessage.InvalidPayment(company.language));
    }
}
