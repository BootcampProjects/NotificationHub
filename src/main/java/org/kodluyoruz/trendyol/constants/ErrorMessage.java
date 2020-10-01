package org.kodluyoruz.trendyol.constants;

public class ErrorMessage {

    private static final String[] InvalidPaymentErrorMessage = {
            "Invalid Payment",
            "Geçersiz Ödeme Hatası",
            "Invalid Payment Error"
    };

    private static final String[] InvalidMessageContentErrorMessage = {
            "Invalid Message Content",
            "Geçersiz Mesaj İçeriği Hatası",
            "Invalid Message Content Error"
    };

    public static String InvalidPayment(int language) {
        return InvalidPaymentErrorMessage[language];
    }

    public static String InvalidMessageContent(int language) {
        return InvalidMessageContentErrorMessage[language];
    }
}
