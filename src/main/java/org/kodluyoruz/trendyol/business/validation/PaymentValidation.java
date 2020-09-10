package org.kodluyoruz.trendyol.business.validation;

import org.kodluyoruz.trendyol.model.BlackList;
import org.kodluyoruz.trendyol.model.Company;
import org.kodluyoruz.trendyol.model.dto.BlackListCompanyDTO;

import java.util.Calendar;
import java.util.Date;

public class PaymentValidation {
    public static boolean CheckLastPaidInvoiceDate(Company company) {

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
