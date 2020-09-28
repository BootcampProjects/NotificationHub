package org.kodluyoruz.trendyol.exceptions;

public class InvalidMessageContentException extends RuntimeException {
    public InvalidMessageContentException(String errorMessage) {
        super(errorMessage);
    }
}
