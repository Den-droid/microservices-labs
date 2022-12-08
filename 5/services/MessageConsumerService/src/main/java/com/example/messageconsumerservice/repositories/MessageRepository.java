package com.example.messageconsumerservice.repositories;

import com.example.messageconsumerservice.entities.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageRepository {
    Message getById(int id) throws SQLException;

    List<Message> getAll() throws SQLException;

    void add(Message message) throws SQLException;
}
