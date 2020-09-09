package org.kodluyoruz.trendyol.business.payment;

import org.kodluyoruz.trendyol.model.Company;

import java.util.Date;

public class InvoicePayment {
    public static void PayInvoice(Company company) {
        System.out.printf(company.getName() + " - invoice : %.2f \n", company.getInvoice());
        company.setInvoice(0);
        company.setLastPaidInvoiceDate(new Date());
        System.out.printf(company.getName() + " - your invoice has been paid" +
                " - payment date : " + company.getLastPaidInvoiceDate() +
                " - invoice : %.2f \n", company.getInvoice());
    }
}
