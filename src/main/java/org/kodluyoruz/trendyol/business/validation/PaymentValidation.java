package org.kodluyoruz.trendyol.business.validation;

import org.kodluyoruz.trendyol.models.BlackList;
import org.kodluyoruz.trendyol.models.Company;
import org.kodluyoruz.trendyol.models.dtos.BlackListCompanyDTO;

import java.util.Calendar;
import java.util.Date;

public class PaymentValidation {
    public static boolean checkLastPaidInvoiceDate(Company company) {

        Date dateNow = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(company.getCreatedDate());
        cal.add(Calendar.MONTH, 2);

        Date twoMonthsLaterCreate = cal.getTime();

        if (dateNow.before(twoMonthsLaterCreate) || company.getLastPaidInvoiceDate().after(twoMonthsLaterCreate)) {
            // no problem
            return true;
        } else {
            BlackListCompanyDTO blackListCompanyDTO = new BlackListCompanyDTO(company.getId(), company.getName());
            BlackList blackList = BlackList.getInstance();
            blackList.addCompany(blackListCompanyDTO);
            return false;
        }
    }
}
