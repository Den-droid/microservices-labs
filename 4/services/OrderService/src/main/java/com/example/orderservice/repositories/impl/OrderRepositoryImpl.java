package com.example.orderservice.repositories.impl;

import com.example.orderservice.entities.Order;
import com.example.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void add(Order order) {
        orders.add(order);
    }

    @Override
    public List<Integer> getIds() {
        return orders.stream()
                .map(Order::getId)
                .collect(Collectors.toList());
    }

    @Override
    public Order getById(int id) {
        return orders.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
