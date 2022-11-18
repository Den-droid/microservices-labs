package com.example.foodservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FoodController {
    private String status = "ok";

    @GetMapping("/food")
    public String getFoodString() throws InterruptedException {
        if (!Objects.equals(status, "ok"))
            Thread.sleep(10 * 1000);
        return "You have 3 pizzas in warehouse!!!";
    }

    @GetMapping("/food/bad")
    public void setBadStatus() {
        status = "fail";
    }
}
