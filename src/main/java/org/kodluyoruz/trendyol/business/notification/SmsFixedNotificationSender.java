package org.kodluyoruz.trendyol.business.notification;

import org.kodluyoruz.trendyol.business.notification.abstraction.FixedNotificationSender;
import org.kodluyoruz.trendyol.business.validation.MessageContentValidation;
import org.kodluyoruz.trendyol.constants.ErrorMessage;
import org.kodluyoruz.trendyol.datastructures.SmsFixedPackage;
import org.kodluyoruz.trendyol.exceptions.InvalidMessageContentException;
import org.kodluyoruz.trendyol.models.Company;
import org.kodluyoruz.trendyol.models.Sms;
import org.kodluyoruz.trendyol.models.dtos.NotificationSendDTO;

public class SmsFixedNotificationSender implements FixedNotificationSender {
    @Override
    public void sendNotification(NotificationSendDTO notificationSendDTO) {
        Sms sms = (Sms) notificationSendDTO.getMessage();
        Company company = notificationSendDTO.getCompany();

        boolean validContent = MessageContentValidation.checkMessageContent(sms);

        if (!validContent) throw new InvalidMessageContentException(ErrorMessage.invalidMessageContent(company.getLanguage()));

        if (company.getSmsPackage().limit <= 0) {
            System.out.printf("\n" + company.getName() + " - exceeded SMS limit (FixedPackage)" +
                    " - current invoice : %.2f \n", company.getInvoice());

            defineExtraPackage(company);
        }
        company.getSmsPackage().limit--;

        System.out.println(company.getName() +
                " - sent SMS (FixedPackage) -> " + notificationSendDTO.getUserName() +
                " - content : " + sms.getContent() +
                " - remaining limit : " + company.getSmsPackage().limit);
    }

    @Override
    public void defineExtraPackage(Company company) {
        SmsFixedPackage smsFixedPackage = (SmsFixedPackage) company.getSmsPackage();

        company.getSmsPackage().limit = smsFixedPackage.limitExcessExtraLimit;
        company.setInvoice(company.getInvoice() + smsFixedPackage.limitExcessPackagePrice);

        System.out.printf(company.getName() + " - defining extra SMS package (FixedPackage) " +
                " - new SMS package limit : " + smsFixedPackage.limitExcessExtraLimit +
                " - new invoice : %.2f \n\n", company.getInvoice());
    }
}
