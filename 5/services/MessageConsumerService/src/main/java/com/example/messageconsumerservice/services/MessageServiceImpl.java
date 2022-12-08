package com.example.messageconsumerservice.services;

import com.example.messageconsumerservice.entities.Message;
import com.example.messageconsumerservice.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message getById(int id) throws SQLException {
        return messageRepository.getById(id);
    }

    @Override
    public List<Message> getAll() throws SQLException {
        return messageRepository.getAll();
    }

    @Override
    public void add(Message message) throws SQLException {
        messageRepository.add(message);
    }
}
