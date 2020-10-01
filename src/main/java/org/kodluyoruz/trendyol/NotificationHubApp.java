package org.kodluyoruz.trendyol;

import org.kodluyoruz.trendyol.business.payment.InvoicePayment;
import org.kodluyoruz.trendyol.business.validation.PaymentValidation;
import org.kodluyoruz.trendyol.datastructures.EmailElasticPackage;
import org.kodluyoruz.trendyol.datastructures.EmailFixedPackage;
import org.kodluyoruz.trendyol.datastructures.SmsElasticPackage;
import org.kodluyoruz.trendyol.datastructures.SmsFixedPackage;
import org.kodluyoruz.trendyol.models.*;
import org.kodluyoruz.trendyol.models.dtos.BlackListCompanyDTO;

import java.util.Calendar;
import java.util.List;

public class NotificationHubApp {
    public static void main(String[] args) {
        int languageTR = 1;
        int languageEN = 2;

        Company company1 = new Company("Comp1", languageEN, new SmsElasticPackage());
        Company company2 = new Company("Comp2", languageEN, new EmailElasticPackage());
        Company company3 = new Company("Comp3", languageTR, new SmsFixedPackage(), new EmailFixedPackage());

        Sms sms1 = new Sms("Hi");
        Email email1 = new Email("Hello'", "Greeting");

        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        User user4 = new User("user4");

        PostGroup postGroup1 = new PostGroup();
        PostGroup postGroup2 = new PostGroup();
        postGroup1.addUsers(user1, user2, user3, user4);
        postGroup2.addUsers(user2, user4);

        company1.sendSms(sms1, postGroup1);

        System.out.println("\n\t*************\n");

        try {
            company2.sendEmail(email1, postGroup1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n\t*************\n");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -4);

        company3.setCreatedDate(cal.getTime());
        company3.setLastPaidInvoiceDate(cal.getTime());

        boolean result = PaymentValidation.checkLastPaidInvoiceDate(company3);

        System.out.println(result);


        BlackList blackList = BlackList.getInstance();
        List<BlackListCompanyDTO> arrayList = blackList.getBlackListCompanies();

        for (BlackListCompanyDTO blackListCompanyDTO : arrayList) {
            System.out.println(blackListCompanyDTO.getCompanyName());
        }

        try {
            company3.sendEmail(email1, postGroup1);
            company3.sendSms(sms1, postGroup2);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();


        System.out.println("\n\t*************\n");

        System.out.println(company3.getName() + " - SMS package validity date : " + company3.getSmsPackage().validityDate);

        System.out.println("\n\t*************\n");

        InvoicePayment.payInvoice(company1);

        System.out.println("\n\t*************\n");

        System.out.printf(company1.getName() + " - invoice : %.2f\n", company1.getInvoice());
        System.out.printf(company2.getName() + " - invoice : %.2f\n", company2.getInvoice());
        System.out.printf(company3.getName() + " - invoice : %.2f\n", company3.getInvoice());

        System.out.println("\n\t*************\n");


    }
}
