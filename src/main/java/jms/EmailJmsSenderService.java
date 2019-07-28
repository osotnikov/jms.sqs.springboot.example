package jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailJmsSenderService {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendEmail(Email email) {

        System.out.println("Sending an email message.");
        jmsTemplate.convertAndSend("olegk-queue", new Email("info@example.com", "Hello"));
    }
}
