package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.ElasticNotificationSender;
import org.kodluyoruz.trendyol.business.validation.PaymentValidation;
import org.kodluyoruz.trendyol.datastructure.SmsElasticPackage;
import org.kodluyoruz.trendyol.exception.InvalidPaymentException;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Sms;
import org.kodluyoruz.trendyol.model.dto.NotificationSendDTO;

public class SmsElasticNotificationSender implements ElasticNotificationSender {
    @Override
    public void SendNotification(NotificationSendDTO notificationSendDTO) {
        Sms sms = (Sms) notificationSendDTO.getMessage();
        Company company = notificationSendDTO.getCompany();

        boolean validPayment = PaymentValidation.CheckLastPaidInvoiceDate(company);

        if (!validPayment)
            throw new InvalidPaymentException();

        if (company.getSmsPackage().limit > 0) {
            company.getSmsPackage().limit--;

            System.out.println(company.getName() +
                    " - sent SMS (ElasticPackage) -> " + notificationSendDTO.getUserName() +
                    " - content : " + sms.getContent() +
                    " - remaining limit : " + company.getSmsPackage().limit);
        } else {
            System.out.printf("\n" + company.getName() + " - exceeded SMS limit (ElasticPackage)" +
                    " - current invoice : %.2f \n", company.getInvoice());

            addUnitPriceToInvoice(company);

            System.out.printf(company.getName() +
                    " - sent SMS (ElasticPackage) -> " + notificationSendDTO.getUserName() +
                    " - content : " + sms.getContent() +
                    " - new invoice : %.2f \n", company.getInvoice());
        }
    }

    @Override
    public void addUnitPriceToInvoice(Company company) {
        SmsElasticPackage smsFixedPackage = (SmsElasticPackage) company.getSmsPackage();

        company.setInvoice(company.getInvoice() + smsFixedPackage.limitExcessUnitPrice);
    }
}
