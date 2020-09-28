package org.kodluyoruz.trendyol.models;

import org.kodluyoruz.trendyol.business.validation.PaymentValidation;
import org.kodluyoruz.trendyol.constants.ErrorMessage;
import org.kodluyoruz.trendyol.datastructures.abstraction.EmailPackage;
import org.kodluyoruz.trendyol.datastructures.abstraction.NotificationPackage;
import org.kodluyoruz.trendyol.datastructures.abstraction.SmsPackage;
import org.kodluyoruz.trendyol.exceptions.InvalidPaymentException;
import org.kodluyoruz.trendyol.models.dtos.NotificationSendDTO;

import java.util.Date;
import java.util.Random;

public class Company {
    private int id;
    private String name;
    private String language; // TR - EN
    private double invoice;
    private Date createdDate;
    private Date lastPaidInvoiceDate;
    private SmsPackage smsPackage;
    private EmailPackage emailPackage;

    public Company(String name, String language, SmsPackage smsPackage) {
        createCompany(name, language, smsPackage);
        this.smsPackage = smsPackage;
    }

    public Company(String name, String language, EmailPackage emailPackage) {
        createCompany(name, language, emailPackage);
        this.emailPackage = emailPackage;
    }

    public Company(String name, String language, SmsPackage smsPackage, EmailPackage emailPackage) {
        createCompany(name, language, smsPackage, emailPackage);
        this.smsPackage = smsPackage;
        this.emailPackage = emailPackage;
    }

    private void createCompany(String name, String language, NotificationPackage... notificationPackages) {
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getInvoice() {
        return invoice;
    }

    public void setInvoice(double invoice) {
        this.invoice = invoice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastPaidInvoiceDate() {
        return lastPaidInvoiceDate;
    }

    public void setLastPaidInvoiceDate(Date lastPaidInvoiceDate) {
        this.lastPaidInvoiceDate = lastPaidInvoiceDate;
    }

    public NotificationPackage getSmsPackage() {
        return smsPackage;
    }

    public void setSmsPackage(SmsPackage smsPackage) {
        this.smsPackage = smsPackage;
    }

    public NotificationPackage getEmailPackage() {
        return emailPackage;
    }

    public void setEmailPackage(EmailPackage emailPackage) {
        this.emailPackage = emailPackage;
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
