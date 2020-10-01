package org.kodluyoruz.trendyol.business.validation;

import org.kodluyoruz.trendyol.models.Message;

public class MessageContentValidation {
    public static boolean checkMessageContent(Message message) {

        if (message.getContent().length() < 2)
            return false;
        else if (message.getContent().contains("'"))
            return false;

        return true;
    }
}
