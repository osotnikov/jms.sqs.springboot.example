package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "${aws.destination_name}")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }

}
