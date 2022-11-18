package com.example.userservice.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    private String status = "ok";

    @GetMapping("/users")
    public String getUsersString() throws InterruptedException {
        if (!Objects.equals(status, "ok"))
            Thread.sleep(10 * 1000);
        return "You have 3 users in system!!!";
    }

    @GetMapping("/users/bad")
    public void setBadStatus() {
        status = "failed";
    }
}
