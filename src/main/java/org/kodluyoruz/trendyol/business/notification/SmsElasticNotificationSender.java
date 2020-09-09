package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.ElasticNotificationSender;
import org.kodluyoruz.trendyol.datastructure.SmsElasticPackage;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Message;
import org.kodluyoruz.trendyol.model.Sms;

public class SmsElasticNotificationSender implements ElasticNotificationSender {
    @Override
    public void SendNotification(Company company, Message message) {
        Sms sms = (Sms) message;

        if (company.getSmsPackage().limit > 0) {
            company.getSmsPackage().limit--;

            System.out.println(company.getName() + " - sent SMS (ElasticPackage)" +
                    " - content : " + sms.getContent() +
                    " - remaining limit : " + company.getSmsPackage().limit);
        } else {
            System.out.printf("\n" + company.getName() + " - exceeded SMS limit (ElasticPackage)" +
                    " - invoice : %.2f \n", company.getInvoice());

            addUnitPriceToInvoice(company);

            System.out.println(company.getName() + " - sent SMS (ElasticPackage) - content : " + sms.getContent());
        }
    }

    @Override
    public void addUnitPriceToInvoice(Company company) {
        SmsElasticPackage smsFixedPackage = (SmsElasticPackage) company.getSmsPackage();

        company.setInvoice(company.getInvoice() + smsFixedPackage.limitExcessUnitPrice);
    }
}
