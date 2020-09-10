package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.ElasticNotificationSender;
import org.kodluyoruz.trendyol.business.validation.MessageContentValidation;
import org.kodluyoruz.trendyol.constant.ErrorMessage;
import org.kodluyoruz.trendyol.datastructure.SmsElasticPackage;
import org.kodluyoruz.trendyol.exception.InvalidMessageContentException;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.Sms;
import org.kodluyoruz.trendyol.model.dto.NotificationSendDTO;

public class SmsElasticNotificationSender implements ElasticNotificationSender {
    @Override
    public void SendNotification(NotificationSendDTO notificationSendDTO) {
        Sms sms = (Sms) notificationSendDTO.getMessage();
        Company company = notificationSendDTO.getCompany();

        boolean validContent = MessageContentValidation.CheckMessageContent(sms);

        if (!validContent) throw new InvalidMessageContentException(ErrorMessage.InvalidMessageContent(company.getLanguage()));

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
