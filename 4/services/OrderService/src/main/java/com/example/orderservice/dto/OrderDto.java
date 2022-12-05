package com.example.orderservice.dto;

import com.example.orderservice.entities.Food;
import com.example.orderservice.entities.User;

public class OrderDto {
    private int orderId;
    private User user;
    private Food food;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
