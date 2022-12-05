package com.example.orderservice.services.impl;

import com.example.orderservice.entities.Order;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.services.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final String foodServiceUrl = "http://food-service-service/api/food";
    private final String userServiceUrl = "http://user-service-service/api/users";

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getById(int orderId) {
        return orderRepository.getById(orderId);
    }

    @Override
    public void generate(int count) {
        RestTemplate template = new RestTemplate();
        List<Integer> userIds = template.getForObject(userServiceUrl + "/ids", List.class);
        List<Integer> foodIds = template.getForObject(foodServiceUrl + "/ids", List.class);

        if (userIds == null || foodIds == null)
            throw new NoSuchElementException();

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int userId = userIds.get(random.nextInt(userIds.size()));
            int foodId = foodIds.get(random.nextInt(foodIds.size()));
            List<Integer> orderIds = orderRepository.getIds();

            Order order = new Order();
            if (orderIds == null || orderIds.isEmpty())
                order.setId(0);
            else
                order.setId(orderIds.get(orderIds.size() - 1) + 1);
            order.setFoodId(foodId);
            order.setUserId(userId);

            orderRepository.add(order);
        }
    }

    @Override
    public List<Integer> getIds() {
        return orderRepository.getIds();
    }
}
