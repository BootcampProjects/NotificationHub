package org.kodluyoruz.trendyol.constants;

public class ErrorMessage {

    public static String InvalidPayment(String language) {
        if (language == "TR") {
            return "Geçersiz Ödeme Hatası";
        } else if (language == "EN")
            return "Invalid Payment Error";

        else return "Invalid Payment";
    }

    public static String InvalidMessageContent(String language) {
        if (language == "TR") {
            return "Geçersiz Message İçeriği Hatası";
        } else if (language == "EN")
            return "Invalid Message Content Error";

        else return "Invalid Message Content";
    }
}
