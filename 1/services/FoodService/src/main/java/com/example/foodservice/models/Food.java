package com.example.foodservice.models;

import com.example.foodservice.repositories.FoodRepository;

public class Food {
    private int id;
    private String name;
    private float price;

    public Food() {
    }

    public Food(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
