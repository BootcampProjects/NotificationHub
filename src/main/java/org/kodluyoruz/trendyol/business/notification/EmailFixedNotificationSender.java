package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.FixedNotificationSender;
import org.kodluyoruz.trendyol.datastructure.EmailFixedPackage;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Email;
import org.kodluyoruz.trendyol.model.Message;

public class EmailFixedNotificationSender implements FixedNotificationSender {
    @Override
    public void SendNotification(Company company, Message message) {
        Email email = (Email) message;

        if (company.getEmailPackage().limit <= 0) {
            System.out.println("\n" + company.getName() + " - exceeded Email limit (FixedPackage)"
                    + " - invoice : " + company.getInvoice());
            DefineExtraPackage(company);
        }
        company.getEmailPackage().limit--;
        System.out.println(company.getName() + " - sent Email (FixedPackage)"
                + "- subject : " + email.getSubject() + " - content : " + email.getContent()
                + " - remaining limit : " + company.getEmailPackage().limit);
    }

    @Override
    public void DefineExtraPackage(Company company) {
        EmailFixedPackage emailFixedPackage = (EmailFixedPackage) company.getEmailPackage();

        company.getEmailPackage().limit = emailFixedPackage.limitExcessExtraLimit;
        company.setInvoice(company.getInvoice() + emailFixedPackage.limitExcessPackagePrice);

        System.out.printf(company.getName() + " - defining extra Email package (FixedPackage)"
                + " - new Email package limit : " + emailFixedPackage.limitExcessExtraLimit
                + " - invoice : %.2f \n\n", company.getInvoice());
    }
}
