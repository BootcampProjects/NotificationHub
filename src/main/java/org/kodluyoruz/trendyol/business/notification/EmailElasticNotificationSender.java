package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.ElasticNotificationSender;
import org.kodluyoruz.trendyol.datastructure.EmailElasticPackage;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Email;
import org.kodluyoruz.trendyol.model.Message;

public class EmailElasticNotificationSender implements ElasticNotificationSender {
    @Override
    public void SendNotification(Company company, Message message) {
        Email email = (Email) message;

        if (company.getEmailPackage().limit > 0) {
            company.getEmailPackage().limit--;
            System.out.println(company.getName() + " - sent Email (ElasticPackage)" +
                    " - subject : " + email.getSubject() +
                    " - content : " + email.getContent() +
                    " - remaining limit : " + company.getEmailPackage().limit);
        } else {
            System.out.printf("\n" + company.getName() + " - exceeded Email limit (ElasticPackage)" +
                    " - invoice : %.2f \n", company.getInvoice());

            addUnitPriceToInvoice(company);

            System.out.println(company.getName() + " - sent Email (ElasticPackage)" +
                    " - subject : " + email.getSubject() +
                    " - content : " + email.getContent());
        }
    }

    @Override
    public void addUnitPriceToInvoice(Company company) {
        EmailElasticPackage emailElasticPackage = (EmailElasticPackage) company.getEmailPackage();

        company.setInvoice(company.getInvoice() + emailElasticPackage.limitExcessUnitPrice);
    }
}