package org.kodluyoruz.trendyol.business.notification;

import com.sun.tools.javac.util.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodluyoruz.trendyol.datastructure.EmailElasticPackage;
import org.kodluyoruz.trendyol.model.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class EmailElasticNotificationSenderTest {

    Company company1;
    Company company2;
    Email email1;
    Email email_error;
    User user1;
    User user2;
    User user3;
    User user4;
    PostGroup postGroup1;
    PostGroup postGroup2;


    @BeforeEach
    public void setUp() {
        String languageTR = "TR";
        String languageEN = "EN";

        company1 = new Company("Comp1", languageEN, new EmailElasticPackage());
        company2 = new Company("Comp2", languageTR, new EmailElasticPackage());

        email1 = new Email("Hello", "Greeting");
        email_error = new Email("Hello'", "Greeting");

        user1 = new User("user1");
        user2 = new User("user2");
        user3 = new User("user3");
        user4 = new User("user4");

        postGroup1 = new PostGroup();
        postGroup2 = new PostGroup();
        postGroup1.addUsers(user1, user2, user3, user4);
        postGroup2.addUsers(user2, user4);
    }

    @Test
    public void it_should_success() {
        company1.SendEmail(email1, postGroup1);

        assertThat(company1.getEmailPackage().limit).isEqualTo(1996);
    }
}
