package com.example.emailsender;

import com.example.emailsender.config.MailConfig;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author jeremy on 2023/5/10
 */
@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService() {
        emailSender = MailConfig.getJavaMailSender();
    }

    public void sendSimpleMessage(String to,
                                  String subject,
                                  String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
