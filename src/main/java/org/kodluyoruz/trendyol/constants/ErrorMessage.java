package org.kodluyoruz.trendyol.constants;

public class ErrorMessage {

    private static final String[] INVALID_PAYMENT_ERROR_MESSAGE = {
            "Invalid Payment",
            "Geçersiz Ödeme Hatası",
            "Invalid Payment Error"
    };

    private static final String[] INVALID_MESSAGE_CONTENT_ERROR_MESSAGE = {
            "Invalid Message Content",
            "Geçersiz Mesaj İçeriği Hatası",
            "Invalid Message Content Error"
    };

    public static String invalidPayment(int language) {
        return INVALID_PAYMENT_ERROR_MESSAGE[language];
    }

    public static String invalidMessageContent(int language) {
        return INVALID_MESSAGE_CONTENT_ERROR_MESSAGE[language];
    }
}
