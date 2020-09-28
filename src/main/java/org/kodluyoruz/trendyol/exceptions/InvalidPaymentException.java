package org.kodluyoruz.trendyol.exceptions;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String errorMessage) {
        super(errorMessage);
    }
}
