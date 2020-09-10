package org.kodluyoruz.trendyol.exception;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String errorMessage) {
        super(errorMessage);
    }
}
