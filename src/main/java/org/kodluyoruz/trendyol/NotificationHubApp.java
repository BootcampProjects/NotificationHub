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
        Company company1 = new Company("Comp1", new SmsElasticPackage());
        Company company2 = new Company("Comp2", new EmailElasticPackage());
        Company company3 = new Company("Comp3", new SmsFixedPackage(), new EmailFixedPackage());

        Sms sms1 = new Sms("Hi");
        Email email1 = new Email("Hello", "Greeting");

        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);

        System.out.println("\n\t*************\n");

        company2.SendEmail(email1);
        company2.SendEmail(email1);
        company2.SendEmail(email1);
        company2.SendEmail(email1);
        company2.SendEmail(email1);
        company2.SendEmail(email1);

        System.out.println("\n\t*************\n");

        company3.SendEmail(email1);
        company3.SendEmail(email1);
        company3.SendEmail(email1);

        System.out.println();

        company3.SendSms(sms1);
        company3.SendSms(sms1);
        company3.SendSms(sms1);
        company3.SendSms(sms1);
        company3.SendSms(sms1);
        company3.SendSms(sms1);

        System.out.println("\n\t*************\n");

        System.out.printf(company1.getName() + " - invoice : %.2f\n", company1.getInvoice());
        System.out.printf(company2.getName() + " - invoice : %.2f\n", company2.getInvoice());
        System.out.printf(company3.getName() + " - invoice : %.2f\n", company3.getInvoice());
    }
}
