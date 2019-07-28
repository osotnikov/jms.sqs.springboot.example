package jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmailJmsSenderService {

    @Value("${aws.destination_name}")
    private String destinationName;

    @Autowired
    JmsTemplate jmsTemplate;

    @PostConstruct
    public void init() {

        System.out.println("destination: " + destinationName + ".");
    }

    public void sendEmail(Email email) {

        System.out.println("Sending an email message to: " + destinationName);
        jmsTemplate.convertAndSend(destinationName, email);
    }
}
