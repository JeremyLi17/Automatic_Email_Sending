package com.example.messageproducer;

import com.example.messageproducer.domain.EmailMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author jeremy on 2023/5/10
 */
@Component
public class ScheduledTask {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void sendMessageEverySecond() {
        EmailMessage email = EmailMessage.builder()
                .to("HaotianLi0505@hotmail.com")
                .subject("test for RabbitMQ")
                .message("Hello this is jeremy from gmail!")
                .build();

        // convert to JSON and then to string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(email);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
        }

        if (jsonString != null) {
            rabbitTemplate.convertAndSend(
                    "emailExchange",
                    "message.scheduled.email",
                    jsonString
            );
        }
    }
}
