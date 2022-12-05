package com.example.orderservice.services;

import com.example.orderservice.entities.Order;

import java.util.List;

public interface OrderService {
    Order getById(int orderId);

    void generate(int count);

    List<Integer> getIds();
}
