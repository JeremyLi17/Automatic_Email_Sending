package com.example.emailsender;

import com.example.emailsender.domain.EmailMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.IOException;


/**
 * @author jeremy on 2023/5/10
 */
public class RabbitListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        EmailMessage email = null;
        try {
            email = objectMapper.readValue(message.getBody(), EmailMessage.class);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        if (email != null) {
            // send Email
            EmailService emailService = new EmailService();
            emailService.sendSimpleMessage(email.getTo(), email.getSubject(), email.getMessage());
        }
    }
}
