package com.example.foodservice.repositories;

import com.example.foodservice.models.Food;

import java.util.List;

public interface FoodRepository {
    Food getById(int id);
    List<Integer> getAllIds();
}
