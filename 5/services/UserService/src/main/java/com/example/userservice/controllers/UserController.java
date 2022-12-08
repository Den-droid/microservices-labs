package com.example.userservice.controllers;

import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    private AmqpTemplate template;

    public UserController(UserService userService, AmqpTemplate template) {
        this.userService = userService;
        this.template = template;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        template.convertAndSend("user-queue",
                "User " + userService.getById(id).getName() + " is accessed!!!");
        return userService.getById(id);
    }

    @GetMapping("/users/ids")
    public List<Integer> getAllIds() {
        return userService.getAllIds();
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable int id,
                           @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
