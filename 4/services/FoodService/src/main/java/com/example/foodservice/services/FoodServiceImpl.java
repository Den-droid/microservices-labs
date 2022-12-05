package com.example.foodservice.services;

import com.example.foodservice.models.Food;
import com.example.foodservice.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food getById(int id) {
        return foodRepository.getById(id);
    }

    @Override
    public List<Integer> getAllIds() {
        return foodRepository.getAllIds();
    }

    @Override
    public void addFood(Food food) {
        foodRepository.add(food);
    }

    @Override
    public void updateFood(int id, Food food) {
        food.setId(id);
        foodRepository.update(food);
    }

    @Override
    public void deleteFood(int id) {
        foodRepository.delete(id);
    }
}
