package com.example.foodservice.controllers;

import com.example.foodservice.models.Food;
import com.example.foodservice.services.FoodService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FoodController {
    private final FoodService foodService;

    private AmqpTemplate template;

    public FoodController(FoodService foodService, AmqpTemplate template) {
        this.foodService = foodService;
        this.template = template;
    }

    @GetMapping("/food/{id}")
    public Food getFoodById(@PathVariable int id) {
        template.convertAndSend("food-queue",
                "Food " + foodService.getById(id).getName() + " is accessed!!!");
        return foodService.getById(id);
    }

    @GetMapping("/food/ids")
    public List<Integer> getAllIds() {
        return foodService.getAllIds();
    }

    @PostMapping("/food")
    public void addFood(@RequestBody Food food) {
        foodService.addFood(food);
    }

    @PutMapping("/food/{id}")
    public void updateFood(@PathVariable int id,
                           @RequestBody Food food) {
        foodService.updateFood(id, food);
    }

    @DeleteMapping("/food/{id}")
    public void deleteFood(@PathVariable int id) {
        foodService.deleteFood(id);
    }
}
