package com.example.messageconsumerservice.controllers;

import com.example.messageconsumerservice.entities.Message;
import com.example.messageconsumerservice.services.MessageService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDateTime;

@EnableRabbit
@Component
public class RabbitHandler {
    @Autowired
    private MessageService messageService;

    @RabbitListener(queues = {"food-queue", "user-queue"})
    public void handle(String message) throws SQLException {
        Message toAdd = new Message();
        toAdd.setText(message);
        toAdd.setDateDelivered(LocalDateTime.now());
        messageService.add(toAdd);
    }
}
