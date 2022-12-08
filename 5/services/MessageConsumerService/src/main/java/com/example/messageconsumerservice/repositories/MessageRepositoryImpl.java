package com.example.messageconsumerservice.repositories;

import com.example.messageconsumerservice.entities.Message;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private final String url;
    private final String username;
    private final String password;

    public MessageRepositoryImpl(Environment env) {
        this.username = env.getProperty("spring.datasource.username");
        this.password = env.getProperty("spring.datasource.password");
        this.url = env.getProperty("spring.datasource.url");
    }

    private static final String SELECT_BY_ID = "select * from messages where id=?";
    private static final String SELECT_ALL = "select * from messages";
    private static final String INSERT = "insert into messages(text, date_delivered) values (?, ?);";

    @Override
    public Message getById(int id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet set = preparedStatement.executeQuery()) {
                if (set.next()) {
                    Message message = new Message();
                    message.setId(set.getInt(1));
                    message.setText(set.getString(2));
                    message.setDateDelivered(set.getObject(3, LocalDateTime.class));
                    return message;
                } else return null;
            }
        }
    }

    @Override
    public List<Message> getAll() throws SQLException {
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet set = preparedStatement.executeQuery()) {
            List<Message> messages = new ArrayList<>();
            while (set.next()) {
                Message message = new Message();
                message.setId(set.getInt(1));
                message.setText(set.getString(2));
                message.setDateDelivered(set.getObject(3, LocalDateTime.class));
                messages.add(message);
            }
            return messages;
        }
    }

    @Override
    public void add(Message message) throws SQLException {
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, message.getText());
            preparedStatement.setObject(2, Date.from(
                    message.getDateDelivered().atZone(ZoneId.systemDefault()).toInstant()
            ));
            preparedStatement.executeUpdate();
        }
    }
}
