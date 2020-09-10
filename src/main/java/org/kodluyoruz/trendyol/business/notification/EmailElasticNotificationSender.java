package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.ElasticNotificationSender;
import org.kodluyoruz.trendyol.business.validation.PaymentValidation;
import org.kodluyoruz.trendyol.datastructure.EmailElasticPackage;
import org.kodluyoruz.trendyol.exception.InvalidPaymentException;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Email;
import org.kodluyoruz.trendyol.model.dto.NotificationSendDTO;

public class EmailElasticNotificationSender implements ElasticNotificationSender {
    @Override
    public void SendNotification(NotificationSendDTO notificationSendDTO) {
        Email email = (Email) notificationSendDTO.getMessage();
        Company company = notificationSendDTO.getCompany();

        boolean validPayment = PaymentValidation.CheckLastPaidInvoiceDate(company);

        if (!validPayment)
            throw new InvalidPaymentException();

        if (company.getEmailPackage().limit > 0) {
            company.getEmailPackage().limit--;
            System.out.println(company.getName() +
                    " - sent Email (ElasticPackage) -> " + notificationSendDTO.getUserName() +
                    " - subject : " + email.getSubject() +
                    " - content : " + email.getContent() +
                    " - remaining limit : " + company.getEmailPackage().limit);
        } else {
            System.out.printf("\n" + company.getName() + " - exceeded Email limit (ElasticPackage)" +
                    " - current invoice : %.2f \n", company.getInvoice());

            addUnitPriceToInvoice(company);

            System.out.printf(company.getName() +
                    " - sent Email (ElasticPackage) -> " + notificationSendDTO.getUserName() +
                    " - subject : " + email.getSubject() +
                    " - content : " + email.getContent() +
                    " - new invoice : %.2f \n", company.getInvoice());
        }
    }

    @Override
    public void addUnitPriceToInvoice(Company company) {
        EmailElasticPackage emailElasticPackage = (EmailElasticPackage) company.getEmailPackage();

        company.setInvoice(company.getInvoice() + emailElasticPackage.limitExcessUnitPrice);
    }
}
