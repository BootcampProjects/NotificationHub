package org.kodluyoruz.trendyol;

import org.kodluyoruz.trendyol.datastructure.SmsElasticPackage;
import org.kodluyoruz.trendyol.datastructure.SmsFixedPackage;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Sms;

public class NotificationHubApp {
    public static void main(String[] args) {
        Company company1 = new Company("Comp1", new SmsElasticPackage());
        Company company2 = new Company("Comp2", new SmsFixedPackage());

        Sms sms1 = new Sms("Hi!");
        Sms sms2 = new Sms("Hellooo");

        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);
        company1.SendSms(sms1);

        System.out.println();

        company2.SendSms(sms2);
        company2.SendSms(sms2);
        company2.SendSms(sms2);
        company2.SendSms(sms2);
        company2.SendSms(sms2);
        company2.SendSms(sms2);
        company2.SendSms(sms2);
    }
}
