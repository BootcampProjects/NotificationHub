package org.kodluyoruz.trendyol;

import org.kodluyoruz.trendyol.datastructure.EmailElasticPackage;
import org.kodluyoruz.trendyol.datastructure.EmailFixedPackage;
import org.kodluyoruz.trendyol.datastructure.SmsElasticPackage;
import org.kodluyoruz.trendyol.datastructure.SmsFixedPackage;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Email;
import org.kodluyoruz.trendyol.model.Sms;

public class NotificationHubApp {
    public static void main(String[] args) {
        Company company1 = new Company("Comp1", new SmsElasticPackage(), new EmailFixedPackage());
        Company company2 = new Company("Comp2", new SmsFixedPackage(), new EmailElasticPackage());

        Sms sms1 = new Sms("Hi!");
        Sms sms2 = new Sms("Hellooo");

        Email email1 = new Email("Hi!", "Greeting");

        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);

        System.out.println();

        company1.SendEmail(email1);
        company1.SendEmail(email1);
        company1.SendEmail(email1);
        company1.SendEmail(email1);

        System.out.println("\n\t*************\n");


        company2.SendSms(sms2);
        company2.SendSms(sms2);
        company2.SendSms(sms2);
        company2.SendSms(sms2);
        company2.SendSms(sms2);
        company2.SendSms(sms2);
        company2.SendSms(sms2);

        System.out.println();

        company2.SendEmail(email1);
        company2.SendEmail(email1);
        company2.SendEmail(email1);
        company2.SendEmail(email1);
        company2.SendEmail(email1);
        company2.SendEmail(email1);
    }
}
