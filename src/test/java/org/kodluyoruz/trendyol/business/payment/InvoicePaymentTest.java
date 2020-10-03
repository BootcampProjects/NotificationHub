package org.kodluyoruz.trendyol.business.payment;

import org.junit.jupiter.api.Test;
import org.kodluyoruz.trendyol.datastructures.SmsFixedPackage;
import org.kodluyoruz.trendyol.models.Company;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class InvoicePaymentTest {

    @Test
    public void payInvoice_WhenPaid_ShouldInvoiceSetZeroAndDefineLastPaidInvoiceDate() {
        // Arrange
        Company company = new Company("Company", 0, new SmsFixedPackage());
        Date companyPaidDate = company.getLastPaidInvoiceDate();

        // Act
        InvoicePayment.payInvoice(company);

        // Assert
        assertThat(company.getInvoice()).isEqualTo(0);
        assertThat(company.getLastPaidInvoiceDate()).isAfter(companyPaidDate);
    }
}