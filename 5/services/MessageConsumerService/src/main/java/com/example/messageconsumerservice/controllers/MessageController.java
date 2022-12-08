package com.example.messageconsumerservice.controllers;

import com.example.messageconsumerservice.entities.Message;
import com.example.messageconsumerservice.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAll() throws SQLException {
        return ResponseEntity.ok(messageService.getAll());
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getById(@PathVariable int id) throws SQLException {
        return ResponseEntity.ok(messageService.getById(id));
    }
}
