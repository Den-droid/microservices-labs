package com.example.orderservice.controllers;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entities.Food;
import com.example.orderservice.entities.Order;
import com.example.orderservice.entities.User;
import com.example.orderservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class MainController {
    private final OrderService orderService;

    public MainController(OrderService orderService) {
        this.orderService = orderService;
    }

    private String foodServiceUrl = "http://food-service-service/api/food";
    private String userServiceUrl = "http://user-service-service/api/users";

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable int id) {
        Order order = orderService.getById(id);
        try {
            RestTemplate template = new RestTemplate();
            Food food = template.getForObject(foodServiceUrl + "/" + order.getFoodId(),
                    Food.class);
            User user = template.getForObject(userServiceUrl + "/" + order.getUserId(),
                    User.class);
            OrderDto dto = new OrderDto();
            dto.setOrderId(order.getId());
            dto.setUser(user);
            dto.setFood(food);
            return ResponseEntity.ok(dto);
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode().is4xxClientError())
                return ResponseEntity.notFound().build();
            else if (ex.getStatusCode().is5xxServerError())
                return ResponseEntity.internalServerError().build();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/ids")
    public ResponseEntity<List<Integer>> getIds() {
        return ResponseEntity.ok(orderService.getIds());
    }

    @PostMapping("generate")
    public void generateOrders() {
        orderService.generate(10);
    }
}
