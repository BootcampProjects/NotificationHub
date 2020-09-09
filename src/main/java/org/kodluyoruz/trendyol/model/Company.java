package org.kodluyoruz.trendyol.model;

import org.kodluyoruz.trendyol.datastructure.abstraction.EmailPackage;
import org.kodluyoruz.trendyol.datastructure.abstraction.NotificationPackage;
import org.kodluyoruz.trendyol.datastructure.abstraction.SmsPackage;

import java.util.Date;
import java.util.Random;

public class Company {
    private int id;
    private String name;
    private double invoice;
    private Date createdDate;
    private SmsPackage smsPackage;
    private EmailPackage emailPackage;

    public Company(String name, SmsPackage smsPackage) {
        createCompany(name, smsPackage);
        this.smsPackage = smsPackage;
    }

    public Company(String name, EmailPackage emailPackage) {
        createCompany(name, emailPackage);
        this.emailPackage = emailPackage;
    }

    public Company(String name, SmsPackage smsPackage, EmailPackage emailPackage) {
        createCompany(name, smsPackage, emailPackage);
        this.smsPackage = smsPackage;
        this.emailPackage = emailPackage;
    }

    private void createCompany(String name, NotificationPackage... notificationPackages) {
        this.id = new Random().nextInt(99999);
        this.name = name;
        this.createdDate = new Date();
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

    public double getInvoice() {
        return invoice;
    }

    public void setInvoice(double invoice) {
        this.invoice = invoice;
    }

    public Date getCreatedDate() {
        return createdDate;
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

    public void SendSms(Sms sms) {
        smsPackage.notificationSender.SendNotification(this, sms);
    }

    public void SendEmail(Email email) {
        emailPackage.notificationSender.SendNotification(this, email);
    }

}
