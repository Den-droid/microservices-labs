package com.example.foodservice.controllers;

import com.example.foodservice.models.Food;
import com.example.foodservice.services.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FoodController {
    private final FoodService foodService;
    private String status = "ok";

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/food/{id}")
    public Food getUserById(@PathVariable int id) throws InterruptedException {
        if (!Objects.equals(status, "ok"))
            Thread.sleep(10 * 1000);
        return foodService.getById(id);
    }

    @GetMapping("/food/ids")
    public List<Integer> getAllIds() {
        return foodService.getAllIds();
    }

    @PostMapping("/food")
    public void addUser(@RequestBody Food food) {
        foodService.addFood(food);
    }

    @PutMapping("/food/{id}")
    public void updateUser(@PathVariable int id,
                           @RequestBody Food food) {
        foodService.updateFood(id, food);
    }

    @DeleteMapping("/food/{id}")
    public void deleteUser(@PathVariable int id) {
        foodService.deleteFood(id);
    }

    @GetMapping("/food/bad")
    public void simulateBadRequest() {
        status = "failed";
    }
}
