package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.FixedNotificationSender;
import org.kodluyoruz.trendyol.datastructure.SmsFixedPackage;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Message;
import org.kodluyoruz.trendyol.model.Sms;

public class SmsFixedNotificationSender implements FixedNotificationSender {
    @Override
    public void SendNotification(Company company, Message message) {
        Sms sms = (Sms) message;

        if (company.getSmsPackage().limit <= 0) {
            System.out.printf("\n" + company.getName() + " - exceeded SMS limit (FixedPackage)" +
                    " - invoice : %.2f \n", company.getInvoice());

            DefineExtraPackage(company);
        }
        company.getSmsPackage().limit--;

        System.out.println(company.getName() + " - sent SMS (FixedPackage)" +
                " - content : " + sms.getContent() +
                " - remaining limit : " + company.getSmsPackage().limit);
    }

    @Override
    public void DefineExtraPackage(Company company) {
        SmsFixedPackage smsFixedPackage = (SmsFixedPackage) company.getSmsPackage();

        company.getSmsPackage().limit = smsFixedPackage.limitExcessExtraLimit;
        company.setInvoice(company.getInvoice() + smsFixedPackage.limitExcessPackagePrice);

        System.out.printf(company.getName() + " - defining extra SMS package (FixedPackage) " +
                " - new SMS package limit : " + smsFixedPackage.limitExcessExtraLimit +
                " - invoice : %.2f \n\n", company.getInvoice());
    }
}
