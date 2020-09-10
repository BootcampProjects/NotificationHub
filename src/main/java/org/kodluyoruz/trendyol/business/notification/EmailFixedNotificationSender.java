package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.FixedNotificationSender;
import org.kodluyoruz.trendyol.business.validation.MessageContentValidation;
import org.kodluyoruz.trendyol.business.validation.PaymentValidation;
import org.kodluyoruz.trendyol.datastructure.EmailFixedPackage;
import org.kodluyoruz.trendyol.exception.InvalidPaymentException;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Email;
import org.kodluyoruz.trendyol.model.dto.NotificationSendDTO;

public class EmailFixedNotificationSender implements FixedNotificationSender {
    @Override
    public void SendNotification(NotificationSendDTO notificationSendDTO) {
        Email email = (Email) notificationSendDTO.getMessage();
        Company company = notificationSendDTO.getCompany();

        boolean validPayment = PaymentValidation.CheckLastPaidInvoiceDate(company);
        boolean validContent = MessageContentValidation.CheckMessageContent(email);

        if (!validContent)
            // exception

        if (!validPayment)
            throw new InvalidPaymentException();


        if (company.getEmailPackage().limit <= 0) {
            System.out.printf("\n" + company.getName() + " - exceeded Email limit (FixedPackage)" +
                    " - current invoice : %.2f \n", company.getInvoice());

            DefineExtraPackage(company);
        }
        company.getEmailPackage().limit--;

        System.out.println(company.getName() +
                " - sent Email (FixedPackage) -> " + notificationSendDTO.getUserName() +
                " - subject : " + email.getSubject() + " - content : " + email.getContent() +
                " - remaining limit : " + company.getEmailPackage().limit);
    }

    @Override
    public void DefineExtraPackage(Company company) {
        EmailFixedPackage emailFixedPackage = (EmailFixedPackage) company.getEmailPackage();

        company.getEmailPackage().limit = emailFixedPackage.limitExcessExtraLimit;
        company.setInvoice(company.getInvoice() + emailFixedPackage.limitExcessPackagePrice);

        System.out.printf(company.getName() + " - defining extra Email package (FixedPackage)" +
                " - new Email package limit : " + emailFixedPackage.limitExcessExtraLimit +
                " - new invoice : %.2f \n\n", company.getInvoice());
    }
}
