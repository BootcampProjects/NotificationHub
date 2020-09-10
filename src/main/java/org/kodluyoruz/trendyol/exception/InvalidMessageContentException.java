package org.kodluyoruz.trendyol.exception;

public class InvalidMessageContentException extends RuntimeException {
    public InvalidMessageContentException(String errorMessage) {
        super(errorMessage);
    }
}
