package com.example.messageconsumerservice;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessageConsumerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageConsumerServiceApplication.class, args);
    }

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

    @Bean
    public Queue userQueue() {
        return new Queue("user-queue", false);
    }

    @Bean
    public Queue foodQueue() {
        return new Queue("food-queue", false);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(rabbitHost);
    }
}
