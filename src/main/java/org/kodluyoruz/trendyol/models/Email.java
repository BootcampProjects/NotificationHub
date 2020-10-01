package org.kodluyoruz.trendyol.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email extends Message {
    private String subject;

    public Email(String content, String subject) {
        super(content);
        this.subject = subject;
    }
}
