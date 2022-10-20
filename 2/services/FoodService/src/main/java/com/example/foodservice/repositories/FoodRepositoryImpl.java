package com.example.foodservice.repositories;

import com.example.foodservice.models.Food;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class FoodRepositoryImpl implements FoodRepository{
    private List<Food> food;

    public FoodRepositoryImpl(){
        this.food = new ArrayList<>(Arrays.asList(
                (new Food(1, "Pizza Margarita", 24.99f)),
                (new Food(2, "Pizza Diablo", 29.99f)),
                (new Food(3, "Pizza Four Cheese", 27.99f))
        ));
    }

    @Override
    public Food getById(int id) {
        return food.stream()
                .filter(x->x.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No such food existing"));
    }

    @Override
    public List<Integer> getAllIds() {
        return food.stream()
                .mapToInt(Food::getId)
                .boxed()
                .collect(Collectors.toList());
    }
}
