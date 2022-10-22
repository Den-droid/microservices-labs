package com.example.foodservice.repositories;

import com.example.foodservice.models.Food;

import java.util.List;

public interface FoodRepository {
    Food getById(int id);

    List<Integer> getAllIds();

    void add(Food food);

    void update(Food food);

    void delete(int id);
}
