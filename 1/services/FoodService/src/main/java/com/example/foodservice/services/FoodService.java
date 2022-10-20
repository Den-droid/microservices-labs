package com.example.foodservice.services;

import com.example.foodservice.models.Food;

import java.util.List;

public interface FoodService {
    Food getById(int id);

    List<Integer> getAllIds();
}
